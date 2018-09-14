package com.elegps.tscweb.dwr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;

import com.elegps.tscweb.comm.ReadExcel;
import com.elegps.tscweb.comm.UpLoadFileUtil;
import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TabSysserverdbinfo;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.AgentManager;
import com.elegps.tscweb.serivce.DdbManager;
import com.elegps.tscweb.serivce.EnterPriseManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpManager;
import com.elegps.tscweb.serivce.GrpMsManager;
import com.elegps.tscweb.serivce.MsManager;
import com.elegps.tscweb.serivce.impl.AgentManagerImpl;
import com.elegps.tscweb.serivce.impl.EnterPriseManagerImpl;
import com.elegps.tscweb.serivce.impl.GpsMsManagerImpl;
import com.elegps.tscweb.serivce.impl.GrpManagerImpl;
import com.elegps.tscweb.serivce.impl.GrpMsManagerImpl;
import com.elegps.tscweb.serivce.impl.MsManagerImpl;
import com.elegps.tscweb.serivce.impl.ServerDBManagerImpl;
import com.elegps.tscweb.tscconfig.FilePathConfig;

public class ChildAgent {
	protected static AgentManager agent;
	protected static EnterPriseManager ep;
	protected static MsManager ms;
	protected static GrpManager grp;
	protected static GrpMsManager grpmsmanager;
	protected static GpsMsManager gpsmsmanager;
	protected static DdbManager<TabSysserverdbinfo> tabSysserverdbinfo;

	static {
		try {
			agent = new AgentManagerImpl();
			ep = new EnterPriseManagerImpl();
			ms = new MsManagerImpl();
			grp = new GrpManagerImpl();
			grpmsmanager = new GrpMsManagerImpl();
			gpsmsmanager=new GpsMsManagerImpl();
			tabSysserverdbinfo=new ServerDBManagerImpl();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����excel�ļ���������ն���Ϣ
	 * @param excel �ļ���
	 * @param excelName �ļ���
	 * @param pagentid һ������
	 * @param childagentid ��������
	 * @param ep ��ҵid
	 * @param ext �ļ���׺�� xls��xlsx��
	 * @return
	 */
	public String readExcel(InputStream excel,String excelName,String pagentid,String childagentid,String ep,String ext){

		if(ep.equals("")){
			return "��ѡ����ҵ";
		}
		
		List list =new ArrayList();
		String czId="";
		String errorId="";
		if(ext.equals("xls") || ext.equals("xlsx")){
			list = ReadExcel.getWorkbook(ext, excel);//��ȡexcel�����ݷ�סΪlist����
		}else{
			return "�ļ���ʽ����";
		}
		
		if(list == null)
			return "Excel�ļ������ݣ�";
		
		for(int i = 0;i<list.size();i++){//����list����
			String[] s = (String[])list.get(i);
			String msid = msid11or21(s[0]);
			if(msid.equals("false")){//����id��ʽ
				errorId += s[0]+"��";
			}else{
				TbMsInfo msinfo = ms.getTBMsinfoby_msid(msid);
				if(msinfo != null){//�ն�ID�Ѵ���
					czId += s[0]+"��";
				}else{
					int aid = Integer.parseInt(pagentid);
					if(!childagentid.equals("-1") && !childagentid.equals("")){
						aid = Integer.parseInt(childagentid);
					}
					ms.createMs(msid, 1, s[1], s[2], 0, 1, ep, "3", s[3],
						null, 1, 0, 0,"0", "0","1", null,"12",aid);
				}
			}
		}
		
		String outString = "";
		int count = list.size();//id������
		int czIdnum = czId.split("��").length;
		int errorIdnum = errorId.split("��").length;//�����ʽ������id�Ѵ�������
		if(czId.length() == 0){
			czIdnum = 0;
		}
		if(errorId.length() == 0){
			errorIdnum = 0;
		}
		int oknum = count - czIdnum - errorIdnum;
		outString = "���ն������� "+count+" ����\n" 
					+ "�ɹ���ӡ�" + oknum +"�� ��\n" 
					+ "����id��ʽ ��" + errorIdnum +"�� �� \n"
					+ "�Ѵ���id ��" + czIdnum +"�� �� ";
		
		return outString;
	}
	
	/**
	 * msidת�����ɴ�21λ��11λ
	 * @param id
	 * @return
	 */
	public String msid11or21(String id){
		String msid = "";
		if(id.length() == 21){
			msid = id;
		}else if(id.length() == 11){
			msid = FilePathConfig.getMSId() + id;
		}else{
			msid ="false";
		}
		return msid;
	}
	
	/**
	 * �ϴ����ͼƬ������ͼƬ·��
	 * @param ft
	 * @param imgformat
	 * @return
	 */
	public String upload(FileTransfer ft,String imgformat){
		String ggg=imgformat.substring(imgformat.lastIndexOf("."), imgformat.length());
		WebContext  wc = WebContextFactory.get();
		//���Ӧ��·��  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//�������ڸ�ʽ
		String d=df.format(new Date());
		String advFile ="advFile/image/"+d;
		
		String headImages = wc.getSession().getServletContext().getRealPath("/")+advFile;
		File file = new File(headImages);
		//����ļ��в����ڣ��ʹ���      
		if( !file.exists()){
			file.mkdirs();
		}      
		try{
			//�ļ��ϴ����Ĵ���
			InputStream uploadFile = ft.getInputStream();
			//�ļ���
			long now = System.currentTimeMillis();
			String fileName = "/"+now+ggg;
			
			
			int available = uploadFile.available();  
			byte[] b = new byte[available];  
			uploadFile.read(b);
			FileOutputStream foutput = new FileOutputStream(file+fileName);
			  
			foutput.write(b);  
			foutput.flush();  
			foutput.close();  
			uploadFile.close();

			return advFile+fileName;
		}catch(Exception e){
			e.printStackTrace(); 
		}     
		return null;   
	}

	/**
	 * ��֤msid�Ƿ����
	 * @param msid
	 * @return
	 */
	public boolean checkIsMs(String msid){
		TbMsInfo msinfo = ms.getTBMsinfoby_msid(msid);
		if(msinfo == null){
			return true;
		}
		return false;
	}
	
	/**
	 * ��ѯһ���������µ����ж���������
	 * 
	 * @param paraentagentid
	 * @return
	 */
	public List<String[]> getchiledAgent(String paraentagentid) {
		List<String[]> list = new ArrayList<String[]>();
		List listagent = agent.getChildAgentByParamentid(paraentagentid);
		if (listagent != null) {
			for (int i = 0; i < listagent.size(); i++) {
				TbAgentInfo agent = (TbAgentInfo) listagent.get(i);
				list.add(new String[] { agent.getAgent_Id().toString(),
						agent.getAgent_Name() });
			}
			return list;
		}
		return null;
	}

	/**
	 * ��ѯָ���������µ���ҵ��Ϣ
	 * 
	 * @param pagent
	 * @param cagent
	 * @return
	 */
	public List<String[]> getEpinfoByAId(String pagent, String cagent,int a_id,int r_id) {
		List<String[]> list = new ArrayList<String[]>();
		List listep = ep.getEpinfo_byagentid(pagent, cagent,a_id,r_id);
		if (listep != null) {
			for (int i = 0; i < listep.size(); i++) {
				TbEnterpriseInfo ep = (TbEnterpriseInfo) listep.get(i);
				list.add(new String[] { ep.getEp_Id().toString(),
						ep.getEp_Name() });
			}
			return list;
		}
		return null;
	}

	/**
	 * ��ѯָ���������µ�ָ����ҵ��Ϣ
	 * 
	 * @param pagent
	 * @param cagent
	 * @return
	 */
	public List<String[]> getEpinfoByEId(String pagent, String cagent,int ep_id,int r_id) {
		List<String[]> list = new ArrayList<String[]>();
		List listep = ep.getEpinfo_byeid(pagent, cagent,ep_id,r_id);
		if (listep != null) {
			for (int i = 0; i < listep.size(); i++) {
				TbEnterpriseInfo ep = (TbEnterpriseInfo) listep.get(i);
				list.add(new String[] { ep.getEp_Id().toString(),
						ep.getEp_Name() });
			}
			return list;
		}
		return null;
	}
	
	/**
	 * ��ѯָ����������ָ����ҵ�µ��ն���Ϣ
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<String[]> getMsinfo(String pagentid, String childagentid,
			String ep) {
		List<String[]> list = new ArrayList<String[]>();
		if (ep != null && !ep.equals("")) {
			List listms = ms.getAllMs_Info(pagentid, childagentid, ep);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() });
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * ��ѯָ�������̺���ҵ�µ�Ⱥ����Ϣ
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<String[]> getGrpinfo(String pagentid, String childagentid,
			String ep) {
		List<String[]> list = new ArrayList<String[]>();
		if (ep != null && !ep.equals("")) {
			List listgrp = grp.getAllGrp_Info(pagentid, childagentid, ep);
			if (listgrp != null) {
				for (int i = 0; i < listgrp.size(); i++) {
					TbGrpInfo grpinfo = (TbGrpInfo) listgrp.get(i);
					list.add(new String[] { grpinfo.getGrpid().toString(),
							grpinfo.getGrpid() + "-" + grpinfo.getGrpname() });
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * ����Ⱥ��Ų�ѯ�Ѿ�����Ⱥ���ն˶�Ӧ��ϵ���ն��б�
	 * 
	 * @param grpid
	 * @return
	 */
	public List<String[]> getGrpMSinfo_grpid(String grpid) {
		List<String[]> list = new ArrayList<String[]>();
		if (grpid != null && !grpid.equals("") && !grpid.equals("-1")) {
			List listms = grpmsmanager.getMs_info(grpid);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					Map map = new HashMap();
					map = (Map)listms.get(i);
					list.add(new String[] { map.get("msid").toString(),
							map.get("msid") + "-" + map.get("msname") });
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * ���ݴ����̲�ѯ��������ն��б�
	 * 
	 * @param grpid
	 * @return
	 */
	public List<String[]> getFPMSinfo_aid(String aid,String mssl) {
		List<String[]> list = new ArrayList<String[]>();
		if (aid != null && !aid.equals("")) {
			int msCount = 0;
			if(mssl.equals("") || mssl.equals("0") || mssl.equals(null)){
				msCount = 50;
			}else{
				msCount =Integer.parseInt(mssl);
			}
			List listms = ms.getMsInfo_byAid(aid,msCount);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo ms = new TbMsInfo();
					ms = (TbMsInfo)listms.get(i);
					list.add(new String[] { ms.getMsId(),
							ms.getMsId() + "-" + ms.getMsName() });
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * ����Ⱥ��Ų�ѯ������Ⱥ���ն˶�Ӧ��ϵ���ն��б�
	 * 
	 * @param grpid
	 * @return
	 */
	public List<String[]> getGrpMSinfo_notgrpid(String ep, String grpid) {
		if(ep != null && !ep.equals("")){
			List<String[]> list = new ArrayList<String[]>();
			if (grpid != null && !grpid.equals("")) {
				List listms = ms.getAllMs_Info_not_Bygrpid(grpid, ep);
				if (listms != null) {
					for (int i = 0; i < listms.size(); i++) {
						TbMsInfo msinfo = (TbMsInfo) listms.get(i);
						list.add(new String[] { msinfo.getMsId().toString(),
								msinfo.getMsId() + "-" + msinfo.getMsName() });
					}
					return list;
				}
			}
		}
		return null;
	}

	/**
	 * ����Ⱥ��Ų�ѯȺ���ն˶�Ӧ��ϵ���ն��б�
	 * �ն�������ֵҳ����ʾ
	 * @param grpid
	 * @return
	 */
	public List<String[]> getGrpMSAllinfo_bygrpid(String ep, String grpid) {
		if (ep != null && !ep.equals("")) {
			List<String[]> list = new ArrayList<String[]>();
			if (grpid != null && !grpid.equals("")) {
				List listms = ms.getAllMs_InfoBygrpid(grpid, ep);
				if (listms != null) {
					for (int i = 0; i < listms.size(); i++) {
						TbMsInfo msinfo = (TbMsInfo) listms.get(i);
						String bc = "";//��ɫ����
						if(msinfo.getMsMoneyTime()!= null){
							Calendar c = Calendar.getInstance();//���һ��������ʵ��  
							//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							c.setTime(new Date());//��������ʱ��   
							c.add(Calendar.MONTH,1);//���������·���������
							Date date = c.getTime();
							//System.out.println(format2.format(c.getTime()));
							if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
								bc = "#7B7B7B";
							}else if(msinfo.getMsMoneyTime().before(new Date())){//��ϵͳʱ����ǰ����ʾǷ�ѣ�
								bc = "red";
							}else if(msinfo.getMsMoneyTime().after(date)){//��ϵͳʱ��һ�������ϣ���ʾ������
								bc = "green";
							}
						}else{
							bc = "red";
						}
						
						list.add(new String[] { msinfo.getMsId().toString(),
								msinfo.getMsId() + "-" + msinfo.getMsName() + "-�ײͣ�" 
								+ msinfo.getPf().getPfType() + "��-" +msinfo.getMsMoneyTime(),bc });
					}
					return list;
				}
			}
		}
		return null;
	}
	
	/**
	 * ����Ⱥ��Ų�ѯȺ���ն˶�Ӧ��ϵ�������ն��б�
	 * �ն��ײ�ת��ҳ����ʾ
	 * @param grpid
	 * @return
	 */
	public List<String[]> getGrpZCMSAllinfo_bygrpid(String ep, String grpid) {
		if (ep != null && !ep.equals("")) {
			List<String[]> list = new ArrayList<String[]>();
			if (grpid != null && !grpid.equals("")) {
				List listms = ms.getAllMs_Info_notQF_Bygrpid(grpid, ep);//��ȡ��Ƿ�ѵ��ն�
				if (listms != null) {
					for (int i = 0; i < listms.size(); i++) {
						TbMsInfo msinfo = (TbMsInfo) listms.get(i);
						String bc = "";//��ɫ����
						if(msinfo.getMsMoneyTime()!= null){
							Calendar c = Calendar.getInstance();//���һ��������ʵ��  
							//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							c.setTime(new Date());//��������ʱ��   
							c.add(Calendar.MONTH,1);//���������·���������
							Date date = c.getTime();
							//System.out.println(format2.format(c.getTime()));
							if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
								bc = "#7B7B7B";
							}else if(msinfo.getMsMoneyTime().before(new Date())){//��ϵͳʱ����ǰ����ʾǷ�ѣ�
								bc = "red";
							}else if(msinfo.getMsMoneyTime().after(date)){//��ϵͳʱ��һ�������ϣ���ʾ������
								bc = "green";
							}
						}else{
							bc = "red";
						}
						
						list.add(new String[] { msinfo.getMsId().toString(),
								msinfo.getMsId() + "-" + msinfo.getMsName() + "-�ײͣ�" 
								+ msinfo.getPf().getPfType() + "��-" +msinfo.getMsMoneyTime(),bc });
					}
					return list;
				}
			}
		}
		return null;
	}
	/**
	 * ����  msID  ģ����ѯ   �û��������ն˵��б�
	 * �ն�����  ��ֵ   ҳ����ʾ
	 * @param roleId
	 * @param agentId
	 * @param epId
	 * @param msId
	 * @return list
	 */
	public List<String[]> getGrpMSAllinfo_byms(int roleId,String agentId,String epId,String msId) {
		List<String[]> list = new ArrayList<String[]>();
		if (msId.trim() != null && !msId.trim().equals("")) {
			List listms =null;
			StringBuffer epid = new StringBuffer();
			if (roleId == 3 || roleId == 4){
				epid.append(epId);
				listms = ms.getMsInfo_byEpid(epid,msId);
			}else if(roleId == 2){
				List<TbEnterpriseInfo> listep = ep.getEpinfo_byagentid(agentId);
				
				for(int i = 0 ; i< listep.size(); i++){
					epid.append("'").append(listep.get(i).getEp_Id()).append("'"); 
					if(i!= listep.size()-1){
						epid.append(",");
					}
				}
				listms = ms.getMsInfo_byEpid(epid,msId);//�õ���ҵ���ն�
			}else{
				listms = ms.getMsInfo_byEpid(epid,msId);//�õ���ҵ���ն�
			}
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					String bc = "";//��ɫ����
					if(msinfo.getMsMoneyTime()!= null){
						Calendar c = Calendar.getInstance();//���һ��������ʵ��  
						//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						c.setTime(new Date());//��������ʱ��   
						c.add(Calendar.MONTH,1);//���������·���������
						Date date = c.getTime();
						//System.out.println(format2.format(c.getTime()));
						if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
							bc = "#7B7B7B";
						}else if(msinfo.getMsMoneyTime().before(new Date())){//��ϵͳʱ����ǰ����ʾǷ�ѣ�
							bc = "red";
						}else if(msinfo.getMsMoneyTime().after(date)){//��ϵͳʱ��һ�������ϣ���ʾ������
							bc = "green";
						}
					}else{
						bc = "red";
					}
					
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() + "-�ײͣ�" 
							+ msinfo.getPf().getPfType() + "��-" +msinfo.getMsMoneyTime(),bc });
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * ����  msID  ģ����ѯ   �û��������ն˵��б�
	 * �ն���������  Ⱥ����Ϣ  ҳ����ʾ
	 * @param roleId
	 * @param agentId
	 * @param epId
	 * @param msId
	 * @return list
	 */
	public List<String[]> getGrpMSAllinfo_byms_grpjsp(int roleId,String agentId,String epId,String msId) {
		List<String[]> list = new ArrayList<String[]>();
		if (msId.trim() != null && !msId.trim().equals("")) {
			List listms =null;
			StringBuffer epid = new StringBuffer();
			if (roleId == 3 || roleId == 4){
				epid.append(epId);
				listms = ms.getMsInfo_byEpid(epid,msId);
			}else if(roleId == 2){
				List<TbEnterpriseInfo> listep = ep.getEpinfo_byagentid(agentId);
				
				for(int i = 0 ; i< listep.size(); i++){
					epid.append("'").append(listep.get(i).getEp_Id()).append("'"); 
					if(i!= listep.size()-1){
						epid.append(",");
					}
				}
				listms = ms.getMsInfo_byEpid(epid,msId);//�õ���ҵ���ն�
			}else{
				listms = ms.getMsInfo_byEpid(epid,msId);//�õ���ҵ���ն�
			}
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName()});
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * �����ն˺Ż�ȡ�ն˴�������Ϣ
	 * @param msid
	 * @return
	 */
	public Map<String,String> getMsAllInfoBy_msid(String msid){
		TbMsInfo msinfo = ms.getTBMsinfoby_msid(msid);
		
		TbGrpMsListInfo grpms = grpmsmanager.getGrp(msid);
		TbEnterpriseInfo epinfo =ep.getEpinfo_byepid(msinfo.getEpid().toString());
		String ep =null;
		TbAgentInfo ainfo = new TbAgentInfo();
		if(epinfo != null){
			ep = epinfo.getEp_Id()+"";
			ainfo = agent.getAgent_ByAgentID(epinfo.getAgent_Id()+"");
		}else{
			ainfo = agent.getAgent_ByAgentID(msinfo.getAgentId()+"");
		}
		
		Map<String,String> m =new HashMap<String,String>();
		m.put("aId", ainfo.getAgent_Id()+"");
		m.put("epId", ep);
		if(grpms == null){
			m.put("grpId", null);
		}else{
			m.put("grpId", grpms.getGrp_id()+"");
		}
		
		return m;
	}
//	/**
//	 * �����ն˺Ż�ȡ�ն� Ⱥ�� ��Ϣ
//	 * @param msid
//	 * @return
//	 */
//	public List<String[]> getGRPAllInfoBy_msid(String msid){
//		
//		TbMsInfo msinfo = ms.getTBMsinfoby_msid(msid);
//		TbGrpInfo grpinfo = grp.g
//////		List grplist = grpmsmanager.getGrp_info(msid);
////
////		TbAgentInfo ainfo = agent.getAgent_ByAgentID(epinfo.getAgent_Id()+"");
////		Map<String,String> m =new HashMap<String,String>();
////		m.put("aId", ainfo.getAgent_Id()+"");
////		m.put("epId", epinfo.getEp_Id()+"");
//		return null;
//	}
	/**
	 * ���ݴ����̻�ȡ�ն���ҵ��Ϣ
	 * @param aid
	 * @return
	 */
	public List<String[]> getEpInfoBy_aid(String aid){
		List<String[]> list = new ArrayList<String[]>();
		List epList = ep.getEpinfo_byagentid(aid);
		if (epList != null) {
			for (int i = 0; i < epList.size(); i++) {
				TbEnterpriseInfo ep = (TbEnterpriseInfo) epList.get(i);
				list.add(new String[] { ep.getEp_Id().toString(),
						ep.getEp_Name() });
			}
			return list;
		}
		return null;
	}
	/**
	 * �����ն˺Ų�ѯ�Ѿ�����Ⱥ���ն˶�Ӧ��ϵ��Ⱥ���б�
	 * @param msid
	 * @return
	 */
	public List<String[]> getGrpMSinfo_msid(String msid) {
		List<String[]> list = new ArrayList<String[]>();
		if (msid != null && !msid.equals("")) {
			List listgrp = grpmsmanager.getGrp_info(msid);
			if (listgrp != null) {
				for (int i = 0; i < listgrp.size(); i++) {
					Map map = new HashMap();
					map = (Map) listgrp.get(i);
					list.add(new String[] { map.get("grpid").toString(),
							map.get("grpid") + "-" + map.get("grpname") });
				}
				return list;
			}
		}
		return null;
	}

	
	/**
	 * �����ն˺Ų�ѯ������Ⱥ���ն˶�Ӧ��ϵ��Ⱥ���б�
	 * 
	 * @param msid
	 * @return
	 */
	public List<String[]> getGrpMSinfo_notmsid(String ep, String msid) {
		if(ep != null && !ep.equals("")){
			List<String[]> list = new ArrayList<String[]>();
			if (msid != null && !msid.equals("")) {
				List listgrp = grp.getAllGrp_Info_not_Bymsid(msid, ep);
				if (listgrp != null) {
					for (int i = 0; i < listgrp.size(); i++) {
						TbGrpInfo grpinfo = (TbGrpInfo) listgrp.get(i);
						list.add(new String[] { grpinfo.getGrpid().toString(),
								grpinfo.getGrpid() + "-" + grpinfo.getGrpname()});
					}
					return list;
				}
			}
		}
		return null;
	}
	
	/**
	 * ��ѯָ���ն˿���ҵ�²�ѯ�������ն˶�Ӧ��ϵ��Ⱥ��
	 * 
	 * @param msid  �ն�
	 * @param mdep Ŀ����ҵ
	 * @param yep  Դ��ҵ
	 * @return
	 */
	public List<String[]> getGrmMsinfo(String msid,String yep) {
		List<String[]> list = new ArrayList<String[]>();
		if (yep != null && !yep.equals("")) {
			List listgrp = grp.getAllGrp_Info_not_BymsidGrp(msid,yep);
			if (listgrp != null) {
				for (int i = 0; i < listgrp.size(); i++) {
					TbGrpInfo grpinfo = (TbGrpInfo) listgrp.get(i);
					list.add(new String[] { grpinfo.getGrpid().toString(),
							grpinfo.getGrpid() + "-" + grpinfo.getGrpname() });
				}
				return list;
			}
		}
		return null;
	}
	
	
	
	/**
	 * ����GPS�Ų�ѯ�Ѿ�����GPS�����ն˶�Ӧ��ϵ���ն��б�
	 * 
	 * @param gpsid
	 * @return
	 */
	public List<String[]> getGPSMSinfo_gpsid(String gpsid) {
		List<String[]> list = new ArrayList<String[]>();
		if (gpsid != null && !gpsid.equals("")) {
			List listms = gpsmsmanager.getMs_info(gpsid);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					Map map = new HashMap();
					map = (Map)listms.get(i);
					list.add(new String[] { map.get("msid").toString(),
							map.get("msid") + "-" + map.get("msname") });
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * ����GPS�Ų�ѯ������GPS�����ն˶�Ӧ��ϵ���ն��б�
	 * @param gpsid
	 * @return
	 */
	public List<String[]> getGPSMSinfo_notgpsid(String ep, String gpsid) {
		List<String[]> list = new ArrayList<String[]>();
		if (gpsid != null && !gpsid.equals("")) {
			List listms = ms.getAllMs_Info_not_Bygpsid(ep,gpsid);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() });
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * ��ѯָ�������̺���ҵ�µ�Ⱥ����Ϣ(�ּ������õ�)
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<TbGrpInfo> getGrpinfoAll(String pagentid, String childagentid,
			String ep) {
		if (ep != null && !ep.equals("")) {
			List<TbGrpInfo> listgrp = grp.getAllGrp_Info(pagentid, childagentid, ep);
			if (listgrp != null) {
				System.out.println(">>>>>>>>>>>>>>>");
				TbGrpInfo grpinfo;
				for(int i=0;i<listgrp.size();i++)   
				{
					grpinfo=(TbGrpInfo)listgrp.get(i);
					System.out.println("<<<<<<<<"+grpinfo.getGrpid());
					System.out.println("<<<<<<<<"+grpinfo.getGrppid());
					System.out.println("<<<<<<<<"+grpinfo.getGrpname());
				}

				return listgrp;
			}
		}
		return null;
	}
	/**
	 * ��ѯָ�������̺���ҵ�µ�Ⱥ����Ϣ(�ּ������õ�)
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<TbGrpInfo> getBaseGrpIsNot(String pagentid, String childagentid,String ep) {
		if (ep != null && !ep.equals("")) {
			List<TbGrpInfo> listgrp = grp.getBaseGrp_Info(pagentid, childagentid, ep);
			if (listgrp != null) {
				System.out.println(">>>>>>>>>>>>>>>");
				TbGrpInfo grpinfo;
				for(int i=0;i<listgrp.size();i++)   
				{
					grpinfo=(TbGrpInfo)listgrp.get(i);
					System.out.println("<<<<<<<<"+grpinfo.getGrpid());
					System.out.println("<<<<<<<<"+grpinfo.getGrppid());
					System.out.println("<<<<<<<<"+grpinfo.getGrpname());
				}
				
				return listgrp;
			}
		}
		return null;
	}
	
	
	//getNonentityMsinfo_ByGrpid
	
	/*
	 * �����Ƿּ������õ���
	 */
	/**
	 * ��ѯָ����������ָ����ҵ�µ��ն���Ϣ
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<String[]> getNonentityMsinfo_ByGrpid(String epid,String grpid) {
		List<String[]> list = new ArrayList<String[]>();
		if (epid != null && !epid.equals("")) {
			List listms = ms.getNonentityMsinfo_ByGrpid(epid, grpid);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() });
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * ��ѯָ����������ָ����ҵ�µ��ն���Ϣ
	 * 
	 * @param pagentid
	 * @param childagentid
	 * @param ep
	 * @return
	 */
	public List<String[]> getMsinfoByTypeZero(String pagentid, String childagentid,
			String ep) {
		List<String[]> list = new ArrayList<String[]>();
		if (ep != null && !ep.equals("")) {
			List listms = ms.getAllMs_Info(pagentid, childagentid, ep);
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					if(msinfo.getMsType()!=0)
					 continue;
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() });
				}
				return list;
			}
		}
		return null;
	}
	/**
	 * �������з�������Ϣ
	 * @author luyun
	 * @date 2011-03-18
	 */
	public List<TabSysserverdbinfo> getDBInfo(){
		return tabSysserverdbinfo.getListBean(-1, -1);
	}
	
	public List<String[]> listMsInfoEpid(String epid){
			List<String[]> list=new ArrayList<String[]>();
			if(epid!=null&&!"".equals(epid)){
				List<TbMsInfo> tblist=ms.listMsInfoByEpId(new Integer(epid), 2,"1");
				if(tblist!=null&&tblist.size()>0){
					for (TbMsInfo tbMsInfo : tblist) {
						list.add(new String[]{tbMsInfo.getMsId().toString(),tbMsInfo.getMsId() + "-" + tbMsInfo.getMsName()});
					}
					return list;
				}
			}
		return null;
	}
	
	public static void main(String[] args) {
		ChildAgent agent=new ChildAgent();
//		agent.getNonentityMsinfo_ByGrpid(epid, grpid);
		agent.getBaseGrpIsNot("3", "-1", "8");
	}
}
