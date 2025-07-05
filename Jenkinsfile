pipeline {
    agent any

    tools {
        maven 'Maven3'  // Make sure you configured this Maven version in Jenkins global tools
        jdk 'Java17'    // Adjust to match Jenkins Java config
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/CharradR/Groupe4-2ALINFO5-2425.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
