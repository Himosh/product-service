using docker-compose.yml file pull and run kafka container

--for windows

#docker exec -it kafka bash

#cd /usr/bin

--create kafka topics 

#kafka-topics --create --topic product-request-topic --bootstrap-server localhost:9092 --partitions 1 --re
plication-factor 1

#kafka-topics --create --topic product-response-topic --bootstrap-server localhost:9092 --partitions 1 --re
plication-factor 1

