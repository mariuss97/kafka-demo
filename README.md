Permission denied gradlew: git update-index --chmod=+x gradlew

Node1: 100.0.0.2
Node2: 100.0.0.3

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
http://prometheus.mariusdev.net/prometheus
http://grafana.mariusdev.net/
https://core.harbor.domain/

Ingress muss folgende Annotation haben:
kubernetes.io/ingress.class: nginx

Login in Private Registry: docker login core.harbor.domain --username=admin --password Harbor12345
username: admin
password: Harbor12345

Token k8s Dashboard: eyJhbGciOiJSUzI1NiIsImtpZCI6IkJoRkVZY3hmTW1LVkh1ajI0bHF4dndkeFhxcU9SZDZEWTJJNnlMOFBuYjgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRhc2hib2FyZC1hZG1pbi1zYS10b2tlbi10djVsayIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJkYXNoYm9hcmQtYWRtaW4tc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI4OTZmYzg0YS01MzY0LTQ5NWEtYTE5OC1jNWY1NmYwMjlmY2IiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDpkYXNoYm9hcmQtYWRtaW4tc2EifQ.Q5RXQfTjWBawMTZ3slavkJna-ybPCKvEGGGi4zSkEB53aBcvksdjxZn8yC7fZBgUxFk5PKB-odKLJ2l-LK6DKdJXDNhPgDS2OJKpB-NCvr9S5cMnKZ48vhSD9e-NIxBAZTl8tbv-76jl-FksNY-Eoh-fDnC09KOcUpVDDK2iftVNEpVDuqMr5RMdS1rocJZMAAbbx_pZxuAThB9E2CPjafjbOjJp4DxfZwEpK-Im4LFVryhx3ixX1oLARP60NhOJimO6P6SYzLs3L2RENKZ6WxsJLz-ghWl9LHR8YZzPTvFMoO4UwgdaETqsntGpk047gerLuu5e5oF_g3UCLiBv2Q
