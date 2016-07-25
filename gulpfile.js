var path   = require('path');
var gulp   = require('gulp');
var rimraf = require('rimraf');
var npm    = require('gulp-brass-npm');
var brass  = require('gulp-brass');

var pkg, options, rpm;

pkg = require('./package.json');
options = npm.getOptions(pkg);
options.installDir = '/usr/libexec/'+ options.name;
options.service = {
  type        : 'systemd',
  name        : options.name,
  description : options.description,
  exec        : '/usr/bin/cc-web-runner',
  user        : 'cc-web-runner',
  group       : 'cc-web-runner'
};
rpm = brass.create(options);

gulp.task('clean', function () {
  return gulp.src([ rpm.buildDir, '*.tgz', '*.log' ], { read: false })
  .pipe(brass.util.stream(function (file, done) {
    this.push(file);
    rimraf(file.path, done);
  }));
});

gulp.task('setup', [ 'clean' ], rpm.setupTask());

gulp.task('files', [ 'setup' ], function () {
  return gulp.src('target/'+ options.name +'-standalone-'+ options.version +'.jar')
  .pipe(gulp.dest(path.join(rpm.buildRoot, options.installDir)))
  .pipe(brass.util.stream(function (file, done) {
    if (file.relative == options.service.exec) {
      file.attr = [ '0775', 'root', 'root' ];
    }

    done(null, file);
  }))
  .pipe(rpm.files());
});

gulp.task('service', [ 'setup' ], function () {
  return gulp.src('assets/unit.service')
  .pipe(brass.util.template(options.service))
  .pipe(brass.util.rename(options.service.name +'.service'))
  .pipe(gulp.dest(path.join(rpm.buildRoot, '/lib/systemd/system')))
  .pipe(rpm.files());
});

gulp.task('binary', [ 'setup' ], function () {
  return gulp.src('assets/cc-web-runner')
  .pipe(brass.util.template(options))
  .pipe(gulp.dest(path.join(rpm.buildRoot, '/usr/bin')))
  .pipe(rpm.files());
});

gulp.task('binaries', [ 'files' ], npm.binariesTask(pkg, rpm));

gulp.task('spec', [ 'files', 'binary', 'service' ], function () {
  return gulp.src('assets/spec')
  .pipe(rpm.spec())
  .pipe(gulp.dest(rpm.buildDir_SPECS));
});

gulp.task('build', [ 'spec' ], rpm.buildTask());

gulp.task('default', [ 'build' ], function () {
  console.log('build finished');
});
