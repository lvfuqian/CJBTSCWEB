package com.elegps.tscweb.comm;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.tscconfig.FilePathConfig;

public class GrpIdRule extends BaseAction{
	
	/**
	 * 生成grp ID
	 * 关于群组ID的规则：
	 * 				前面9位代表服务器编号；
	 * 				10、11备用；（0、0）
	 * 				12、13、14代理商编号；
	 * 				15、16、17企业编号；
	 * 				18、19、20、21群组编号
	 * @return
	 */
	public final static String grpIdRule(String aId , String epId){
		String agentNum = agentNum(aId);//12、13、14代理商编号；
		String epNum = epNum(epId);//15、16、17企业编号
		String seventeenNum = nineNum + spareTenAndElecenNum + agentNum + epNum;//前17位
		String grpNum = grpNum(seventeenNum);//后4位
		String grpId = seventeenNum + grpNum; //21位群组ID
		System.out.println("生成群组ID"+grpId);
		return grpId;
	}
	
	
	private static String nineNum = FilePathConfig.getGRPId(); //前9位
	private static String spareTenAndElecenNum = "00" ;//10、11备用；（0、0）
	
	/**
	 * 12、13、14代理商编号；
	 * @return
	 */
	private static String agentNum(String aId){
		String a = aId;
		if(!a.equals("")&& a != null && !a.equals("-1")){
			for(int i = 0; i < (3-aId.length()) ;i++){
				a = "0"+a;
			}
		}else{
			a ="000";
		}
		return a;
	}
	
	/**
	 * 15、16、17企业编号；
	 * @return
	 */
	private static String epNum(String epId){
		String e = epId;
		for(int i = 0; i < (3-epId.length()) ;i++){
			e = "0"+e;
		}
		return e;
	}
	
	/**
	 * 18、19、20、21群组编号
	 * @return
	 */
	private static String grpNum(String seventeenNum){
		String upGrpId = grpmanager.getGrpId(seventeenNum);
		String grpFourNum = "";
		if(upGrpId != null && upGrpId != ""){
			int upG =Integer.parseInt(upGrpId.substring(upGrpId.length()-4,upGrpId.length()));
			grpFourNum = upG + 1 +"";
		}else{
			grpFourNum = "0000";
		}
		
		int nextLength = grpFourNum.length();
		if(nextLength < 4){
			
			for(int i = 0; i < (4-nextLength) ;i++){
				grpFourNum = "0"+grpFourNum;
			}
		}
		return grpFourNum;
	}
}
