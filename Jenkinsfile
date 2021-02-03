pipeline {
  agent any
  stages {
    stage('Test') {
      parallel {
        stage('Test') {
          steps {
            echo 'Hola mundo!'
          }
        }

        stage('') {
          steps {
            echo 'Other Message'
          }
        }

      }
    }

    stage('') {
      steps {
        echo 'Another one'
      }
    }

  }
}