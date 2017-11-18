pipeline {
  agent any
  stages {
    stage('Prepare') {
      steps {
        git(url: 'https://github.com/chhil/ISO8583.git', branch: 'develop')
      }
    }
    stage('Build') {
      steps {
        bat(script: 'C:\\portableapps\\gradle-4.3.1\\bin\\gradle.bat', returnStatus: true)
      }
    }
    stage('Test') {
      steps {
        bat 'C:\\portableapps\\gradle-4.3.1\\bin\\gradle.bat test'
      }
    }
  }
}