def call() {
pipeline {
    agent any
    // options {
    //     timeout(time: 1, unit: 'MINUTES') 
    //     }
    parameters {
	    string(name: 'hello', defaultValue: 'divya', description: 'name is divya') 
            booleanParam(name: 'colour-black', defaultValue: 'true')
        }
    stages {
		
        stage('Build') {
            steps {
			   
               sh "cd sfg-pet-clinic && mvn clean install"
            }
        }
	    stage('Build docker image') {
			steps{
				sh '''docker build --tag jira:diya1 .
					'''
				}
			}
        stage('master-branch-stuff') {
                when {
                    branch 'newbranch*'
                }
                steps {
                    echo 'run this stage - ony if the branch = master branch'
                }
        }
		stage('Clone additional repository') {
                steps {
                    dir('newdir') {
                        git branch: 'main', credentialsId: '499d84ac-7790-4571-998a-2f3eed2b292e', url: 'https://github.com/divz141/hello-world.git'
                    }
                }
		}
        stage('Build docker image second repo') {
			steps {
				sh '''cd newdir
					  docker build --tag jira:diya2 .
				    '''
			}
		}
		
    }
}
}
