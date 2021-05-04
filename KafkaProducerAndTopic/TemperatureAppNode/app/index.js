/**
 * 
 * Based on node-rdkafka: https://github.com/Blizzard/node-rdkafka
 * Possible configurations: https://github.com/edenhill/librdkafka/blob/v1.6.1/CONFIGURATION.md
 * 
 */

const { v4: uuidv4 } = require('uuid');
const Kafka = require('node-rdkafka');
const producer = new Kafka.Producer({
    'client.id': 'temperature-nodejs',
    'metadata.broker.list': 'localhost:9092',
    'acks': '0'
});


/**
 * 
 * Create the topic:
 * kafka-topics.sh --create --topic temperatures --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
 *
 * Test the topic, sending data manually:
 * kafka-console-producer.sh --bootstrap-server localhost:9092 --topic temperatures --property "parse.key=true" --property "key.separator=,"
 *
 * Test if data was sent:
 * kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic temperatures --property "print.key=true" --value-deserializer "org.apache.kafka.common.serialization.DoubleDeserializer" --from-beginning
 *
 * If everything works, run this main method (or try...) or delete and create the topic again
 *
 * To delete: kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic temperatures
 *
 * */

const args = process.argv;
console.log(args)
if (args.length != 3) {
    console.error("Only one argument must be provided: number of messages to produce");
    process.exit(1);
}

const numberOfRecords = args[2];

producer.connect();

const sensorId = uuidv4();

const doubleAsBuffer = value => {
    /**
     * All this code to just publish as Double... not nice, don't do it!
     */
    const buffer = Buffer.alloc(8);
    buffer.writeDoubleBE(20.10);
    return buffer;
}

producer.on('ready', () => {
    let recordsToProduce = numberOfRecords;
    while (recordsToProduce > 0) {
        const valueBuffer = doubleAsBuffer(20.10);
        producer.produce('temperatures', null, valueBuffer, Buffer.from(sensorId), Date.now());
        recordsToProduce--;
    }
});

producer.on('event.error', err => {
    console.error('Error from producer');
    console.error(err);
});

producer.on('delivery-report', (err, report) => {
    // Report of delivery statistics here:
    //
    console.log(report);
});

producer.setPollInterval(100);