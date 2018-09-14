package com.elegps.tscweb.comm;

import java.util.Timer;//定时器类
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class DeductListener implements ServletContextListener
{
  private Timer timer = null;
  public void contextInitialized(ServletContextEvent event)
  {//在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
      timer = new Timer(true);
      event.getServletContext().log("定时器已启动");//添加日志，可在tomcat日志中查看到
      timer.schedule(new TastDeduct(event.getServletContext()),0,1000);//调用 exportHistoryBean，0表示任务无延迟，1000表示每隔1秒执行任务，60*60*1000表示一个小时。
      event.getServletContext().log("已经添加任务");
  }
  public void contextDestroyed(ServletContextEvent event)
  {//在这里关闭监听器，所以在这里销毁定时器。
      timer.cancel();
      event.getServletContext().log("定时器销毁");
  }
}