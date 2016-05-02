var gulp = require('gulp');
var browserSync = require('browser-sync').create();
var proxy = require('http-proxy-middleware');
var inject = require('gulp-inject');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var less = require('gulp-less');
var path = require('path');

gulp.task('server', ['less', 'local-dependency', 'watch'], function() {
	var config = {
		baseDir: './src/main/webapp',
		routes: { '/node_modules': 'node_modules' },
		middleware: [
			proxy('/api', {target: 'http://localhost:8090'})
		],
		port: 8080
	}

	browserSync.init({ server : config });
});

gulp.task('local-dependency', function () {
  	var sources = gulp.src(['./src/main/webapp/**/*.js', './src/main/webapp/**/*.css'], {read: false});

  	return gulp.src('./src/main/webapp/index.html')
  			   .pipe(inject(sources, { ignorePath: 'src/main/webapp', 
  			 	                       relative: true }))
  			   .pipe(gulp.dest('./src/main/webapp'));
});

gulp.task('less', function () {
  	return gulp.src('./src/main/webapp/keepitsafe.less')
    	       .pipe(less())
               .pipe(gulp.dest('./src/main/webapp'));
});

gulp.task('watch', function() {
	gulp.watch(['./src/main/webapp/keepitsafe.less'], [less]);
	// gulp.watch(['./src/main/webapp/**/*.js', './src/main/webapp/**/*.css'], ['local-dependency']);
});

gulp.task('default', ['server']);
