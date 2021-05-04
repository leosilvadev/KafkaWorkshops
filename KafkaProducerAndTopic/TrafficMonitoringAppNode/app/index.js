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
 * - A Detector may not have good internet every time (it may oscile)
 * - Detection rate may be high, depending on the place it is running
 * - Order is very important (a detection may be used for many things)
 * - Data loss is not acceptable
 * - Data duplication should be avoided if possible
 * - Data must be available for at least a month
 * - Consumption of detections is going to be high
 */

const sensorId = uuidv4();
const detector = require('./detector')(sensorId);

detector.start(detection => {
    console.log(JSON.stringify(detection));
});