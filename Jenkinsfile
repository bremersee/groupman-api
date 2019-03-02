pipeline {
  agent {
    label 'maven'
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean compile'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Deploy') {
      steps {
        sh 'mvn deploy'
      }
    }
    stage('Site') {
      steps {
        sh 'mvn site site:deploy'
      }
    }
  }
}