package com.rieckpil.learning.kafka.consumergroup;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerKafkaThree {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "group4");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

		ArrayList<String> topics = new ArrayList<String>();
		topics.add("my-first-java-group-topic");

		consumer.subscribe(topics);

		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("Record read in KafkaConsumer: " + record.toString());

				}
			}
		} catch (Exception e) {
			System.out.println("Inside excetion loop: ");
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
}
