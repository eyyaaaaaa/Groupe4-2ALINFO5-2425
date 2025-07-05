pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        SONARQUBE = 'sonarqube' // Must match the SonarQube server name you added in Jenkins config
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE}") {
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
            echo '❌ Build or analysis failed.'
        }
    }
}
