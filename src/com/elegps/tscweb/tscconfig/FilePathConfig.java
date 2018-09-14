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
	 * TSC�����ļ�·��
	 * 
	 * @return
	 */
	public static String getTSCFileName() {
		return getProperty("filename_tsc");
	}

	/**
	 * �����ļ�·��
	 * 
	 * @return
	 */
	public static String getGRPFileName() {
		return getProperty("filename_grp");
	}
	
	/**
	 * �̶���ǰ9λȺ��id
	 * @return
	 */
	public static String getGRPId(){
		return getProperty("prifix_grpid");
	}
	
	/**
	 * �����������app·��
	 * @return
	 */
	public static String getAPPPath(){
		return getProperty("app_path");
	}
	/**
	 * �̶���ǰ10λȺ��id
	 * @return
	 */
	public static String getGRPId10(){
		return getProperty("prifix_grpid10");
	}
	
	/**
	 * �̶���ǰ10λ�ն�id
	 * @return
	 */
	public static String getMSId(){
		return getProperty("prifix_id");
	}
	
	/**
	 * �׺�ͨע���ʱ�����ڵ���ҵID
	 * @return
	 */
	public static String getYhtEnterpriseId(){
		return getProperty("yht_enterpriseid");
	}
	
	/**
	 * �人������Ŀ�����ڵ���ҵID
	 * @return
	 */
	public static String getWhlyEnterpriseId(){
		return getProperty("whly_enterpriseid");
	}
	/**
	 * ������ip
	 * @return
	 */
	public static String getServer_Ip(){
		return getProperty("server_ip");
	}
	
//	/**
//	 * ������tomcat�˿ں�
//	 * @return
//	 */
//	public static String getServer_Port(){
//		return getProperty("server_port");
//	}
	
	public static String getFreeswitch_Path(){
		return getProperty("freeswitch_path");
	}
	
	
	/**
	 * ��ȡalipay�̻���Ϣ
	 * @return
	 */
	public static String getAliPartner(){
		return getProperty("ali_partner");//�̻���
	}
	
	public static String getAliSeller(){
		return getProperty("ali_seller");//�տ��˺�
	}
	public static String getAliEpPrivatekey(){
		return getProperty("ali_ep.privatekey");//�̻�˽Կ
	}
	public static String getAliLogPathr(){
		return getProperty("ali_logpath");//��־·��
	}
	public static String getAliRsaprivate(){
		return getProperty("ali_rsaprivate");//RSA
	}
	public static String getAliPublicKey(){
		return getProperty("ali_publickey");//��Կ
	}
	public static String getAliNotifyUrl(){
		return getProperty("ali_notifyurl");//֧�����ص�URL
	}

	/**
	 * ��ȡapp֧������Ҫ���˺���Ϣ
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
	 * ��ȡ��̨������΢��֧���̻���Ϣ
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