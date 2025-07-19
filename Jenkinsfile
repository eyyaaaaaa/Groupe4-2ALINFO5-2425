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

            // Send email on success
            emailext (
                subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Hi Team,</p>
                    <p>The build #${env.BUILD_NUMBER} for project <b>${env.JOB_NAME}</b> succeeded.</p>
                    <p>SonarQube report is available at: <a href="http://localhost:9000/dashboard?id=com.example%3AFoyer">SonarQube Dashboard</a></p>
                    <p>Regards,<br/>Jenkins</p>
                """,
                mimeType: 'text/html',
                to: 'eya.chtourou1@gmail.com' // <-- replace with your email(s)
            )
        }
        failure {
            echo '❌ Build or analysis failed'

            // Send email on failure
            emailext (
                subject: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Hi Team,</p>
                    <p>The build #${env.BUILD_NUMBER} for project <b>${env.JOB_NAME}</b> failed.</p>
                    <p>Please check Jenkins logs and SonarQube for details.</p>
                    <p>Regards,<br/>Jenkins</p>
                """,
                mimeType: 'text/html',
                to: 'eya.chtourou1@gmail.com' // <-- replace with your email(s)
            )
        }
    }
}
