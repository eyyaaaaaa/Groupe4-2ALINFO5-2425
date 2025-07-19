pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        SONARQUBE = 'sonarqube'
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
                    // Use triple quotes to avoid problems with multiline string and backslash
                    sh '''
                        mvn sonar:sonar -Dsonar.projectKey=com.example:Foyer \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco-report/jacoco.xml
                    '''
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
            echo '✅ Build, Test, and SonarQube analysis succeeded'
        }
        failure {
            echo '❌ Build or analysis failed'
        }
    }
}
