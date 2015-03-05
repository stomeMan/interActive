package com.tv189.interAction.logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xuezhiyu
 *
 * 2014-6-18
 */
public class LogThread extends Thread{
	private static Queue<LogInfo> logInfoQueue = new ConcurrentLinkedQueue<LogInfo>();
	
	public static void addLogInfo(LogInfo logInfo){
		logInfoQueue.add(logInfo);
	}
	
	public void run() {
		System.out.println("--------日志线程启动-----------");
		while(true){
			LogInfo log = logInfoQueue.poll();
			System.out.println("记录日志名称--->"+log.getLogName());
		}
	}
}
