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
 * <font color='red'>�����ļ�tsc.xml����Ҫ��:</font>
 * <ul>
 * <li>���ݿ⣺db(���ݿ�����)������ѡ��oracle��mysql
 * <li>ϵͳͨ��ģʽ���ã�
 * <ol>
 * <li>tsc-grpBackout��ȡֵ0Ϊͨ�����������ͨ��Ⱥ�飬1Ϊ�ֶ����ͨ��Ⱥ�飻
 * <li>isResCall:�Ƿ�֪ͨ�����ն˸�̨,0Ϊ֪ͨ��1Ϊ��֪ͨ��
 * </ol>
 * <li>�˿����ã��˿�ȡֵ��Χ(1024<port<65535),��ģ��ͻ��˽��м��˿��Ƿ�ռ��
 * <ol>
 * <li>tcsp-port(tcsp�˿�)
 * <li>gps-port(gps�˿�)
 * </ol>
 * <li>�߳�ʱ�����ã�����Ϊ0���������ո񣬲������������ַ���ֻ������
 * <li>��־Ȩ�����ã�������Ӷ�����־Ȩ������
 * <ol>
 * <li>������ͨ����ʱ0Ϊ����ӡ��1Ϊ��ӡ
 * <li>�����õ���MOGPS��־ʱ:0Ϊ����ӡ��2λ��ӡ
 * </ol>
 * <li>��־�ļ�·�����ã��̶������޸�<br/>
 * &nbsp;excep-log·��Ϊ��/data/TSC/logs/error.log
 * <li>Id�̶�ֵ���ã���Χ(0~9){9}�������пո������ַ�
 * <ol>
 * <li>prifix-id�����Ⱥ�̨id��ǰ��λ�̶�ֵ
 * </ol>
 * </ul>
 * 
 * @author LuYun
 * 
 */
public class LoadXMLAction extends DispatchAction {
	/**
	 * ��ȡxml�����б�
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
	 * ��� һ��xmlԪ�ص�����
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
	 * �޸�һ�����ò���
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
