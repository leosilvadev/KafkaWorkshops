# How to run

Start the infra (Zookeeper, Kafka and akhq for UI)
```
docker-compose up
```

Run the Producer application
```
./gradlew runClass -Pmode=producer
```