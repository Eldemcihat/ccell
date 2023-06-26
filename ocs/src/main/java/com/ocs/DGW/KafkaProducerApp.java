package com.ocs.DGW;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerApp {
    private static final String TOPIC = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {

            Client client = new Client();
            while (true) {

                String data = client.generateData();

                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, data);
                producer.send(record);

                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {

            producer.close();
        }
    }
}
