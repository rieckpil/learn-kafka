package com.rieckpil.learning.kafka.consumer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumerKafkaOffset {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "group1");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

		ArrayList<String> topics = new ArrayList<String>();
		String topic = "demo-reset-single";
		topics.add(topic);

		consumer.subscribe(topics);

		TopicPartition partition = new TopicPartition(topic, 0);
		boolean flag = false;

		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(1000);

				if (!flag) {
					consumer.seek(partition, 20);
					flag = true;
				}

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
