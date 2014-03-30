module.exports = (grunt) ->
  grunt.initConfig
    pkg: grunt.file.readJSON 'package.json'

    coffee:
      source:
        expand: true
        cwd:  'scripts'
        src:  '**/*.coffee'
        ext:  '.js'
        dest: 'public/static/js'

  grunt.loadNpmTasks 'grunt-contrib-coffee'

  grunt.registerTask 'default', 'coffee'
