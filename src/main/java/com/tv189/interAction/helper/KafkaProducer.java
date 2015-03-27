package com.tv189.interAction.helper;

import java.util.Properties;
import org.springframework.stereotype.Component;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

@Component
public class KafkaProducer {
	
	private static ProducerConfig config = null;
	
	
	static{
		Properties props = new Properties();
		String brokerList = MqConfigHelper.getBrokerList();
		String kafkaSerializerClass = MqConfigHelper.getSerializerClass();
		String kafkaKeySerializerClass = MqConfigHelper.getKeySerializerClass();
		String requestRequiredAcks = MqConfigHelper.getRequestRequiredAcks();
		props.put("metadata.broker.list",brokerList);
		props.put("serializer.class", kafkaSerializerClass);
        props.put("key.serializer.class", kafkaKeySerializerClass);
		props.put("request.required.acks", requestRequiredAcks);
		if(config == null){
			config = new ProducerConfig(props);
		}
	}
	
	public void push(String topic,String key,String message){
		// 创建producer
		Producer<String, String> producer = new Producer<String, String>(config);
		try {
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, key, message);
			producer.send(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
	
//	public static void main(String[] args) throws InterruptedException{
//		int events = 10;
//		// 创建producer
//		Producer<String, String> producer = new Producer<String, String>(config);
//		// 产生并发送消息
//		long start = System.currentTimeMillis();
//		for (long i = 0; i < events; i++) {
//			long runtime = new Date().getTime();
//			String ip = "192.168.2." + i;// rnd.nextInt(255);
//			String msg = runtime + ",www.example.com," + ip;
//			// 如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0
//			KeyedMessage<String, String> data = new KeyedMessage<String, String>("mykafka", ip, msg);
//			System.out.println(new String(data.message()));
//			System.out.println("ok");
//			producer.send(data);
//		}
//		System.out.println("耗时:" + (System.currentTimeMillis() - start));
//		// 关闭producer
//		producer.close();
//	}
}