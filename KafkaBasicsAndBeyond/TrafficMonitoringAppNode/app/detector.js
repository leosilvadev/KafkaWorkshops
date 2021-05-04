class Detection {
    constructor(sensorId, id, image, speed) {
        this.sensorId = sensorId;
        this.id = id;
        this.image = image;
        this.speed = speed;
    }
}

class Detector {
    constructor(sensorId) {
        this.sensorId = sensorId;
        this.started = false;
    }

    detect() {
        const id = `${this.sensorId}_${Date.now()}`;
        const speed = Math.random(150) * 100;
        return new Detection(this.sensorId, id, Buffer.of(), speed);
    }

    start(onDetect) {
        if (this.started) {
            console.warn('Detector is already running');
            return;
        }

        this.started = true;
        this.run(onDetect);
    }

    run(onDetect) {
        if (this.started) {
            setTimeout(() => {
                onDetect(this.detect());
                this.run(onDetect);
            }, Math.random(100) * 1000);
        }
    }
}

module.exports = sensorId => new Detector(sensorId)