Permission denied gradlew: git update-index --chmod=+x gradlew

Producer: 8090
Consumer: 8091
Kafka: 9092
Actuator enabled

Node Port Range: 30000 - 32767

Consumer-Service: 30001
Producer-Service: 30000
Kafdrop: 30900



helm status my-release
helm pull bitnami/kafka

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