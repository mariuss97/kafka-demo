node {
    
    stage('Git Clone') { // for display purposes
        // Get some code from a GitHub repository
         git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/mariuss97/kafka-demo.git'
    }
    
    stage('Gradle Build') {
		
		dir("kafka-consumer"){
		sh './gradlew build'
		}
	   //sh ''' cd kafka-consumer
	   //       ./gradlew build
	   //'''
       

    } 
    
    stage("Docker build and tag"){
        sh 'docker version'
		dir("kafka-consumer"){
		sh 'docker build -t kafka-demo-consumer .'
		}
        //sh 'docker build -t kafka-demo-consumer -f kafka-consumer/Dockerfile .'
        sh 'docker image list'
        sh 'docker tag kafka-demo mariuss97/kafka-demo-consumer:latest'
    } 
    
    stage("Docker Login"){
        withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
            sh 'docker login -u mariuss97 -p $PASSWORD'
        }
    } 
    
    stage("Push Image to Docker Hub"){
        sh 'docker push  mariuss97/kafka-demo-consumer:latest'
    }
    
    stage("SSH Into k8s Server") {
        def remote = [:]
        remote.name = 'K8S master'
        remote.host = '100.0.0.2'
        remote.user = 'vagrant'
        remote.password = 'vagrant'
        remote.allowAnyHosts = true
        
        stage('Put k8s-kafka-demo-consumer-deployment.yml onto k8smaster') {
            sshPut remote: remote, from: 'kafka-consumer/k8s-kafka-demo-consumer-deployment.yml', into: '.'
        }
    
        stage('Deploy spring boot') {
          sshCommand remote: remote, command: "kubectl apply -f k8s-kafka-demo-consumer-deployment.yml"
        }
        
        // Hack to pull new image during creation of new Pods
        stage('Start new Rollout') {
          sshCommand remote: remote, command: "kubectl rollout restart deployment.apps/kafka-demo-consumer"
        }
        
        
    } 
}