pipeline {
  agent {
    label 'maven'
  }
  tools {
    jdk 'jdk8'
    maven 'm3'
  }
  stages {
    stage('Tools') {
      steps {
        sh 'java -version'
        sh 'mvn -B --version'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn -B test'
      }
    }
    stage('Deploy') {
      when {
        anyOf {
          branch 'develop'
          branch 'master'
        }
      }
      steps {
        sh 'mvn -B -P deploy deploy'
      }
    }
    stage('Snapshot Site') {
      when {
        branch 'develop'
      }
      steps {
        sh 'mvn -B site-deploy'
      }
    }
    stage('Release Site') {
      when {
        branch 'master'
      }
      steps {
        sh 'mvn -B -P gh-pages-site site site:stage scm-publish:publish-scm'
      }
    }
  }
}