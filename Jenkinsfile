pipeline {

    agent any

    stages {

        stage('Check') {
            agent { docker { image 'maven:3.3.3'}}
            steps {
                sh 'mvn --version'
            }
        }

        stage('Build') {
            agent { docker { image 'maven:3.3.3'}}
            steps {
                sh 'mvn -DskipTests clean package'
            }
        }
    }
}
