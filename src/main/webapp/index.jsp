<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Spring 3.0 MVC demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <hr>
    <ul>
	    <li>
	    	<a href="http://localhost:8080/interAction_v2/activityService/queryActivityInfo.json?activityIds=A0000001&needExt=1">获取活动信息</a><br/>
	    </li>
	    <li>
    		<a href="http://localhost:8080/interAction_v2/activityTypeService/queryActivityTypeInfo.json?activityIds=A0000002">获取活动类型数据</a><br/>
    	</li>
    	<li>
    		<a href="http://localhost:8080/interAction_v2/auctionService/auction.json?activityId=test123&uid=8818886&auctionTime=20150210132630&auctionFee=99999&commodityId=sp00001&commodityName=sp00001&couponCode=qNo.001&couponAmount=1999&accountNo=13212345678&appId=app0001&channelId=000020&ip=127.0.0.1&promotionChannel=122222">用户竞拍请求</a><br/>
    	</li>
	    <li>
    		<a href="http://localhost:8080/interAction_v2/auctionService/queryWinningUser.json?date=20150213&activityId=A0000001">获取抢拍成功的用户名单</a><br/>
    	</li>
    	<li>
    		<a href="http://localhost:8080/interAction_v2/auctionService/queryUserAction.json?uid=104313167275306140902">获取用户的互动记录（暂时只支持竞拍行为）  </a><br/>
    	</li>
    	
	    <li>
    		<a href="http://localhost:8080/interAction_v2/liveService/queryScheduleInfo.json?liveId=C98000000000000000001355725830536&date=20140322">获取直播节目单</a><br/>
    	</li>
	    <li><!-- liveId=98000000000000000001407998816993&time=20150210081011 -->
    		<a href="http://localhost:8080/interAction_v2/liveService/queryNowLiveInfo.json?liveId=C8000000000000000001376544343155&time=20150213154231">获取单个直播节目信息</a><br/>
    	</li>
    	
    	<li>
    		<a href="http://localhost:8080/interAction_v2/commodityService/queryCommodityById.json?commodityIds=3,4">获取商品信息</a><br/>
    	</li>
	    <li>
    		<input type="button" value="竞拍" onclick="auction()">
    	</li>
    </ul>
  </body>
  <script type="text/javascript">
  	function auction(){
  		var uid = "881888";
  		var url = "http://localhost:8080/interAction_v2/auctionService/auction.json?activityId=test123&auctionTime=20150210132630&auctionFee=99999&commodityId=sp00001&commodityName=sp00001&couponCode=qNo.001&couponAmount=1999&accountNo=13212345678&appId=app0001&channelId=000020&ip=127.0.0.1&promotionChannel=122222&uid=" + uid;
  		
  	}
  </script>
</html>
