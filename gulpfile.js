
var gulp = require("gulp");
var ts = require("gulp-typescript");
var less = require("gulp-less");
var sourcemaps = require("gulp-sourcemaps");
var uglify = require("gulp-uglify");
var cleanCss = require("gulp-clean-css");
var rename = require("gulp-rename");
var browserSync = require("browser-sync").create();
var webpack = require("webpack-stream");

var tsc = ts.createProject("tsconfig.json");

/**
 * Generate javascript
 */
gulp.task("js", function() {
    return gulp.src("app/main.ts")
            .pipe(webpack(require("./webpack.config.js")))
            //.pipe(sourcemaps.init({loadMaps: true}))
            //.pipe(uglify())
            //.pipe(sourcemaps.write())
            //.pipe(rename("keepitsafe.js"))
            .pipe(gulp.dest("build/app"))
            .pipe(browserSync.stream());
});

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
gulp.task("index", ["js", "css"], function() {
    return gulp.src("index.html")
            .pipe(gulp.dest("build"))
            .pipe(browserSync.stream());
});

/**
 * Start server
 */
gulp.task("start", ["js", "css", "index"], function() {
    browserSync.init({
        server: {
            baseDir: "build/"
        }
    });

    gulp.watch("app/**/*.ts", ["js"]);
    gulp.watch("css/less/**/*.less", ["css"]);
    gulp.watch("index.html", ["index"]);//, browserSync.reload]);
});
