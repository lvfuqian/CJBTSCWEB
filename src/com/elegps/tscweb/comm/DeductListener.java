package com.elegps.tscweb.comm;

import java.util.Timer;//��ʱ����
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class DeductListener implements ServletContextListener
{
  private Timer timer = null;
  public void contextInitialized(ServletContextEvent event)
  {//�������ʼ������������tomcat������ʱ�����������������������ʵ�ֶ�ʱ������
      timer = new Timer(true);
      event.getServletContext().log("��ʱ��������");//�����־������tomcat��־�в鿴��
      timer.schedule(new TastDeduct(event.getServletContext()),0,1000);//���� exportHistoryBean��0��ʾ�������ӳ٣�1000��ʾÿ��1��ִ������60*60*1000��ʾһ��Сʱ��
      event.getServletContext().log("�Ѿ��������");
  }
  public void contextDestroyed(ServletContextEvent event)
  {//������رռ��������������������ٶ�ʱ����
      timer.cancel();
      event.getServletContext().log("��ʱ������");
  }
}