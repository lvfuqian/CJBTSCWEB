package com.elegps.tscweb.action.base;



import org.apache.struts.action.Action;

import com.elegps.tscweb.comm.MD5;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TabSysserverdbinfo;
import com.elegps.tscweb.model.Vehicle;
import com.elegps.tscweb.serivce.AdvManager;
import com.elegps.tscweb.serivce.AdvanceManager;
import com.elegps.tscweb.serivce.AgentManager;
import com.elegps.tscweb.serivce.AppPayManager;
import com.elegps.tscweb.serivce.AquaticIndexManager;
import com.elegps.tscweb.serivce.AquaticPriceManager;
import com.elegps.tscweb.serivce.AquaticTypeManager;
import com.elegps.tscweb.serivce.DdbManager;
import com.elegps.tscweb.serivce.DdmsMsManager;
import com.elegps.tscweb.serivce.EnterPriseManager;
import com.elegps.tscweb.serivce.FisheryForecastManager;
import com.elegps.tscweb.serivce.ForecastReferenceManager;
import com.elegps.tscweb.serivce.GaleForecastManager;
import com.elegps.tscweb.serivce.GaleHierrarchyManager;
import com.elegps.tscweb.serivce.GpsManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpManager;
import com.elegps.tscweb.serivce.GrpMsManager;
import com.elegps.tscweb.serivce.LogManager;
import com.elegps.tscweb.serivce.LoginManager;
import com.elegps.tscweb.serivce.MenuManager;
import com.elegps.tscweb.serivce.MoneyManager;
import com.elegps.tscweb.serivce.MsChargeManager;
import com.elegps.tscweb.serivce.MsControlBiz;
import com.elegps.tscweb.serivce.MsManager;
import com.elegps.tscweb.serivce.ParamsManager;
import com.elegps.tscweb.serivce.PhoneTypeManager;
import com.elegps.tscweb.serivce.PhoneUserManage;
import com.elegps.tscweb.serivce.PhonekoufeiManager;
import com.elegps.tscweb.serivce.QuestionManager;
import com.elegps.tscweb.serivce.RoleManager;
import com.elegps.tscweb.serivce.RoleMenuManager;
import com.elegps.tscweb.serivce.TrafficBiz;
import com.elegps.tscweb.serivce.TyphoonDetailManager;
import com.elegps.tscweb.serivce.TyphoonListManager;
import com.elegps.tscweb.serivce.UserManager;
import com.elegps.tscweb.serivce.UserRoleManager;
import com.elegps.tscweb.serivce.WeatherAnalysisManager;
import com.elegps.tscweb.serivce.PFManager;
import com.elegps.tscweb.serivce.YxCrmLogManager;
import com.elegps.tscweb.serivce.impl.AdvManagerImpl;
import com.elegps.tscweb.serivce.impl.AdvanceManagerImpl;
import com.elegps.tscweb.serivce.impl.AgentManagerImpl;
import com.elegps.tscweb.serivce.impl.AppPayManagerImpl;
import com.elegps.tscweb.serivce.impl.AquaticIndexManagerImpl;
import com.elegps.tscweb.serivce.impl.AquaticPriceManagerImpl;
import com.elegps.tscweb.serivce.impl.AquaticTypeManagerImpl;
import com.elegps.tscweb.serivce.impl.DdbManagerImpl;
import com.elegps.tscweb.serivce.impl.DdmsMsManagerImpl;
import com.elegps.tscweb.serivce.impl.EnterPriseManagerImpl;
import com.elegps.tscweb.serivce.impl.FisheryForecastManagerImpl;
import com.elegps.tscweb.serivce.impl.ForecastReferenceManagerImpl;
import com.elegps.tscweb.serivce.impl.GaleForecastManagerImpl;
import com.elegps.tscweb.serivce.impl.GaleHierrarchyManagerImpl;
import com.elegps.tscweb.serivce.impl.GpsManagerImpl;
import com.elegps.tscweb.serivce.impl.GpsMsManagerImpl;
import com.elegps.tscweb.serivce.impl.GrpManagerImpl;
import com.elegps.tscweb.serivce.impl.GrpMsManagerImpl;
import com.elegps.tscweb.serivce.impl.LogManagerImpl;
import com.elegps.tscweb.serivce.impl.LoginManagerImpl;
import com.elegps.tscweb.serivce.impl.MenuManagerImpl;
import com.elegps.tscweb.serivce.impl.MoneyManagerImpl;
import com.elegps.tscweb.serivce.impl.MsChargeManagerImpl;
import com.elegps.tscweb.serivce.impl.MsControlBizImpl;
import com.elegps.tscweb.serivce.impl.MsManagerImpl;
import com.elegps.tscweb.serivce.impl.ParamsManagerImpl;
import com.elegps.tscweb.serivce.impl.PhoneTypeManagerImpl;
import com.elegps.tscweb.serivce.impl.PhoneUserManagerImpl;
import com.elegps.tscweb.serivce.impl.PhonekoufeiManagerImpl;
import com.elegps.tscweb.serivce.impl.QuestionManagerImpl;
import com.elegps.tscweb.serivce.impl.RoleManagerImpl;
import com.elegps.tscweb.serivce.impl.RoleMenuManagerImpl;
import com.elegps.tscweb.serivce.impl.ServerDBManagerImpl;
import com.elegps.tscweb.serivce.impl.TyphoonDetailManagerImpl;
import com.elegps.tscweb.serivce.impl.TyphoonListManagerImpl;
import com.elegps.tscweb.serivce.impl.UserManagerImpl;
import com.elegps.tscweb.serivce.impl.UserRoleManagerImpl;
import com.elegps.tscweb.serivce.impl.VehicleBizImpl;
import com.elegps.tscweb.serivce.impl.WeatherAnalysisManagerImpl;
import com.elegps.tscweb.serivce.impl.PFManagerImpl;
import com.elegps.tscweb.serivce.impl.YxCrmLogManagerImpl;


public class BaseAction extends Action
{
	protected static MsManager msmanager;
	protected static GrpManager grpmanager;
	protected static GrpMsManager grpmsmanager;
	protected static GpsManager gpsmanager;
	protected static GpsMsManager gpsmsmanager;
	protected static DdmsMsManager ddmsmsmanager;
	protected static MenuManager menumanager;
	protected static RoleManager rolemanager;
	protected static RoleMenuManager rolemenumanager;
	protected static UserManager usermanager;
	protected static UserRoleManager userrolemanager;	
	protected static LoginManager loginmanager;
	protected static AgentManager agentmanger;
	protected static EnterPriseManager epmanger;
	protected static AdvanceManager advancemanger;
	protected static MsChargeManager mschargermanger;
	protected static QuestionManager questionmanger;
	protected static DdbManager<TabSysconfig> ddbManager;
	protected static DdbManager<TabSysserverdbinfo> serverDBManager;
	protected static TrafficBiz<Vehicle> vbiz;
	protected static MsControlBiz msControlBiz;
	protected static LogManager logManager;
	protected static MD5 md5;
	protected static PhoneUserManage phoneUserManage;
	
	protected static PFManager pfManager;
	protected static MoneyManager moneyManager;
	protected static AppPayManager apManager;
	protected static AdvManager advManager;
	protected static ParamsManager paramsManager;
	protected static YxCrmLogManager yxCrmLog; 
	protected static PhoneTypeManager ptManager;
	protected static PhonekoufeiManager pkManager; 
	
	protected static AquaticIndexManager aiManager;
	protected static AquaticPriceManager aqupManager;
	protected static AquaticTypeManager atManager;
	protected static FisheryForecastManager ffManager;
	protected static ForecastReferenceManager frManager;
	protected static GaleForecastManager gfManager;
	protected static GaleHierrarchyManager ghManager;
	protected static TyphoonDetailManager tdManager;
	protected static TyphoonListManager tlManager;
	protected static WeatherAnalysisManager waManager;
	static 
	{
		try
		{
			msmanager = new MsManagerImpl();
			grpmanager=new GrpManagerImpl();
			grpmsmanager=new GrpMsManagerImpl();
			gpsmanager=new GpsManagerImpl();
			gpsmsmanager=new GpsMsManagerImpl();
			ddmsmsmanager=new DdmsMsManagerImpl();
			menumanager=new MenuManagerImpl();
			rolemanager=new RoleManagerImpl();
			rolemenumanager=new RoleMenuManagerImpl();
			usermanager=new UserManagerImpl();
			userrolemanager=new UserRoleManagerImpl();
			loginmanager=new LoginManagerImpl();
			agentmanger=new AgentManagerImpl();
			epmanger=new EnterPriseManagerImpl();
			advancemanger=new AdvanceManagerImpl();
			mschargermanger=new MsChargeManagerImpl();
			questionmanger=new QuestionManagerImpl();
			ddbManager=new DdbManagerImpl();
			serverDBManager=new ServerDBManagerImpl();
			vbiz=new VehicleBizImpl();
			msControlBiz=new MsControlBizImpl();
			logManager=new LogManagerImpl();
			md5=new MD5();
			phoneUserManage=new PhoneUserManagerImpl();
			pfManager = new PFManagerImpl();
			moneyManager = new MoneyManagerImpl();
			apManager = new AppPayManagerImpl();
			advManager = new AdvManagerImpl();
			paramsManager = new ParamsManagerImpl();
			yxCrmLog = new YxCrmLogManagerImpl();
			ptManager = new PhoneTypeManagerImpl();
			pkManager = new PhonekoufeiManagerImpl();
			aiManager = new AquaticIndexManagerImpl();
			aqupManager = new AquaticPriceManagerImpl();
			atManager = new AquaticTypeManagerImpl();
			ffManager = new FisheryForecastManagerImpl();
			frManager = new ForecastReferenceManagerImpl();
			gfManager = new GaleForecastManagerImpl();
			ghManager = new GaleHierrarchyManagerImpl();
			tdManager = new TyphoonDetailManagerImpl();
			tlManager = new TyphoonListManagerImpl();
			waManager = new WeatherAnalysisManagerImpl();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
