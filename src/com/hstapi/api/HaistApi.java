package com.hstapi.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.ToJsonString;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbAquaticIndex;
import com.elegps.tscweb.model.hstmodel.TbAquaticIndexData;
import com.elegps.tscweb.model.hstmodel.TbAquaticPrice;
import com.elegps.tscweb.model.hstmodel.TbAquaticPriceData;
import com.elegps.tscweb.model.hstmodel.TbAquaticType;
import com.elegps.tscweb.model.hstmodel.TbAquaticTypeData;
import com.elegps.tscweb.model.hstmodel.TbFisheryForecast;
import com.elegps.tscweb.model.hstmodel.TbFisheryForecastData;
import com.elegps.tscweb.model.hstmodel.TbForecastReference;
import com.elegps.tscweb.model.hstmodel.TbForecastReferenceVO;
import com.elegps.tscweb.model.hstmodel.TbGaleForecast;
import com.elegps.tscweb.model.hstmodel.TbGaleForecastData;
import com.elegps.tscweb.model.hstmodel.TbGaleHierrarchy;
import com.elegps.tscweb.model.hstmodel.TbGaleHierrarchyData;
import com.elegps.tscweb.model.hstmodel.TbTyphoonDetail;
import com.elegps.tscweb.model.hstmodel.TbTyphoonDetailVO;
import com.elegps.tscweb.model.hstmodel.TbTyphoonList;
import com.elegps.tscweb.model.hstmodel.TbTyphoonListData;
import com.elegps.tscweb.model.hstmodel.TbWeatherAnalysis;
import com.hstapi.util.http.PostBCE;
import com.hstapi.util.sign.Sign;
import com.hstapi.util.utils.JSONUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author liangz
 * 调用海上通接口类
 * time:2017-4-13
 */
public class HaistApi extends BaseAction{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = request.getParameter("CMD");
		
		if(cmd == null || cmd.equals("")){
			request.setAttribute("flag", "参数错误!");
			actionforward = mapping.findForward("default");
		}else if(cmd.equals("weasit")){		//天气形势分析
			actionforward = weaSit(mapping, request);
		}else if(cmd.equals("galerating")){		//大风预报等级对应颜色
			actionforward = galeRating(mapping, request);		
		}else if(cmd.equals("galemap")){		//大风预报分布图
			actionforward = galeMap(mapping, request);
		}else if(cmd.equals("fisheryfore")){	//渔场精细化预报
			actionforward = fisheryFore(mapping, request);
		}else if(cmd.equals("typhoonlist")){	//当前台风列表 
			actionforward = typhoonList(mapping, request);
		}else if(cmd.equals("typdetails")){		//台风详情
			actionforward = typDetails(mapping, request);
		}else if(cmd.equals("sst")){			//海温预报
			actionforward = getSSTForecast(mapping, request);
		}else if(cmd.equals("forecastref")){		//各家预报参考
			actionforward = forecastRef(mapping, request);
		}else if(cmd.equals("risefall")){			//水产信息——涨跌幅
			actionforward = riseFall(mapping, request);
		}else if(cmd.equals("aquaticindex")){		//水产信息——水产指数
			actionforward = aquaticIndex(mapping, request);
		}else if(cmd.equals("aquatictype")){		//水产信息——水产分类
			actionforward = aquaticType(mapping, request);
		}else if(cmd.equals("aquaticpriceinfo")){	//水产信息——水产价格信息
			actionforward = aquaticPriceInfo(mapping, request);
		}/*else if(cmd.equals("intermediary")){		//中转地址
			actionforward = intermediary(mapping, request);
		}*/
		else if(cmd.equals("waveAndHyhj")){	//海温海浪
			actionforward = waveAndHyhj(mapping, request);
		}
		
		return actionforward;
	}
	/**
	 * 天气形势分析  ????早上8:30之前推送
	 */
	private ActionForward weaSit(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/manualForecast";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String lon = request.getParameter("lon");	//经度
		String lat = request.getParameter("lat");	//纬度
		String msid = request.getParameter("msid");
		String param = null;
		Object result = null; //返回数据
		TbWeatherAnalysis wa = new TbWeatherAnalysis();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("lon", lon);
			params.put("lat", lat);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp+ "&lon=" + lon+ "&lat=" + lat;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		try {
			if(result!=null){ //数据库操作
				JSONObject jsonObject = JSONObject.fromObject(result);  
				TbWeatherAnalysis list = (TbWeatherAnalysis) JSONObject.toBean(jsonObject, TbWeatherAnalysis.class);
				wa.setMsid(msid);
				wa.setLon(lon);
				wa.setLat(lat);
				wa.setResult(list.getResult());
				waManager.save(wa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	/**
	 * 大风预报等级对应颜色
	 */
	private ActionForward galeRating(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/windAreaLegend";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String param = null;
		String result = null; //返回数据
		TbGaleHierrarchy gh = new TbGaleHierrarchy();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		try {
			if(result!=null){  //数据库操作
				TbGaleHierrarchyData data = JSONUtil.json2Object(result, TbGaleHierrarchyData.class);
				for (int i = 0; i < data.getResult().size(); i++) {
					gh.setColor(data.getResult().get(i).getColor());
					gh.setValue((data.getResult().get(i)).getValue());
					ghManager.save(gh);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 * 大风预报分布图 
	 */
	private ActionForward galeMap(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/windAreaDrawing";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String lon = request.getParameter("lon");	//经度
		String lat = request.getParameter("lat");	//纬度
		String msid = request.getParameter("msid");
		String param = null;
		String result = null; //返回数据
		TbGaleForecast gf = new TbGaleForecast();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("lon", lon);
			params.put("lat", lat);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&lon=" + lon + "&lat=" + lat;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		try {
			if(result!=null){	//数据库操作
			    TbGaleForecastData data = JSONUtil.json2Object(result, TbGaleForecastData.class);
			    for (int i = 0; i < data.getResult().size(); i++) {
			    	gf.setMsid(msid);
				    gf.setLat(lat);
				    gf.setLon(lon);
				    gf.setArea(data.getResult().get(i).getArea());
				    gf.setColor(data.getResult().get(i).getColor());
				    gf.setWindDirection(data.getResult().get(i).getWindDirection());
				    gfManager.save(gf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 * 渔场精细化预报  no
	 */ 
	private ActionForward fisheryFore(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/fisheryFineForecast";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String type = request.getParameter("type");   //类型   1-当前渔场（默认）2-精细化位置
		String scale = request.getParameter("scale");  //3-返回未来3天预报数据（默认） 5-返回未来5天预报数据  7-返回未来7天预报数据
		String lon = request.getParameter("lon");	//经度
		String lat = request.getParameter("lat");	//纬度
		String msid = "";
		String param = null;
		String result = null; //返回数据
		TbFisheryForecast ff = new TbFisheryForecast();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("type", type);
			params.put("scale", scale);
			params.put("lon", lon);
			params.put("lat", lat);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&type=" + type + "&scale=" + scale + "&lon=" +lon + "&lat=" + lat;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		/*try {
			if(result!=null){ //数据库操作
				TbFisheryForecastData data = JSONUtil.json2Object(result, TbFisheryForecastData.class);
			    for (int i = 0; i < data.getResult().get(0).getForecasts().size(); i++) {
			    	for (int j = 0; j < data.getResult().get(0).getForecasts().get(i).getData().size(); j++) {
			    		ff.setDate(data.getResult().get(0).getForecasts().get(i).getDate());
					    ff.setForecastDate(data.getResult().get(0).getForecasts().get(i).getData().get(j).getForecastDate());
					    ff.setLat(lat);
					    ff.setLon(lon);
					    ff.setName(data.getResult().get(0).getName());
					    ff.setPubtime(data.getResult().get(0).getPubTime());
					    ff.setScale(scale);
					    ff.setType(type);
					    ff.setWave(data.getResult().get(0).getForecasts().get(i).getData().get(j).getWave());
					    ff.setWindDirection(data.getResult().get(0).getForecasts().get(i).getData().get(j).getWindDirection());
					    ff.setWindLevel(data.getResult().get(0).getForecasts().get(i).getData().get(j).getWindLevel());
					    ff.setMsid(msid);
					    ffManager.save(ff);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("default");
	}
	
	/**
	 * 当前台风列表   ???有台风时自动更新 
	 */
	private ActionForward typhoonList(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/typhoonList";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String param = null;
		String result = null; //返回数据
		TbTyphoonList tl = new TbTyphoonList();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		try {
			if(result!=null){
				TbTyphoonListData data = JSONUtil.json2Object(result, TbTyphoonListData.class);
			    for (int i = 0; i < data.getResult().size(); i++) {
			    	tl.setNo(data.getResult().get(i).getNo());
				    tl.setNameEn(data.getResult().get(i).getNameEn());
				    tl.setNameCn(data.getResult().get(i).getNameCn());
				    tlManager.save(tl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 *台风详情
	 */
	private ActionForward typDetails(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/typhoonInfo";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String no = request.getParameter("no");	   //台风编号
		String param = null;
		String result = null; //返回数据
		TbTyphoonDetail td = new TbTyphoonDetail();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("no", no);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&no=" + no;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		try {
			if(result!=null){ //数据库操作
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(result);        
				com.alibaba.fastjson.JSONObject o2=JSON.parseObject((String) jsonObject.getString("result"));
				TbTyphoonDetailVO data = JSONUtil.json2Object((String) jsonObject.getString("result"), TbTyphoonDetailVO.class);
			    for (int i = 0; i < data.getLivePaths().size(); i++) {
					td.setFenglifor7(data.getLivePaths().get(i).getFenglifor7());
					if(data.getLivePaths().get(i).getFenglifor10() == null){
						td.setFenglifor10("");
					}else{
						td.setFenglifor10(data.getLivePaths().get(i).getFenglifor10());
					}
					
					td.setLatitude(data.getLivePaths().get(i).getLatitude());
					td.setLongitude(data.getLivePaths().get(i).getLongitude());
					td.setNo(no);
					td.setNowString(data.getLivePaths().get(i).getNowString());
					td.setPress(data.getLivePaths().get(i).getPress());
					td.setPublisher(data.getLivePaths().get(i).getPublisher());
					td.setTitle(o2.getString("title"));
					td.setTtype(data.getLivePaths().get(i).getTtype());
					td.setWind(data.getLivePaths().get(i).getWind());
					td.setWindLevel(data.getLivePaths().get(i).getWindLevel());
					td.setFlag(1);
					td.setDistance(data.getLivePaths().get(i).getDistance());
					if(data.getLivePaths().get(i).getInterval()==null){
						td.setInterval("");
					}else{
						td.setInterval(data.getLivePaths().get(i).getInterval());
					}
					
					tdManager.save(td);
				}
			    for (int i = 0; i < data.getForecastPaths().size(); i++) {
					td.setFenglifor7(data.getLivePaths().get(i).getFenglifor7());
					if(data.getLivePaths().get(i).getFenglifor10() == null){
						td.setFenglifor10("");
					}else{
						td.setFenglifor10(data.getLivePaths().get(i).getFenglifor10());
					}
					td.setLatitude(data.getLivePaths().get(i).getLatitude());
					td.setLongitude(data.getLivePaths().get(i).getLongitude());
					td.setNo(no);
					td.setNowString(data.getLivePaths().get(i).getNowString());
					td.setPress(data.getLivePaths().get(i).getPress());
					td.setPublisher(data.getLivePaths().get(i).getPublisher());
					td.setTitle(o2.getString("title"));
					td.setTtype(data.getLivePaths().get(i).getTtype());
					td.setWind(data.getLivePaths().get(i).getWind());
					td.setWindLevel(data.getLivePaths().get(i).getWindLevel());
					td.setFlag(0);
					td.setDistance(data.getLivePaths().get(i).getDistance());
					if(data.getLivePaths().get(i).getInterval()==null){
						td.setInterval("");
					}else{
						td.setInterval(data.getLivePaths().get(i).getInterval());
					}
					tdManager.save(td);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 * 海温预报   ??8:00-10:00不定期更新
	 */
	private ActionForward getSSTForecast(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/sstForecast";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String param = null;
		String result = null; //返回数据
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 * 各家预报参考    no
	 */
	private ActionForward forecastRef(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/variousPredictionReference";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String forecastSource = request.getParameter("forecastSource");	//NMC-中央气象台	GDMO-广东省气象台 SDHY-山东省海洋气象台 KTW-香港天文台
		String forecastType = request.getParameter("forecastType");
		String param = null;
		String result = null; //返回数据
		TbForecastReference fr = new TbForecastReference();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("forecastSource", forecastSource);
			params.put("forecastType", forecastType);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&forecastSource=" +forecastSource+"&forecastType=" +forecastType;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		/*try {
			if(result!=null){ //数据库操作
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(result);        
				com.alibaba.fastjson.JSONObject o2=JSON.parseObject((String) jsonObject.getString("result"));
				com.alibaba.fastjson.JSONObject o3=JSON.parseObject((String) o2.getString("content"));
			    TbForecastReferenceVO data = JSONUtil.json2Object((String) o2.getString("content"), TbForecastReferenceVO.class);
			    for (int i = 0; i < data.getContent().size(); i++) {
			    	for (int j = 0; j < data.getContent().get(i).getData().size(); j++) {
						fr.setPubtime(o2.getString("pubtime"));
						fr.setTitle(o2.getString("title"));
						fr.setType(o2.getString("type"));
						fr.setForecastSource(data.getContent().get(i).getData().get(j).getForecastSource());
						fr.setForecastSource(data.getContent().get(i).getData().get(j).getForecastSource());
						fr.setForecastHour(data.getContent().get(i).getData().get(j).getForecastHour());
						fr.setVisibility(data.getContent().get(i).getData().get(j).getVisibility());
						fr.setWeather(data.getContent().get(i).getData().get(j).getWeather());
						fr.setWindDirectionStr(data.getContent().get(i).getData().get(j).getWindDirectionStr());
						fr.setWindLive(data.getContent().get(i).getData().get(j).getWindLive());
						frManager.save(fr);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("default");
	}
	
	/**
	 * 水产信息——涨跌幅
	 */
	private ActionForward riseFall(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/aquaticInfoByApplies";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String type = request.getParameter("type");	//1-涨幅 2-跌幅
		String param = null;
		String result = null; //返回数据
		TbAquaticPrice ap = new TbAquaticPrice();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("type", type);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&type=" +type;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		/*try {
			if(result!=null){ //数据库操作
			    TbAquaticPriceData data = JSONUtil.json2Object(result, TbAquaticPriceData.class);
				for (int i = 0; i < data.getResult().size(); i++) {
					ap.setType(type);
					ap.setName(data.getResult().get(i).getName());
					ap.setPrice(data.getResult().get(i).getPrice());
					ap.setSort(data.getResult().get(i).getSort());
					aqupManager.save(ap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("default");
	}
	
	/**
	 * 水产信息——水产指数 
	 */
	private ActionForward aquaticIndex(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/aquaticInfoByIndex";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String type = request.getParameter("type");	//3-周价格指数 4-月价格指数 5-景气指数 6-成交量
		String param = null;
		String result = null; //返回数据
		TbAquaticIndex ai = new TbAquaticIndex();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("type", type);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&type=" +type;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		/*try {
			if(result!=null){ //数据库操作
			    TbAquaticIndexData data = JSONUtil.json2Object(result, TbAquaticIndexData.class);
			    for (int i = 0; i < data.getResult().size(); i++) {
					ai.setAmout(data.getResult().get(i).getAmout());
					ai.setPubtime(data.getResult().get(i).getPubtime());
					ai.setTime(data.getResult().get(i).getTime());
					ai.setType(data.getResult().get(i).getType());
					aiManager.save(ai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("default");
	}
	/**
	 * 水产信息——水产分类
	 */
	private ActionForward aquaticType(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/aquaticInfoByClass";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String param = null;
		String result = null; //返回数据
		TbAquaticType at = new TbAquaticType();
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp;
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		/*try {
			if(result!=null){ //数据库操作
			    TbAquaticTypeData data = JSONUtil.json2Object(result, TbAquaticTypeData.class);
			    for (int i = 0; i < data.getResult().size(); i++) {
					at.setClassId(data.getResult().get(i).getClassId());
					at.setName(data.getResult().get(i).getName());
					//at.setPid(list.get(i).getPid());
					atManager.save(at);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("default");
	}
	/**
	 * 水产信息——水产价格信息  
	 */
	private ActionForward aquaticPriceInfo(ActionMapping mapping,HttpServletRequest request){
		String url = "http://service.seaweather.cn/aquaticInfoByPrice";	//接口路径
		String appkey ="acd4c32dee16e24e";	
	    String appSecret="8b9c6183584e79d2";
		String timestamp = request.getParameter("timestamp");  //时间戳 APP端传来
		String classId = request.getParameter("classId");	//分类id
		String param = null;
		String result = null; //返回数据
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("appkey", appkey);
			params.put("classId", classId);
			
			String sign = Sign.getSignature(params, appSecret,timestamp);   //URL参数签名
			param = "sign=" + sign + "&appkey=" + appkey + "&timestamp=" + timestamp + "&classId=" + classId;
			System.out.println(param);
			result = PostBCE.postApache(url, param);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	
	/**
	 * 海温和海浪
	 */
	private ActionForward waveAndHyhj(ActionMapping mapping,HttpServletRequest request) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String strDate = sdf.format(new Date());
			String path = "47.89.48.61/waveImages/";
			String waveCfx = path + strDate + "sl.gif"; // 海浪实况
			String waveC24 =  path + strDate + "_" + strDate + "-24.gif";	// 海浪24小时
			String waveC48 = path + strDate + "_" + strDate + "-48.gif";	// 海浪48小时
			String waveC72 = path + strDate + "_" + strDate + "-72.gif";	// 海浪72小时
			String hyhj24 = path + strDate + "_024_T_0000.png"; // 海温24小时
			String hyhj48 = path + strDate + "_048_T_0000.png"; // 海温48小时
			String hyhj72 = path + strDate + "_072_T_0000.png"; // 海温72小时
			String hyhj96 = path + strDate + "_096_T_0000.png"; // 海温96小时
			String hyhj120 = path + strDate+ "_120_T_0000.png"; // 海温120小时
			
			request.setAttribute("flag", ToJsonString.packagePara("waveCfx", waveCfx,"waveC24",waveC24,"waveC48",waveC48,"waveC72",waveC72
					,"hyhj24",hyhj24,"hyhj48",hyhj48,"hyhj72",hyhj72,"hyhj96",hyhj96,"hyhj120",hyhj120));
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}
	/**
	 * 推送——天气形势分析
	 */
	
	/**
	 * 推送——强天气报警提醒
	 */
	
	/**
	 * 地址中转
	 */
	/*private ActionForward intermediary(ActionMapping mapping,HttpServletRequest request){
		String url = request.getParameter("url");
		Object result = null; //返回数据
		try {
			HashMap<String,String> params = new HashMap<String,String>();
			String[] urlParams = url.split("[?]");
			String[] arrSplit=urlParams[1].split("[&]");
			for(String strSplit:arrSplit){
				String[] arrSplitEqual=null; 
				arrSplitEqual= strSplit.split("[=]");
				//解析出键值
				if(arrSplitEqual.length>1){
					params.put(arrSplitEqual[0], arrSplitEqual[1]);//正确解析
				}else{
					if(arrSplitEqual[0]!=""){
						params.put(arrSplitEqual[0], ""); 	//只有参数没有值，不加入
					}
				}
			}
			
			String param = Sign.getSignature(params, null);   //URL参数签名
			System.out.println(url);
			result = PostBCE.postApache(url, null);
			request.setAttribute("flag", result);
		} catch (Exception e) {
			request.setAttribute("flag", "获取不到参数");
			e.printStackTrace();
		}
		return mapping.findForward("default");
	}*/
}
