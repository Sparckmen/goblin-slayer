pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Tests') {
            when {
                branch 'feature/*'
            }
            steps {
                bat 'mvn test'
            }
        }
        stage('Static Analysis') {
            when {
                branch 'develop'
            }
            steps {
                bat 'mvn checkstyle:check'
            }
        }
        stage('Report') {
            when {
                branch 'feature/*'
            }
            steps {
                junit testResults: '**/surefire-reports/*.xml'
                jacoco(
                        execPattern: '**/target/*.exec',
                        classPattern: '**/target/classes/',
                        sourcePattern: '**/src/main',
                )
            }
        }
        stage('Install') {
            steps {
                bat 'mvn install'
            }
        }
        stage('Publish') {
            steps {
                bat 'copy main\\target\\main-1.0-SNAPSHOT-jar-with-dependencies.jar C:\\main-1.0.jar'
            }
        }
    }
}