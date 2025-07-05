pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
        sonarScanner 'SonarScanner'  // Name from Global Tool Config
    }

    environment {
        SONARQUBE = 'sonarqube'  // Name from Jenkins Configure System
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE) {
                    sh 'mvn sonar:sonar'
                }
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
            echo '✅ Build and SonarQube analysis succeeded!'
        }
        failure {
            echo '❌ Build or SonarQube analysis failed.'
        }
    }
}
