package com.carTravelsky.utils.app;

import java.util.List;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class AppPushUtils {

	public static final String APP_KEY = "202f4419f6235b0ff5a21e10";
	
	public static final String MASTER_SECRET = "988fa9873886dee1a9f04139";
	
	//public static final String PUSH_URL = "https://api.jpush.cn/v3/push";
	
	// 推送给指定用户
	public static void pushMessageToAppointUser(String title,String content,List<String> userList){
		// 推送对象
		JPushClient push = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		
		PushPayload pushLoad = PushPayload.newBuilder()
         .setPlatform(Platform.android_ios())
         .setAudience(Audience.alias(userList))
         .setNotification(Notification.newBuilder()
         		.setAlert(content)
         		.addPlatformNotification(AndroidNotification.newBuilder()
         				.setTitle(title).build())
         		.addPlatformNotification(IosNotification.newBuilder()
         				.incrBadge(1)
         				.addExtra(title, content).build())
         		.build())
         .build();
		
		try {
			PushResult result = push.sendPush(pushLoad);
			System.out.println("推送结果："+ result);
			
		} catch (APIConnectionException e) {
	        System.err.println("推送网络连接异常，请稍后再试..."+ e);

	    } catch (APIRequestException e) {
	    	System.err.println("推送请求失败，请检查参数是否正确..."+ e);
	    	System.err.println("HTTP Status: " + e.getStatus());
	    	System.err.println("Error Code: " + e.getErrorCode());
	    	System.err.println("Error Message: " + e.getErrorMessage());
	    }
	}
	
	// 推送给所有用户
	public static void pushMessageToAllUser(String title,String content){
		// 推送对象
		JPushClient push = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		
		PushPayload pushLoad = PushPayload.newBuilder()
         .setPlatform(Platform.android_ios())
         .setAudience(Audience.all())
         .setNotification(Notification.newBuilder()
         		.setAlert(content)
         		.addPlatformNotification(AndroidNotification.newBuilder()
         				.setTitle(title).build())
         		.addPlatformNotification(IosNotification.newBuilder()
         				.incrBadge(1)
         				.addExtra(title, content).build())
         		.build())
         .build();
		
		try {
			PushResult result = push.sendPush(pushLoad);
			System.out.println("推送结果："+ result);
			
		} catch (APIConnectionException e) {
	        System.err.println("推送网络连接异常，请稍后再试..."+ e);

	    } catch (APIRequestException e) {
	    	System.err.println("推送请求失败，请检查参数是否正确..."+ e);
	    	System.err.println("HTTP Status: " + e.getStatus());
	    	System.err.println("Error Code: " + e.getErrorCode());
	    	System.err.println("Error Message: " + e.getErrorMessage());
	    }
	}
	
}
