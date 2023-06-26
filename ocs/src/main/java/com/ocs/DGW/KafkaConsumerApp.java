package com.ocs.DGW;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import com.ocs.DataAccess.HibernateUtil;
import com.ocs.Models.Subscriber;
import com.ocs.Models.Balance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class KafkaConsumerApp {
    private static final String TOPIC = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerApp.class);
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(TOPIC));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Received message: {}", record.value());

                    // Gelen kaydı işleme al
                    String[] values = record.value().split(" ");

                    if (values.length == 5) {
                        String msisdn = values[0].trim();
                        String service = values[2].trim();
                        double amount = Double.parseDouble(values[4].trim());

                        try (Session session = sessionFactory.openSession()) {
                            Transaction transaction = session.beginTransaction();

                            Query<Subscriber> subscriberQuery = session
                                    .createQuery("FROM Subscriber WHERE msisdn = :msisdn");
                            subscriberQuery.setParameter("msisdn", msisdn);
                            Subscriber subscriber = subscriberQuery.uniqueResult();

                            if (subscriber != null) {

                                int subscriberId = subscriber.getId();

                                Query<Balance> balanceQuery = session
                                        .createQuery("FROM Balance WHERE SubscriberId = :subscriberId",
                                                Balance.class);
                                balanceQuery.setParameter("subscriberId", subscriberId);
                                Balance balance = balanceQuery.uniqueResult();

                                if (balance != null) {

                                    switch (service) {
                                        case "VOICE":
                                            int voiceBalance = balance.getBalanceVoice();
                                            int newVoiceBalance = (int) (voiceBalance - amount);
                                            balance.setBalanceVoice(newVoiceBalance);
                                            break;
                                        case "DATA":
                                            int dataBalance = balance.getBalanceData();
                                            int newDataBalance = (int) (dataBalance - amount);
                                            balance.setBalanceData(newDataBalance);
                                            break;
                                        case "SMS":
                                            int smsBalance = balance.getBalanceSms();
                                            int newSmsBalance = (int) (smsBalance - amount);
                                            balance.setBalanceSms(newSmsBalance);
                                            break;
                                        default:
                                            logger.warn("Invalid service type. msisdn: {}, amount: {}, service: {}",
                                                    msisdn, amount, service);
                                            transaction.rollback();
                                            break;
                                    }

                                    session.update(balance);
                                    transaction.commit();

                                    logger.info(
                                            "Subscriber balance updated. msisdn: {}, amount: {}, service: {}. New balance: {}",
                                            msisdn, amount, service, balance);
                                } else {
                                    logger.warn("Balance not found for subscriber. msisdn: {}", msisdn);
                                }
                            } else {
                                logger.warn("Subscriber not found. msisdn: {}", msisdn);
                            }
                        } catch (Exception e) {
                            logger.error(
                                    "Error occurred while updating subscriber balance. msisdn: {}, amount: {}, service: {}",
                                    msisdn, amount, service, e);
                        }
                    } else {
                        logger.warn("Invalid record format. Record: {}", record.value());
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }
}
