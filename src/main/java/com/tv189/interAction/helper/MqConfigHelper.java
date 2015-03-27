package com.tv189.interAction.helper;


public class MqConfigHelper {
	private static final String configName = "config/MqConfig.properties";
	
	
//producer
	public static String getBrokerList() {
		return DynamicResource.getResource(configName).getString("metadata.broker.list");
	}

	public static String getSerializerClass() {
		return DynamicResource.getResource(configName).getString("serializer.class");
	}

	public static String getKeySerializerClass() {
		return DynamicResource.getResource(configName).getString("key.serializer.class");
	}

	public static String getPartitionerClass() {
		return DynamicResource.getResource(configName).getString("partitioner.class");
	}
	
	public static String getRequestRequiredAcks() {
		return DynamicResource.getResource(configName).getString("request.required.acks");
	}

	public static String getProducerType() {
		return DynamicResource.getResource(configName).getString("producer.type");
	}

	public static String getActivityExtTimeOut() {
		return DynamicResource.getResource(configName).getString("zk.connectiontimeout.ms");	
	}

	public static String getZookeeperSyncTimeOut() {
		return DynamicResource.getResource(configName).getString("zookeeper.sync.time.ms");	
	}
	
	public static String getAutoCommitEnable() {
		return DynamicResource.getResource(configName).getString("auto.commit.enable");	
	}
	
	public static String getAutoCommitIntervalOut() {
		return DynamicResource.getResource(configName).getString("auto.commit.interval.ms");	
	}
	
//consumer
	public static String getZookeeperConnect() {
		return DynamicResource.getResource(configName).getString("zookeeper.connect");	
	}
	public static String getGroupid() {
		return DynamicResource.getResource(configName).getString("group.id");	
	}
	public static String getAutoOffsetReset() {
		return DynamicResource.getResource(configName).getString("auto.offset.reset");	
	}
	
}
