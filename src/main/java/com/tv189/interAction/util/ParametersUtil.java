package com.tv189.interAction.util;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.mybatis.dao.UserWinningCloseTypeDao;
import com.tv189.interAction.mybatis.dao.UserWinningDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessThePriceDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessTheStockDao;
import com.tv189.interAction.mybatis.dao.UserWinningPraiseDao;
import com.tv189.interAction.mybatis.model.ActivityExt;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.mybatis.model.UserWinningCloseType;
import com.tv189.interAction.mybatis.model.UserWinningGuessThePrice;
import com.tv189.interAction.mybatis.model.UserWinningGuessTheStock;
import com.tv189.interAction.mybatis.model.UserWinningPraise;

public class ParametersUtil {
	public static final String TOPIC_AUCTION = "NATURE";
	public static final String TOPIC_CLOSETYPE = "CLOSETYPE";
	public static final String TOPIC_GUESSTHEPRICE = "GUESSTHEPRICE";
	public static final String TOPIC_GUESSTHESTOCK = "GUESSTHESTOCK";
	public static final String TOPIC_PRAISE = "PRAISE";
	
	/**
	 * 判断竞拍时间和竞拍价格
	 * @param winUser 竞拍用户
	 * @return
	 * 竞拍时间是系统当前时间3s内误差
	 * 竞拍价格是活动扩展信息价格属性3s内误差
	 */
	public static String checkTimeAndPrice(String activityId, Integer auctionFee, Date auctionTime) {
		Date now = new Date();
		long second = (auctionTime.getTime() - now.getTime()) / 1000;
		if(second > 3 || second < -3){
			return "竞拍时间不符合活动要求";
		}
		
		String actExt = RedisCommon.getData(activityId, new ActivityExt());
		JSONObject jsonObj = JSON.parseObject(actExt);
		Integer frequency = jsonObj.getInteger("frequency");
		Integer stepSize = jsonObj.getInteger("stepSize");
		Integer price = JSON.parseObject(jsonObj.getString("commodityInfo")).getInteger("price");
		Integer min = price - stepSize / frequency * 3;
		Integer max = price + stepSize / frequency * 3;
		
		if(auctionFee > max || auctionFee < 0 || auctionFee < min){
			return "竞拍价格有误";
		}
		return "";
	}

	public static void insertDB(UserWinning uw,
			SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserWinningDao uwDao = sqlSession.getMapper(UserWinningDao.class);
		uwDao.insertUserWinning(uw);
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
	}

	public static void insertDB(UserWinningCloseType uwCT,
			SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserWinningCloseTypeDao uwCTDao = sqlSession.getMapper(UserWinningCloseTypeDao.class);
		uwCTDao.insert(uwCT);
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
	}
	
	public static void insertDB(UserWinningGuessThePrice uwGTP,
			SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserWinningGuessThePriceDao uwGTPDao = sqlSession.getMapper(UserWinningGuessThePriceDao.class);
		uwGTPDao.insert(uwGTP);
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
	}
	
	public static void insertDB(UserWinningPraise uwP,
			SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserWinningPraiseDao uwPDao = sqlSession.getMapper(UserWinningPraiseDao.class);
		uwPDao.insert(uwP);
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
	}
	
	public static void insertDB(UserWinningGuessTheStock uwGTS,
			SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserWinningGuessTheStockDao uwGTSDao = sqlSession.getMapper(UserWinningGuessTheStockDao.class);
		uwGTSDao.insert(uwGTS);
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
	}
}
