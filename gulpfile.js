
var gulp = require("gulp");
var ts = require("gulp-typescript");
var less = require("gulp-less");
var sourcemaps = require("gulp-sourcemaps");
var uglify = require("gulp-uglify");
var cleanCss = require("gulp-clean-css");
var rename = require("gulp-rename");
var browserSync = require("browser-sync").create();
var webpack = require("webpack-stream");
var browserify = require("browserify");
var watchify = require("watchify");
var tsify = require("tsify");
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var gutil = require('gulp-util');
var tsc = ts.createProject("tsconfig.json");
var fs = require("fs");

/**
 * load dependencies from package.json
 */
function loadDependencies() {
    let packageJson = JSON.parse(fs.readFile("./package.json"));
    let dependencies = [];

    Object.keys(packageJson.dependencies).forEach(function(dependency) {
        dependencies.push(dependency);
    });

    return dependencies;
}

/**
 * Generate javascript
 */
var js = watchify(browserify({
    debug: true,
    entries: ['app/main.ts'],
    cache: {}, 
    packageCache: {},
    plugin: [tsify]
}));

function bundleJs() {
    return js.bundle()
            .pipe(source('bundle.js'))
            .pipe(buffer())
            .pipe(sourcemaps.init({loadMaps: true}))
            .pipe(uglify())
            .pipe(sourcemaps.write())
            .pipe(rename("keepitsafe.js"))
            .pipe(gulp.dest("build/app"))
            .pipe(browserSync.stream());
}
gulp.task("js:watchify", function() {
    return bundleJs();
});

gulp.task("js:browserify", function() {
    return browserify({
                debug: true,
                entries: ['app/main.ts'],
                cache: {}, 
                packageCache: {} 
            })
            .plugin([tsify])
            .bundle()
            .pipe(source('bundle.js'))
            .pipe(buffer())
            .pipe(sourcemaps.init({loadMaps: true}))
            .pipe(uglify())
            .pipe(sourcemaps.write())
            .pipe(rename("keepitsafe.js"))
            .pipe(gulp.dest("build/app"))
            .pipe(browserSync.stream());
});

var myConfig = Object.create(require("./webpack.config.js"));
gulp.task("js:webpack", function() {
    return gulp.src("app/main.ts")
            .pipe(webpack(myConfig))
            .pipe(sourcemaps.init({loadMaps: true}))
            .pipe(uglify())
            .pipe(sourcemaps.write())
            .pipe(rename("keepitsafe.js"))
            .pipe(gulp.dest("build/app"))
            .pipe(browserSync.stream());
});

gulp.task("js", ["js:watchify"]);

/**
 * Generate css
 */
gulp.task("css", function() {
    return gulp.src("css/less/main.less")
            .pipe(sourcemaps.init())
            .pipe(less())
            .pipe(cleanCss())
            .pipe(sourcemaps.write())
            .pipe(rename("keepitsafe.css"))
            .pipe(gulp.dest("build/"))
            .pipe(browserSync.stream());
});

/**
 * Generate index.html
 */
gulp.task("html", function() {
    return gulp.src("./**/*.html")
            .pipe(gulp.dest("build"))
            .pipe(browserSync.stream());
});

/**
 * Start server
 */
gulp.task("start", ["js", "css", "html"], function() {
    browserSync.init({
        server: {
            baseDir: "build/"
        }
    });

    //js.on('update', bundleJs);
    //b.on('log', gutil.log);
    gulp.watch("app/**/*.ts", ["js"]);
    gulp.watch("css/less/**/*.less", ["css"]);
    gulp.watch("index.html", ["html"]);//, browserSync.reload]);
});
