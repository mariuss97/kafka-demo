

AMaster: 100.0.0.1
KMaster: 100.0.0.2
KWorker: 100.0.0.3
Jenkins: 100.0.0.4 (admin/admin)

Producer: 8090
Consumer: 8091
Kafka: 9092
Actuator enabled

Node Port Range: 30000 - 32767

Consumer-Service: 30001
Producer-Service: 30000
Kafdrop: 30900

Restart cluster:kubectl drain --ignore-daemonsets --force --delete-local-data <node-hostname>
Danach: kubectl uncordon <node-hostname>

Install Kafka via Helm:
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install my-release bitnami/kafka

helm status my-release
helm pull bitnami/kafka
tar -xvzf yyy.tgz

Kafka can be accessed by consumers via port 9092 on the following DNS name from within your cluster:

    my-release-kafka.default.svc.cluster.local

Each Kafka broker can be accessed by producers via port 9092 on the following DNS name(s) from within your cluster:

    my-release-kafka-0.my-release-kafka-headless.default.svc.cluster.local:9092

To create a pod that you can use as a Kafka client run the following commands:

    kubectl run my-release-kafka-client --restart='Never' --image docker.io/bitnami/kafka:2.8.1-debian-10-r73 --namespace default --command -- sleep infinity
    kubectl exec --tty -i my-release-kafka-client --namespace default -- bash

    PRODUCER:
        kafka-console-producer.sh \

            --broker-list my-release-kafka-0.my-release-kafka-headless.default.svc.cluster.local:9092 \
            --topic test

    CONSUMER:
        kafka-console-consumer.sh \

            --bootstrap-server my-release-kafka.default.svc.cluster.local:9092 \
            --topic test \
            --from-beginning
			
			
PV "Terminating"-State: https://stackoverflow.com/a/59900177/3649685

Get generated nginx conf because of Ingress Resource: kubectl exec -it nginx-ingress-5b467c7d7-kbxzv -n "nginx-ingress" -- /bin/bash
check /etc/nginx/conf.d - Folder


---
METALLB: LoadBalancer ermoeglichen fuer Bare-Metal-Deployments (https://blog.dbi-services.com/setup-an-nginx-ingress-controller-on-kubernetes/)
In ConfigMap IP-Range innerhalb Cluster angeben
Service dann als Type LoadBalancer
Es wird automatisch "External-IP" aus Range vergeben
Ansteuerung funktioniert dann von außerhalb über IP:port(aus YAML)

Schnelles bearbeiten: kubectl edit deploy ingress-nginx-controller -n ingress-nginx


http://producer.mariusdev.net/hallo
http://kafdrop.mariusdev.net/hallo
http://kafkademo.mariusdev.net/kafkaconsume/hallo
http://kafkademo.mariusdev.net/kafkaproduce/hallo
http://prometheus.mariusdev.net/graph
http://grafana.mariusdev.net/ (admin/dspdsp)
http://clickhouse.mariusdev.net/
harbor: https://172.16.8.11:30099/ (admin/Harbor12345)
SCDF Dashboard: https://scdf.mariusdev.net/dashboard
Kibana: https://kibana.mariusdev.net/

Ingress muss folgende Annotation haben:
kubernetes.io/ingress.class: nginx

Login in Private Registry: docker login core.harbor.domain --username=admin --password Harbor12345


Token k8s Dashboard: eyJhbGciOiJSUzI1NiIsImtpZCI6IkJoRkVZY3hmTW1LVkh1ajI0bHF4dndkeFhxcU9SZDZEWTJJNnlMOFBuYjgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRhc2hib2FyZC1hZG1pbi1zYS10b2tlbi10djVsayIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJkYXNoYm9hcmQtYWRtaW4tc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI4OTZmYzg0YS01MzY0LTQ5NWEtYTE5OC1jNWY1NmYwMjlmY2IiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDpkYXNoYm9hcmQtYWRtaW4tc2EifQ.Q5RXQfTjWBawMTZ3slavkJna-ybPCKvEGGGi4zSkEB53aBcvksdjxZn8yC7fZBgUxFk5PKB-odKLJ2l-LK6DKdJXDNhPgDS2OJKpB-NCvr9S5cMnKZ48vhSD9e-NIxBAZTl8tbv-76jl-FksNY-Eoh-fDnC09KOcUpVDDK2iftVNEpVDuqMr5RMdS1rocJZMAAbbx_pZxuAThB9E2CPjafjbOjJp4DxfZwEpK-Im4LFVryhx3ixX1oLARP60NhOJimO6P6SYzLs3L2RENKZ6WxsJLz-ghWl9LHR8YZzPTvFMoO4UwgdaETqsntGpk047gerLuu5e5oF_g3UCLiBv2Q


#DOCKER
- Harbor: https://172.16.8.11:30099
username: admin
password: Harbor12345

Zugriff via Windows Docker: docker push 172.16.8.11:30099/library/nginx:v3
-Zertifikat (.crt) installieren via Doppelklick
-Docker Daemon neustarten

#Harbor Notes
-Installation via Helm: 
helm repo add harbor https://helm.goharbor.io
helm install my-harbor harbor/harbor -n harbor-system --set expose.type="nodePort" --set expose.nodePort.ports.https.nodePort=30099 --set expose.tls.auto.commonName="100.0.0.2" --set externalURL="https://100.0.0.2:30099" --create-namespace
-Zertifikat aus UI installieren mit update-ca-certificates (https://manuals.gfi.com/en/kerio/connect/content/server-configuration/ssl-certificates/adding-trusted-root-certificates-to-the-server-1605.html)
-Nodes neustarten: https://www.ibm.com/support/pages/steps-follow-while-restarting-kubernetes-and-docker-infosphere-information-server-installations
-Auf allen Nodes: sudo docker login https://172.16.8.11:30099/

kubectl create secret docker-registry regcred --docker-server=100.0.0.2:30099 --docker-username=admin --docker-password=Harbor12345 --docker-email=test@mail.de

sudo cp ca.crt /usr/local/share/ca-certificates/
sudo update-ca-certificates

#Clickhouse
Erreichbar via Konfiguration in DBeaver: http://clickhouse.mariusdev.net/ Port 80


#K8s Cheatsheet
Force deletion of Pod: kubectl delete pod/scdf-server-5c6fbc77c8-jdxp5 --grace-period=0 --force
Delete all Pods in Namespace: kubectl delete --all pods --namespace=foo
Delete all Terminated/Evicted Pods in Namespace "harbor-system": kubectl get pod -n harbor-system | egrep -i  'Evicted|Terminated' | awk '{print $1}' | xargs kubectl delete pod -n harbor-system
Delete unused replicasets: kubectl delete $(kubectl get all | grep replicaset.apps | grep "0         0         0" | cut -d' ' -f 1)
Default Namespace: kubectl get pod | egrep -i  'Evicted|Terminated' | awk '{print $1}' | xargs kubectl delete pod 
Re-deploy ohne Veränderung von Service: kubectl -n monitoring rollout restart deployment grafana
List env-Vars of Pod: kubectl exec -it pod/grafana-6d9d65bb75-l5w4x -n monitoring -- printenv
Change namespace: kubens monitoring
Untaint master: kubectl taint nodes --all node-role.kubernetes.io/master-
Run pod with ping and nslookup: kubectl run -it --rm --restart=Never --image=infoblox/dnstools:latest dnstools
Decode base-64-encoded secret: echo "cm9vdA=="| base64 --decode
Ping with ServiceName: curl sundayeveningchillig-clicksink100.scdf.svc.cluster.local:8080/actuator/health
((Erreichen von Services durch Pods aus anderen Namespaces: siehe /kafka-demo/k8s/clickhouse/clickhouseDB-service-in-scdf-ns.yml (Type: ExternalName, https://stackoverflow.com/a/44329470/3649685)

#Bash cheatsheet
Ctrl + R: backward search
Ctrl + Shift + R: move back after 
Curl and Pretty-Format-JSON: curl 10.233.34.215:8080/actuator/configprops | python -mjson.tool > out.json
Vim-Search: /suchwort , dann Enter und mit n vorwaertssuche, N rueckwaertssuche

#Install Helm Charts
1. Herunterladen von Package via ArtifactRepo: z.B. https://artifacthub.io/packages/helm/elastic/kibana
2. Install-Button auf Webseite (unten rechts klein: DOwnload)
3. In Verzeichnis dann values.yaml anpassen
4. Installieren des Charts in neuem elk-Namespace: helm install filebeat . -n elk --create-namespace

Helm Upgrade (hier am Beispiel Kafka Topic-Deletion zusaetzlich erlauben): helm upgrade --reuse-values --set deleteTopicEnable=true my-release bitnami/kafka

#Install Kubens
sudo vim /etc/apt/sources.list

#for kubectlx
deb [trusted=yes] http://ftp.de.debian.org/debian buster main

sudo apt-get update
sudo apt install kubectx


#Kafdrop
helm install --set kafka.enabled=false --set kafka.bootstrapServers=my-release-kafka-headless.default.svc.cluster.local:9092 ktool rhcharts/kafdrop

# ingress-nginx
https://kubernetes.github.io/ingress-nginx/deploy/#bare-metal-clusters


kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/baremetal/deploy.yaml

In nginx-controller-deployment ergänzen:
template:
  spec:
    hostNetwork: true (siehe Abschnitt "via the Host Network" -> https://kubernetes.github.io/ingress-nginx/deploy/baremetal/)
	
## Use custom cert and not generate new at every startup of ingress-controller
https://kubernetes.github.io/ingress-nginx/user-guide/tls/#tls-secrets
	
# ELK
Bei Filebeat: aus DaemonSet ressource-limits und requests entfernen	

# Grafana
Permission denied: Rechte ändern von gemountetem PV-Ordner: sudo chown -R 472:472 /data/pv/grafana-pv/

# SCDF
Mysql: root/yourpassword

kubectl create -f src/kubernetes/server/server-rolebinding.yaml (v1 statt v1beta)

Deployte Apps sollen nur wenige Ressourcen requesten:
kubectl edit deployment.apps/skipper

- name: SPRING_CLOUD_SKIPPER_SERVER_PLATFORM_KUBERNETES_ACCOUNTS_DEFAULT_REQUESTS_MEMORY
          value: 1Mi
- name: SPRING_CLOUD_SKIPPER_SERVER_PLATFORM_KUBERNETES_ACCOUNTS_DEFAULT_REQUESTS_CPU
          value: 1m

Streamen in eigenes, selbst definiertes Topic (DSL-Format): opcsrc100 > :myOwnTopic

#SCDF Proxy
wget 10.233.63.175:8080/metrics/connected
wget 10.233.63.175:8080/metrics/proxy

STAND: komische Logs bei SCDF, Permissiondenied ...

# Actuator
Change Loglevel during Runtime: curl -X "POST" "10.233.34.215:8080/actuator/loggers/de.marius" -H "Content-Type: application/json; charset=utf-8"   -d $'{ "configuredLevel": "WARN" }'
Get actual Log-Config: curl 10.233.34.215:8080/actuator/loggers | python -mjson.tool > loggers_after.json

# Install Jenkinsserver
apt-get update Problem:
sudo apt install ca-certificates

Permission denied gradlew: git update-index --chmod=+x gradlew

Restart after groupadd jenkins to dockergroup

# Jib
push to reg: gradle jib
push to docker daemon: gradle jibDockerBuild
build image as tar: gradle jibBuildTar

Cert in Java: https://stackoverflow.com/questions/57812972/failure-on-push-to-docker-registry-using-mvn-compile-jibbuild

# Gradle
Show all Tasks: .\gradlew tasks --all

docker run -d --env PORT=4334 --env RESOURCEPATH=/UA/MyLittleServer --env OPCUAHOSTNAME=localhost -p 4334:4334 core.harbor.domain/library/mylittleopcua

Run project aus Root-Verzeichnis: .\gradlew clickhouseSinkDemo:bootRun

Show project structure (Aufruf aus Root-Verzeichnis own-opcua:  .\gradlew -q projects

Root project 'own-opcua'
+--- Project ':clickhouseSinkDemo'
+--- Project ':commons'
\--- Project ':opcuaDemo'

# Install zsh
https://stackoverflow.com/a/25763071/3649685

#Notes
SCDF erlaubt Updates (gleicher AppName, anderer Tag), im Stream kann dann auf neue Version geswitcht werden
Dabei treten keine Verluste von Werten auf (nahtloser Übergang)
- Altes Programm läuft weiter, neues joint der gleichen Consumer-Group und bekommt dann Partition assigned
- Auch in DB (für Fall clicksink) landen nur alle Werte einmal (keine Verluste, keine Dopplungen)
