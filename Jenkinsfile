
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

        
stage('Diagnose Docker') {
    steps {
        bat '''
            where.exe docker
            docker --version
            docker compose version
            docker info
        '''
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
                artifacts: 'test-output/ExtentReport.html',
                allowEmptyArchive: true
            )

            bat 'docker compose -f compose.yaml down --remove-orphans'
        }
    }
}
