
pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        

stage('Build and Test with Docker') {
    steps {
        withEnv([
            'DOCKER_CONFIG=C:\\Users\\farhe\\.docker'
        ]) {
            bat 'docker compose -f compose.yaml up --build --abort-on-container-exit --exit-code-from test-runner'
        }
    }
}

		
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'test-output/surefire-reports/*.xml'

            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])

            archiveArtifacts(
                artifacts: 'test-output/**',
                allowEmptyArchive: true
            )

            withEnv(['DOCKER_CONFIG=C:\\Users\\farhe\\.docker']) {
                bat 'docker compose -f compose.yaml down --remove-orphans'
            }
        }
    }
}
