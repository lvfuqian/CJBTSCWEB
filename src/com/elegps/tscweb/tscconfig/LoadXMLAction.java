package com.elegps.tscweb.tscconfig;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import PluSoft.Utils.JSON;

/**
 * <font color='red'>配置文件tsc.xml参数要求:</font>
 * <ul>
 * <li>数据库：db(数据库类型)有两种选择oracle或mysql
 * <li>系统通话模式设置：
 * <ol>
 * <li>tsc-grpBackout：取值0为通话结束即拆除通话群组，1为手动拆除通话群组；
 * <li>isResCall:是否通知调度终端复台,0为通知，1为不通知；
 * </ol>
 * <li>端口设置：端口取值范围(1024<port<65535),用模拟客户端进行检测端口是否被占用
 * <ol>
 * <li>tcsp-port(tcsp端口)
 * <li>gps-port(gps端口)
 * </ol>
 * <li>线程时间设置：不能为0，负数，空格，不能配置其他字符，只能整型
 * <li>日志权限设置：可以添加多条日志权限配置
 * <ol>
 * <li>配置普通参数时0为不打印，1为打印
 * <li>当配置的是MOGPS日志时:0为不打印，2位打印
 * </ol>
 * <li>日志文件路径设置：固定不能修改<br/>
 * &nbsp;excep-log路径为：/data/TSC/logs/error.log
 * <li>Id固定值设置：范围(0~9){9}，不能有空格，其他字符
 * <ol>
 * <li>prifix-id：调度后台id的前九位固定值
 * </ol>
 * </ul>
 * 
 * @author LuYun
 * 
 */
public class LoadXMLAction extends DispatchAction {
	/**
	 * 获取xml内容列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type=request.getParameter("type");
		
		List<XmlBean> list = ConfigXML.listXml(type);
		List<LogType> logList=ConfigXML.logTypeList();
		
		request.setAttribute("list", list);
		request.setAttribute("logList", logList);
		
		return mapping.findForward("listok");
	}
	/**
	 * 获得 一个xml元素的内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findXmlBean(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String name=request.getParameter("name");
		
		try{
			XmlBean xml=ConfigXML.getXMLBean(name);
			List<LogType> logList=ConfigXML.logTypeList();
			
			request.setAttribute("xmlBean", xml);
			request.setAttribute("logList", logList);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mapping.findForward("took");
	}
	/**
	 * 修改一个配置参数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("update Method...");
		String json=request.getParameter("submitData");
		System.out.println(json);
		HashMap<String, String> data= (HashMap<String, String>) JSON.Decode(json);
		String eName=(String) (data.get("eName")!=null?data.get("eName"):"");
		String nName=(String) (data.get("nName")!=null?data.get("nName"):"");
		String eValue=(String) (data.get("eValue")!=null?data.get("eValue"):"");
		String eType=(String) (data.get("eType")!=null?data.get("eType"):"");
		String eComment=(String) (data.get("eComment")!=null?data.get("eComment"):"");
		
		XmlBean xmlBean=new XmlBean();
		xmlBean.seteName(nName);
		xmlBean.seteValue(eValue);
		xmlBean.seteType(eType);
		xmlBean.seteComment(eComment);
		System.out.println("eName\t"+eName);
		
//		ConfigXML.updateXml(xmlBean,eName);
		
		return null;
	}
}
