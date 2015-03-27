package com.tv189.interAction.mqthreads;

import java.util.List;

import javax.annotation.PostConstruct;

import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.KafkaConsumer;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.util.ParametersUtil;


@Component
public class ThreadForAuction {
	@Autowired
	KafkaConsumer consumer;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@PostConstruct
	public void run() {
		try {
			System.out.println(" Now is in ThreadForAuction run method! ");
			final Integer threads = 2;
			List<KafkaStream<byte[], byte[]>> streams = consumer.pull(ParametersUtil.TOPIC_AUCTION, threads);
			for (final KafkaStream<byte[], byte[]> kafkaStream : streams) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (MessageAndMetadata<byte[], byte[]> messageAndMetadata : kafkaStream) {
							ParametersUtil.insertDB(JsonHelper.toJSONObject(new String(messageAndMetadata.message()), UserWinning.class), sqlSessionFactory);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
