package com.elegps.tscweb.comm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.serivce.DeductManager;
import com.elegps.tscweb.serivce.impl.DeductManagerImpl;

public class TastDeduct extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;
	public TastDeduct(ServletContext context) {
		this.context = context;
	}
	@Override
	public void run() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String runtime = dateFormat.format(new Date());
		if (runtime.equals("12:00:00")) {
			//System.out.println("监听已经运行wl");
			isRunning = true;
			if (isRunning) {
				
				System.out.println(runtime+"终端冻结已经运行");
				DeductManager deduct;
				try {
					deduct = new DeductManagerImpl();
					deduct.getDeduct();
				} catch (MessageException e) {
					e.printStackTrace();
				}
				isRunning = false;
				System.out.println("终端冻结已经运行结束");
			}
		}

	}

}
