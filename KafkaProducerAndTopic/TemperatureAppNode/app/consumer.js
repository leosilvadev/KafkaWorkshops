/**
 * 
 * Based on node-rdkafka: https://github.com/Blizzard/node-rdkafka
 * Possible configurations: https://github.com/edenhill/librdkafka/blob/v1.6.1/CONFIGURATION.md
 * 
 */

const { v4: uuidv4 } = require('uuid');
const Kafka = require('node-rdkafka');
const stream = Kafka.KafkaConsumer.createReadStream({
    'client.id': 'temperature-nodejs-consumer',
    'group.id': 'temperature-consumer',
    'metadata.broker.list': 'localhost:9092'
}, {}, { topics: ['temperatures'] });

stream.on('data', (data) => {
    console.log(data.key.toString());
    console.log(data.value.toString());
});