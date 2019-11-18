pipeline {
    agent any
    
    stages {
        stage("Git clone") {
            steps {
                //git clone  
                deleteDir()
                echo 'Clone the latest code from the code-base'
                sh 'git clone https://github.com/nareshthota247/issue-tracker.git'       
            }
            
        }
        stage("Testcases") {
            steps {
                //Execute testcases 
                echo 'Execute test cases'
                dir("issue-tracker"){
                    sh 'mvn clean test' 
                }               
            }
            
        }
        stage("Maven Build") {
            steps {
                echo 'Execute Maven Build '
                dir("issue-tracker"){
                    sh 'mvn clean package -Dmaven.test.skip=true '
                }
            }
            
        }
        stage("Docker Build") {            
            steps {
                echo 'Execute Docker Build'
                dir("issue-tracker"){
                    sh "docker build -t issue-tracker:\"${env.BUILD_NUMBER}\" . "
                    //sh 'docker push'
                    echo "Check the Docker image"
                    sh 'docker images'
                }
            }
            
        }
        stage("Deployment") {
            steps {
                dir("issue-tracker"){
                    sh "docker run -d -p 89:8082 issue-tracker:\"${env.BUILD_NUMBER}\""
                }
            }            
        }
      
    }//end stages
}