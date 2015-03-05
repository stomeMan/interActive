package com.tv189.interAction.helper;


public class CacheConfigHelper {
	private static final String configName = "config/CacheConfig.properties";

	public static String getCacheIP() {
		return DynamicResource.getResource(configName).getString("CacheIP");
	}

	public static Integer getCachePort() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("CachePort"));
	}

	public static Integer getCacheTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("CacheTimeOut")) * 60;
	}

	public static Integer getActivityTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("ActivityTimeOut"));
	}
	
	public static Integer getActivityTypeTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("ActivityTypeTimeOut"));
	}

	public static Integer getLiveProgramInfoTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("LiveProgramInfoTimeOut"));
	}

	public static Integer getUserWinningTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("UserWinningTimeOut"));	
	}

	public static Integer getActivityExtTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("ActivityExtTimeOut"));	
	}

	public static Integer getCommodityTimeOut() {
		return Integer.parseInt(DynamicResource.getResource(configName).getString("CommodityTimeOut"));	
	}
}
