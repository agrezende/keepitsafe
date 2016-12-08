
var gulp = require("gulp");
var fs = require("fs");
var gutil = require('gulp-util');

var browserify = require("browserify");
var tsify = require("tsify");
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var resolve = require('resolve');

var less = require("gulp-less");
var cleanCss = require("gulp-clean-css");

var sourcemaps = require("gulp-sourcemaps");
var uglify = require("gulp-uglify");
var rename = require("gulp-rename");

var browserSync = require("browser-sync").create();

// var ts = require("gulp-typescript");
// var webpack = require("webpack-stream");
// var watchify = require("watchify");
// var tsc = ts.createProject("tsconfig.json");

/**
 * load dependencies from package.json
 */
function loadDependencies() {
    let packageJson = JSON.parse(fs.readFileSync("./package.json"));
    let dependencies = [];

    Object.keys(packageJson.dependencies).forEach(function(dependency) {
        dependencies.push(dependency);
    });

    return dependencies;
}

/**
 * Pack a bundle
 */
function packBundle(bundle, bundleName) {
    return bundle
        .on("error", gutil.log)
        .pipe(source(bundleName))
        .pipe(buffer())
        .pipe(sourcemaps.init({loadMaps: true}))
        //.pipe(uglify())
        .pipe(sourcemaps.write());
}

/**
 * Create a bundle with application scripts
 */
function bundleApp() {
    let b = browserify({
        debug: true,
        entries: ['./src/main/js/main.ts'],
        cache: {}, 
        packageCache: {} 
    });

    loadDependencies().forEach(function(dependency) {
        b = b.external(dependency);
    });

    return b.plugin([tsify]).bundle();   
}

/**
 * Create a bundle with external dependencies
 */
function bundleDependencies() {
    let b = browserify({
        debug: false,
        entries: ['./src/main/js/extra.ts'],
        cache: {}, 
        packageCache: {} 
    });

    loadDependencies().forEach(function(dependency) {
        let path = resolve.sync(dependency);

        if (dependency == "zone.js") {
            path = "./node_modules/zone.js/dist/zone.js";
        }
        
        b = b.require(path, { expose: dependency });
    });

    return b.plugin([tsify]).bundle();   
}

/**
 * Generate application javascript
 */
gulp.task("js:app", function() {
    return packBundle(bundleApp(), "keepitsafe.js")
        .pipe(gulp.dest("./target/dist"))
        .pipe(browserSync.stream());
});

/**
 * Generate dependencies` javascript
 */
gulp.task("js:dep", function() {
    return packBundle(bundleDependencies(), "dep.js")
        .pipe(gulp.dest("./target/dist"))
        .pipe(browserSync.stream());
});

gulp.task("js", ["js:app", "js:dep"]);

/**
 * Generate css
 */
gulp.task("css", function() {
    return gulp.src("./src/main/less/main.less")
        .pipe(sourcemaps.init())
        .pipe(less())
        .pipe(cleanCss())
        .pipe(sourcemaps.write())
        .pipe(rename("keepitsafe.css"))
        .pipe(gulp.dest("./target/dist"))
        .pipe(browserSync.stream());
});

/**
 * Generate index.html
 */
gulp.task("html", function() {
    return gulp.src("./src/main/js/**/*.html")
        .pipe(gulp.dest("./target/dist"))
        .pipe(browserSync.stream());
});

/**
 * Start server
 */
gulp.task("start", ["js", "css", "html"], function() {
    browserSync.init({
        server: {
            baseDir: "./target/dist"
        }
    });

    gulp.watch([ "src/main/js/**/*.ts" , "!./src/main/js/extra.ts"], {cwd: "./"} , ["js:app"]);
    gulp.watch([ "./package.json", "./src/main/js/extra.ts" ], {cwd: "./"}, ["js:dep"]);
    gulp.watch("src/main/less/**/*.less", {cwd: "./"}, ["css"]);
    gulp.watch("src/main/js/**/*.html", {cwd: "./"}, ["html"]);
});
