package emdx.example;

import java.rmi.RemoteException;

/**
 * 普通106通道短信发送
 * @author Leo
 *
 */
public class SendManager {
	
	public static int sendSms(String phone, int code){
		int i = -1;
		try {
			i = SingletonClient.getClient().sendSMS(new String[] { phone }, 
					"【船家宝】尊敬的用户，您的“船家宝”注册验证码是："+code+"，该验证码10分钟内失效。", "",5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 带扩展码
		System.out.println("testSendSMS=====" + i);
		return i;
	}
	
	public static int sendSms2(String phone, int code){
		int i = -1;
		try {
			i = SingletonClient.getClient().sendSMS(new String[] { phone }, 
					"【船家宝】尊敬的用户，您的“船家宝”重置密码验证码是："+code+"，该验证码10分钟内失效。若不是本人操作可不理会!", "",5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 带扩展码
		System.out.println("testSendSMS=====" + i);
		return i;
	}
	
	public static int sendSmsYqFriend(String yqm,String name, String address,String friend){
		int i = -1;
		try {
			i = SingletonClient.getClient().sendSMS(new String[] { friend }, "【船家宝】您好！您的好友“"+name
					+"”邀请您加入“船家宝”免费电话、对讲组，下载地址："+address+" ,登录帐号："+friend
					+" ,登录密码：123465", "",4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 带扩展码
		System.out.println("testSendSMS=====" + i);
		return i;
	}
	
	public static int sendSmsYqFriend2(String yqm,String name, String address,String friend){
		int i = -1;
		try {
			i = SingletonClient.getClient().sendSMS(new String[] { friend }, "【船家宝】您好！您的好友“"+name
					+"”邀请您加入“船家宝”免费电话、对讲组，下载地址："+address+" ,组号为："+yqm, "",4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 带扩展码
		System.out.println("testSendSMS=====" + i);
		return i;
	}
}
