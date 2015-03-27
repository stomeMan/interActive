package com.tv189.interAction.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

@Component
public class KafkaConsumer {
	private static ConsumerConfig config = null;
	
	static{
		Properties props = new Properties();
		String zookeeperConnect = MqConfigHelper.getZookeeperConnect();
		String goupid = MqConfigHelper.getGroupid();
		String autoOffsetReset = MqConfigHelper.getAutoOffsetReset();
		props.put("zookeeper.connect",zookeeperConnect);
		props.put("group.id", goupid);
        props.put("auto.offset.reset", autoOffsetReset);
		if(config == null){
			config = new ConsumerConfig(props);
		}
	}
	
	public List<KafkaStream<byte[], byte[]>> pull(String topic,Integer threads){
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
		HashMap<String, Integer> topicCountmap = new HashMap<String, Integer>();
		topicCountmap.put(topic, threads);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountmap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		return streams;
	}
	
	
	
//	public static void main(String[] args) {
//		final String topic = "auction";
//		final Integer threads = 2;
//
//		Properties props = new Properties();
//		props.put("zookeeper.connect", "172.16.37.52:2181");
//		props.put("group.id", "interAction_v2");
//		props.put("auto.offset.reset", "smallest");
//
//		ConsumerConfig config = new ConsumerConfig(props);
//		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
//		HashMap<String, Integer> topicCountmap = new HashMap<String, Integer>();
//		topicCountmap.put(topic, threads);
//
//		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountmap);
//		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
//
//		for (final KafkaStream<byte[], byte[]> kafkaStream : streams) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for (MessageAndMetadata<byte[], byte[]> messageAndMetadata : kafkaStream) {
//						System.err.println(new String(messageAndMetadata.message()));
//						System.out.println(new String(messageAndMetadata.key()));
//					}
//
//				}
//			}).start();
//		}
//	}
}