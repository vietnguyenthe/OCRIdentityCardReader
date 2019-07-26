pipeline {

    agent any

    stages {

        stage('Build') {
            agent {
                docker {
                    image 'maven: 4.0.0'
                }
            }

            steps {
                sh 'mvn --version'
            }
        }
    }
}
