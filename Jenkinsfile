pipeline {
  agent {
    label 'jdk21 && maven'
  }
  environment {
    CODECOV_TOKEN = credentials('groupman-api-codecov-token')
    TEST = true
    DEPLOY = true
    SITE = true
    CLEAN = true
  }
  tools {
    jdk 'jdk21'
    maven 'm3'
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '8', artifactNumToKeepStr: '8'))
  }
  stages {
    stage('Tools') {
      steps {
        sh 'java -version'
        sh 'mvn -B --version'
      }
    }
    stage('Test') {
      when {
        environment name: 'TEST', value: 'true'
      }
      steps {
        sh 'mvn -B clean test'
      }
      // Currently, there are no tests
      //post {
      //  always {
      //    junit '**/surefire-reports/*.xml'
      //    recordCoverage(
      //        tools: [[parser: 'JACOCO', pattern: '**/coverage-reports/*.exec']],
      //        sourceCodeRetention: 'LAST_BUILD'
      //    )
      //  }
      //}
    }
    stage('Deploy') {
      when {
        allOf {
          environment name: 'DEPLOY', value: 'true'
          anyOf {
            branch 'develop'
            branch 'main'
          }
        }
      }
      steps {
        sh 'mvn -B -P build-system,deploy clean deploy'
      }
    }
    stage('Site') {
      when {
        allOf {
          environment name: 'SITE', value: 'true'
          anyOf {
            branch 'develop'
            branch 'main'
          }
        }
      }
      steps {
        sh '''
          mvn -B -P build-system,gh-pages-site site site:stage
          git clone -b gh-pages git@github.com:bremersee/groupman-api.git target/gh-pages
          cp -rf target/staging/* target/gh-pages
          git -C target/gh-pages add .
          git -C target/gh-pages commit -m "Maven site"
          git -C target/gh-pages push
          rm -rf target/gh-pages
        '''
      }
      // Currently, there are no tests
      //post {
      //  always {
      //    sh 'curl -s https://codecov.io/bash | bash -s - -t ${CODECOV_TOKEN}'
      //  }
      //}
    }
    stage('Clean') {
      when {
        environment name: 'CLEAN', value: 'true'
      }
      steps {
        sh 'mvn -B clean'
      }
    }
  }
}