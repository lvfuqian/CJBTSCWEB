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
	 * 处理excel文件内容添加终端信息
	 * @param excel 文件流
	 * @param excelName 文件名
	 * @param pagentid 一级代理
	 * @param childagentid 二级代理
	 * @param ep 企业id
	 * @param ext 文件后缀（ xls、xlsx）
	 * @return
	 */
	public String readExcel(InputStream excel,String excelName,String pagentid,String childagentid,String ep,String ext){

		if(ep.equals("")){
			return "请选择企业";
		}
		
		List list =new ArrayList();
		String czId="";
		String errorId="";
		if(ext.equals("xls") || ext.equals("xlsx")){
			list = ReadExcel.getWorkbook(ext, excel);//读取excel表数据封住为list数据
		}else{
			return "文件格式错误！";
		}
		
		if(list == null)
			return "Excel文件无数据！";
		
		for(int i = 0;i<list.size();i++){//遍历list数据
			String[] s = (String[])list.get(i);
			String msid = msid11or21(s[0]);
			if(msid.equals("false")){//错误id格式
				errorId += s[0]+"、";
			}else{
				TbMsInfo msinfo = ms.getTBMsinfoby_msid(msid);
				if(msinfo != null){//终端ID已存在
					czId += s[0]+"、";
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
		int count = list.size();//id总数量
		int czIdnum = czId.split("、").length;
		int errorIdnum = errorId.split("、").length;//错误格式数量与id已存在数量
		if(czId.length() == 0){
			czIdnum = 0;
		}
		if(errorId.length() == 0){
			errorIdnum = 0;
		}
		int oknum = count - czIdnum - errorIdnum;
		outString = "总终端数量“ "+count+" ”个\n" 
					+ "成功添加“" + oknum +"” 个\n" 
					+ "错误id格式 “" + errorIdnum +"” 个 \n"
					+ "已存在id “" + czIdnum +"” 个 ";
		
		return outString;
	}
	
	/**
	 * msid转换，可传21位或11位
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
	 * 上传广告图片，返回图片路径
	 * @param ft
	 * @param imgformat
	 * @return
	 */
	public String upload(FileTransfer ft,String imgformat){
		String ggg=imgformat.substring(imgformat.lastIndexOf("."), imgformat.length());
		WebContext  wc = WebContextFactory.get();
		//获得应用路径  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
		String d=df.format(new Date());
		String advFile ="advFile/image/"+d;
		
		String headImages = wc.getSession().getServletContext().getRealPath("/")+advFile;
		File file = new File(headImages);
		//如果文件夹不存在，就创建      
		if( !file.exists()){
			file.mkdirs();
		}      
		try{
			//文件上传核心代码
			InputStream uploadFile = ft.getInputStream();
			//文件名
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
	 * 验证msid是否存在
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
	 * 查询一级代理商下的所有二级代理商
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
	 * 查询指定代理商下的企业信息
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
	 * 查询指定代理商下的指定企业信息
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
	 * 查询指定代理商下指定企业下的终端信息
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
	 * 查询指定代理商和企业下的群组信息
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
	 * 根据群组号查询已经存在群组终端对应关系的终端列表
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
	 * 根据代理商查询待分配的终端列表
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
	 * 根据群组号查询不存在群组终端对应关系的终端列表
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
	 * 根据群组号查询群组终端对应关系的终端列表
	 * 终端批量充值页面显示
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
						String bc = "";//颜色控制
						if(msinfo.getMsMoneyTime()!= null){
							Calendar c = Calendar.getInstance();//获得一个日历的实例  
							//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							c.setTime(new Date());//设置日历时间   
							c.add(Calendar.MONTH,1);//在日历的月份上增加月
							Date date = c.getTime();
							//System.out.println(format2.format(c.getTime()));
							if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
								bc = "#7B7B7B";
							}else if(msinfo.getMsMoneyTime().before(new Date())){//在系统时间以前，表示欠费，
								bc = "red";
							}else if(msinfo.getMsMoneyTime().after(date)){//在系统时间一个月以上，表示正常，
								bc = "green";
							}
						}else{
							bc = "red";
						}
						
						list.add(new String[] { msinfo.getMsId().toString(),
								msinfo.getMsId() + "-" + msinfo.getMsName() + "-套餐（" 
								+ msinfo.getPf().getPfType() + "）-" +msinfo.getMsMoneyTime(),bc });
					}
					return list;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据群组号查询群组终端对应关系的正常终端列表
	 * 终端套餐转移页面显示
	 * @param grpid
	 * @return
	 */
	public List<String[]> getGrpZCMSAllinfo_bygrpid(String ep, String grpid) {
		if (ep != null && !ep.equals("")) {
			List<String[]> list = new ArrayList<String[]>();
			if (grpid != null && !grpid.equals("")) {
				List listms = ms.getAllMs_Info_notQF_Bygrpid(grpid, ep);//获取不欠费的终端
				if (listms != null) {
					for (int i = 0; i < listms.size(); i++) {
						TbMsInfo msinfo = (TbMsInfo) listms.get(i);
						String bc = "";//颜色控制
						if(msinfo.getMsMoneyTime()!= null){
							Calendar c = Calendar.getInstance();//获得一个日历的实例  
							//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							c.setTime(new Date());//设置日历时间   
							c.add(Calendar.MONTH,1);//在日历的月份上增加月
							Date date = c.getTime();
							//System.out.println(format2.format(c.getTime()));
							if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
								bc = "#7B7B7B";
							}else if(msinfo.getMsMoneyTime().before(new Date())){//在系统时间以前，表示欠费，
								bc = "red";
							}else if(msinfo.getMsMoneyTime().after(date)){//在系统时间一个月以上，表示正常，
								bc = "green";
							}
						}else{
							bc = "red";
						}
						
						list.add(new String[] { msinfo.getMsId().toString(),
								msinfo.getMsId() + "-" + msinfo.getMsName() + "-套餐（" 
								+ msinfo.getPf().getPfType() + "）-" +msinfo.getMsMoneyTime(),bc });
					}
					return list;
				}
			}
		}
		return null;
	}
	/**
	 * 根据  msID  模糊查询   用户下所有终端的列表
	 * 终端批量  充值   页面显示
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
				listms = ms.getMsInfo_byEpid(epid,msId);//得到企业的终端
			}else{
				listms = ms.getMsInfo_byEpid(epid,msId);//得到企业的终端
			}
			if (listms != null) {
				for (int i = 0; i < listms.size(); i++) {
					TbMsInfo msinfo = (TbMsInfo) listms.get(i);
					String bc = "";//颜色控制
					if(msinfo.getMsMoneyTime()!= null){
						Calendar c = Calendar.getInstance();//获得一个日历的实例  
						//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						c.setTime(new Date());//设置日历时间   
						c.add(Calendar.MONTH,1);//在日历的月份上增加月
						Date date = c.getTime();
						//System.out.println(format2.format(c.getTime()));
						if(msinfo.getMsMoneyTime().before(new Date()) && msinfo.getMsMoneyTime().after(date)){
							bc = "#7B7B7B";
						}else if(msinfo.getMsMoneyTime().before(new Date())){//在系统时间以前，表示欠费，
							bc = "red";
						}else if(msinfo.getMsMoneyTime().after(date)){//在系统时间一个月以上，表示正常，
							bc = "green";
						}
					}else{
						bc = "red";
					}
					
					list.add(new String[] { msinfo.getMsId().toString(),
							msinfo.getMsId() + "-" + msinfo.getMsName() + "-套餐（" 
							+ msinfo.getPf().getPfType() + "）-" +msinfo.getMsMoneyTime(),bc });
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 根据  msID  模糊查询   用户下所有终端的列表
	 * 终端批量增加  群组信息  页面显示
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
				listms = ms.getMsInfo_byEpid(epid,msId);//得到企业的终端
			}else{
				listms = ms.getMsInfo_byEpid(epid,msId);//得到企业的终端
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
	 * 根据终端号获取终端代理商信息
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
//	 * 根据终端号获取终端 群组 信息
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
	 * 根据代理商获取终端企业信息
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
	 * 根据终端号查询已经存在群组终端对应关系的群组列表
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
	 * 根据终端号查询不存在群组终端对应关系的群组列表
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
	 * 查询指定终端跨企业下查询不存在终端对应关系的群组
	 * 
	 * @param msid  终端
	 * @param mdep 目的企业
	 * @param yep  源企业
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
	 * 根据GPS号查询已经存在GPS厂商终端对应关系的终端列表
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
	 * 根据GPS号查询不存在GPS厂端终端对应关系的终端列表
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
	 * 查询指定代理商和企业下的群组信息(分级调度用到)
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
	 * 查询指定代理商和企业下的群组信息(分级调度用到)
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
	 * 以下是分级调度用到的
	 */
	/**
	 * 查询指定代理商下指定企业下的终端信息
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
	 * 查询指定代理商下指定企业下的终端信息
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
	 * 返回所有服务器信息
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
