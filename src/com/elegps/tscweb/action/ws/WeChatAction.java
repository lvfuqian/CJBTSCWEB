package com.elegps.tscweb.action.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.DateFormat;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.tscconfig.FilePathConfig;
import com.wechatpay.demo.Demo;
import com.wechatpay.demo.Notify;
import com.wechatpay.demo.WxPayResult;

public class WeChatAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String r = request.getMethod();
				System.out.println("΢�Żص�����ʼ");
				WxPayResult wr = Notify.notify(request,response);//��֤�ص�����
				wr.setTotalFee(Demo.changeF2Y(wr.getTotalFee()));
				
				if("SUCCESS".equals(wr.getResultCode())){//���׳ɹ�
					TbAppPayInfo ap =apManager.findApInfoById(wr.getOutTradeNo());
					if(ap!=null){
						System.out.println("΢��app֧���̻�����ɹ���");
						request.setAttribute("flag", "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
						return mapping.findForward("default");
					}
					
					TbAppPayInfo apInfo = new TbAppPayInfo();
					apInfo.setId(wr.getOutTradeNo());
					apInfo.setPayMoney(wr.getTotalFee());
					apInfo.setPayNum(wr.getOpenid());
					apInfo.setSellerId("");
					apInfo.setTradeNo(wr.getTransactionId());
					String[] b = wr.getAttach().split("\\|");
					String prifix_id = FilePathConfig.getMSId();
					apInfo.setPayer(prifix_id + b[0]);
					apInfo.setPhoneOrMs(prifix_id + b[1].replace(" ", ""));
					//�������������ҵ���߼�����д�������´�������ο�������
					System.out.println("----"+wr.getAttach()+"-----");
					System.out.println("----"+apInfo.getPhoneOrMs()+"-----");
					//֧��ʱ��
//					String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
//					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date date=sdf.parse(gmt_payment);
					apInfo.setTime(DateFormat.parse(wr.getTimeEnd(), "yyyyMMddHHmmss"));
					apInfo.setType(1);
					
					WsAction  ws = new WsAction();
					ws.appPayNotify(apInfo);
					
					System.out.println("΢��app֧���̻�����ɹ���");
					request.setAttribute("flag", "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return mapping.findForward("default");
				}
				
				System.out.println("΢��app֧��ʧ��");
				request.setAttribute("flag", "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[]]></return_msg></xml>");
				return mapping.findForward("default");
	}
//	
//	public void doGet(){
//		System.out.println("get");
//	}
//	
//	public void doPost(){
//		System.out.println("post");
//	}
}
