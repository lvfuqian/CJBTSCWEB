package com.elegps.tscweb.tscconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author LuYun
 * 
 */
public class FilePathConfig {
	private final static Properties configurations;
	static {
		System.setProperty("java.awt.headless", "true");
		configurations = PathUtils.loadFromResource(FilePathConfig.class,
				"/filepathconfig.properties");
	}

	public static String getProperty(String key) {
		return configurations.getProperty(key);
	}

	/**
	 * TSC配置文件路径
	 * 
	 * @return
	 */
	public static String getTSCFileName() {
		return getProperty("filename_tsc");
	}

	/**
	 * 分组文件路径
	 * 
	 * @return
	 */
	public static String getGRPFileName() {
		return getProperty("filename_grp");
	}
	
	/**
	 * 固定的前9位群组id
	 * @return
	 */
	public static String getGRPId(){
		return getProperty("prifix_grpid");
	}
	
	/**
	 * 邀请好友下载app路径
	 * @return
	 */
	public static String getAPPPath(){
		return getProperty("app_path");
	}
	/**
	 * 固定的前10位群组id
	 * @return
	 */
	public static String getGRPId10(){
		return getProperty("prifix_grpid10");
	}
	
	/**
	 * 固定的前10位终端id
	 * @return
	 */
	public static String getMSId(){
		return getProperty("prifix_id");
	}
	
	/**
	 * 易呼通注册的时候，所在的企业ID
	 * @return
	 */
	public static String getYhtEnterpriseId(){
		return getProperty("yht_enterpriseid");
	}
	
	/**
	 * 武汉旅游项目，所在的企业ID
	 * @return
	 */
	public static String getWhlyEnterpriseId(){
		return getProperty("whly_enterpriseid");
	}
	/**
	 * 服务器ip
	 * @return
	 */
	public static String getServer_Ip(){
		return getProperty("server_ip");
	}
	
//	/**
//	 * 服务器tomcat端口号
//	 * @return
//	 */
//	public static String getServer_Port(){
//		return getProperty("server_port");
//	}
	
	public static String getFreeswitch_Path(){
		return getProperty("freeswitch_path");
	}
	
	
	/**
	 * 获取alipay商户信息
	 * @return
	 */
	public static String getAliPartner(){
		return getProperty("ali_partner");//商户号
	}
	
	public static String getAliSeller(){
		return getProperty("ali_seller");//收款账号
	}
	public static String getAliEpPrivatekey(){
		return getProperty("ali_ep.privatekey");//商户私钥
	}
	public static String getAliLogPathr(){
		return getProperty("ali_logpath");//日志路径
	}
	public static String getAliRsaprivate(){
		return getProperty("ali_rsaprivate");//RSA
	}
	public static String getAliPublicKey(){
		return getProperty("ali_publickey");//公钥
	}
	public static String getAliNotifyUrl(){
		return getProperty("ali_notifyurl");//支付宝回调URL
	}

	/**
	 * 获取app支付宝需要的账号信息
	 * @return
	 */
	public static Map<String, String> getAliInfo(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("PARTNER", getProperty("ali_partner"));
		map.put("SELLER", getProperty("ali_seller"));
		map.put("RSA_PRIVATE", getProperty("ali_rsaprivate"));
		map.put("NOTIFY_URL", getProperty("ali_notifyurl"));
		return map;
	}
	
	/**
	 * 获取后台服务器微信支付商户信息
	 * @return
	 */
	public static Map<String, String> getWeChatInfo(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", getProperty("wechat_appid"));
		map.put("appsecret", getProperty("wechat_appsecret"));
		map.put("partner", getProperty("wechat_partner"));
		map.put("partnerkey", getProperty("wechat_partnerkey"));
		map.put("notifyurl",getProperty("wechat_notifyurl"));
		return map;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("1222"+getWeChatInfo());
		
	}
}