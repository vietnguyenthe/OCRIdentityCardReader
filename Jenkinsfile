pipeline {

    agent { docker { image 'maven:3.3.3'}}

    stages {

        stage('Check') {
            steps {
                sh 'mvn --version'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -DskipTests clean package'
            }
        }

        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
