package com.hstapi.util.quartz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DelWaveJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 当前日期减去7天
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		String strDate = sdf.format(cal.getTime());
		String imgPath = "";
		imgPath = DelWaveJob.class.getClassLoader().getResource("").getPath();
		imgPath = imgPath.split("WEB-INF")[0];
		imgPath = imgPath + "waveImages/";
		File files = new File(imgPath);
		File temp = null;
		File[] filelist = files.listFiles();
		if (filelist.length > 0) {
			for (int i = 0; i < filelist.length; i++) {
				temp = filelist[i];
				if (temp.getName().startsWith(strDate)) {
					temp.delete();// 删除文件
					System.out.println("删除成功");
				}
			}
		}else{
			System.out.println("没有文件");
		}
	}
}
