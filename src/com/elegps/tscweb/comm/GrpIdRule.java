package com.elegps.tscweb.comm;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.tscconfig.FilePathConfig;

public class GrpIdRule extends BaseAction{
	
	/**
	 * ����grp ID
	 * ����Ⱥ��ID�Ĺ���
	 * 				ǰ��9λ�����������ţ�
	 * 				10��11���ã���0��0��
	 * 				12��13��14�����̱�ţ�
	 * 				15��16��17��ҵ��ţ�
	 * 				18��19��20��21Ⱥ����
	 * @return
	 */
	public final static String grpIdRule(String aId , String epId){
		String agentNum = agentNum(aId);//12��13��14�����̱�ţ�
		String epNum = epNum(epId);//15��16��17��ҵ���
		String seventeenNum = nineNum + spareTenAndElecenNum + agentNum + epNum;//ǰ17λ
		String grpNum = grpNum(seventeenNum);//��4λ
		String grpId = seventeenNum + grpNum; //21λȺ��ID
		System.out.println("����Ⱥ��ID"+grpId);
		return grpId;
	}
	
	
	private static String nineNum = FilePathConfig.getGRPId(); //ǰ9λ
	private static String spareTenAndElecenNum = "00" ;//10��11���ã���0��0��
	
	/**
	 * 12��13��14�����̱�ţ�
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
	 * 15��16��17��ҵ��ţ�
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
	 * 18��19��20��21Ⱥ����
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
