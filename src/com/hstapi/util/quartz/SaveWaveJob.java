package com.hstapi.util.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SaveWaveJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String newDate = sdf.format(new Date());
		/**
		 * 海浪
		 */
		String wave = "http://www.nmefc.gov.cn/chanpin/hyzh/wave";

		String waveCfx = "http://www.nmefc.gov.cn/chanpin/hyzh/wave/c-fx/sl.gif"; // 海浪实况
		// 24小时海浪 http://www.nmefc.gov.cn/chanpin/hyzh/wave/c-24/20170414-24.gif
		String waveC24 = wave + "/c-24/" + newDate + "-24.gif";
		// 48小时海浪 http://www.nmefc.gov.cn/chanpin/hyzh/wave/c-48/20170414-48.gif
		String waveC48 = wave + "/c-48/" + newDate + "-48.gif";
		// 72小时海浪 http://www.nmefc.gov.cn/chanpin/hyzh/wave/c-72/20170414-72.gif
		String waveC72 = wave + "/c-72/" + newDate + "-72.gif";
		/**
		 * 海温
		 */
		String hyhj24 = "http://www.nmefc.gov.cn/chanpin/hyhj/c-xbp/024_T_0000.png"; // 海温24小时
		String hyhj48 = "http://www.nmefc.gov.cn/chanpin/hyhj/c-xbp/048_T_0000.png"; // 海温48小时
		String hyhj72 = "http://www.nmefc.gov.cn/chanpin/hyhj/c-xbp/072_T_0000.png"; // 海温72小时
		String hyhj96 = "http://www.nmefc.gov.cn/chanpin/hyhj/c-xbp/096_T_0000.png"; // 海温96小时
		String hyhj120 = "http://www.nmefc.gov.cn/chanpin/hyhj/c-xbp/120_T_0000.png"; // 海温120小时

		String[] paths = new String[9];
		paths[0] = waveCfx;
		paths[1] = waveC24;
		paths[2] = waveC48;
		paths[3] = waveC72;
		paths[4] = hyhj24;
		paths[5] = hyhj48;
		paths[6] = hyhj72;
		paths[7] = hyhj96;
		paths[8] = hyhj120;
		String imgPath = "";
		for (int i = 0; i < paths.length; i++) {
			try {
				String filename = newDate + "_" + paths[i].substring(paths[i].lastIndexOf("/") + 1, paths[i].length());
				System.out.println(filename);
				imgPath = SaveWaveJob.class.getClassLoader().getResource("").getPath();
				imgPath = imgPath.split("WEB-INF")[0];
				imgPath = imgPath + "waveImages/";
				download(paths[i], filename, imgPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 图片存指定路径
	 * 
	 * @param urlString
	 *            图片路径
	 * @param filename
	 *            保存图片名字
	 * @param savePath
	 *            保存图片路径
	 * @throws Exception
	 */
	public static void download(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为10s
		con.setConnectTimeout(10 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}
}
