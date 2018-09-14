package com.elegps.tscweb.serivce.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.CnToPYAlpha;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.comm.SessionFactoryBuilder;
import com.elegps.tscweb.dao.DdmsMsDaoFactory;
import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.MsDaoControlFactory;
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.impl.DdmsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoControlHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbMsControlInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.serivce.MsManager;

public class MsManagerImpl implements MsManager {

	private MsDaoFactory msdao;
	private GrpMsDaoFactory grpmsdao;
	private GpsMsDaoFactory gpsmsdao;
	private DdmsMsDaoFactory ddmsmsdao;
	private MsDaoControlFactory controldao;
	

	public MsManagerImpl() throws MessageException {
		try {
			msdao = new MsDaoHibernate();
			grpmsdao = new GrpMsDaoHibernate();
			gpsmsdao = new GpsMsDaoHibernate();
			ddmsmsdao = new DdmsMsDaoHibernate();
			controldao=new MsDaoControlHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}

	public Boolean deleteMs(String[] msidlist) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < msidlist.length; i++) {
				String emp = msidlist[i];
				TbMsInfo tbms = msdao.get(sess, emp);
				String hql="DELETE  TbMsInfoExt  WHERE msId=?  ";
				String control_hql="DELETE  TbMsControlInfo  WHERE msId=? ";
				if (tbms != null) {
					msdao.update(sess, hql, emp);
					// tbms.setFlag(0);
					// sess.update(tbms);
					sess.delete(tbms);
					// 删除TbGrpMsListInfo对象的中ms_id等于emp
					grpmsdao.deleteGrpMsInfoByMs_id(sess, emp);
					// 删除TbGpsMsListInfo对象的中ms_id等于emp
					gpsmsdao.deleteGpsMsInfoByMs_id(sess, emp);
					// 删除调度表中的信息
					ddmsmsdao.deletems_id(sess, emp);
					//删除控制表对应数据
					controldao.executeHql(sess, control_hql, emp);
					
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	/**
	 * OK 创建一条终端用户信息
	 *
	 * @param msId
	 *            终端号码
	 * @param msName
	 *            终端名称
	 * @param passwd
	 *            终端密码
	 * @param msType
	 *            终端所属类型
	 * @param flag
	 *            终端用户的状态
	 * @return 新创终端的主键,如果创建失败，返回-1。
	 */
	public String createMs(String msId, int mssl, String msName, String passwd,
						   int msType, int flag, String epid, String ms_level, String modid,
						   TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,String c03,
						   String c04,String netWorkType,String msCategory,int pagentid) {
		Transaction tx = null;
		String ret = null;
		TbMsInfo ms =null;
		TbMsInfoExt msExt = null;
		TbMsControlInfo controlInfo=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			StringBuffer oldmsidsub = new StringBuffer(msId);
			String newmsid = "";
			String qmsid = oldmsidsub.substring(0, 16);
			for (int i = 0; i < mssl; i++) {
				String msid = oldmsidsub.substring(16, 21);
				if(mssl>1){
					int msint=Integer.parseInt(msid) + i;
					msid = String.valueOf(msint);
				}
				String tt = String.valueOf(msid);
				int j = 5 - tt.length();
				for (int s = 0; s < j; s++) {
					tt = "0" + tt;
				}
				if (tt.length() > 5) {
					newmsid = qmsid + tt.substring(tt.length()-5, tt.length());
				} else {
					newmsid = qmsid + tt;
				}
				TbMsInfo tbms = msdao.get(sess, newmsid);
				Date date = new Date();
				if (tbms == null) {
					ms = new TbMsInfo();
					msExt = new TbMsInfoExt();
					controlInfo=new TbMsControlInfo();
					ms.setMsId(newmsid);
					if (mssl < 2) {
						ms.setMsName(msName);
					} else {
						ms.setMsName(msName + newmsid.substring(newmsid.length()-3,newmsid.length()));
					}
					ms.setPasswd(passwd);
					ms.setMsType(msType);
					ms.setFlag(flag);
					ms.setOnlineStatus(0);
					ms.setCallSize(0);
					ms.setListenTime(0);
					ms.setCallTime(0);
					ms.setListenSize(0);
					ms.setCreate_time(date);
					ms.setGrpCalling("");
					ms.setEpid(Integer.parseInt(epid));
					ms.setMsLevel(Integer.parseInt(ms_level));
					ms.setMobileid(modid);
					ms.setIs_Arrearage(0);
					ms.setPackage_fee(0);
					ms.setPf(package_fee);
					ms.setMsMessage_post(1);
					ms.setBalance_cash(0);
					ms.setMsdf(ms_df);
					ms.setMscall(call);
					ms.setMileageas(mileageas);
					ms.setC01(memo);
					ms.setC03(c03);
					ms.setC04(c04);
					ms.setNetWorkType(netWorkType);
					ms.setC07(CnToPYAlpha.getFive(ms.getMsName(), 5));
					ms.setC09(4);
					ms.setC10("60");
					ms.setC11("0");
					ms.setMsCategory(msCategory);
					ms.setAgentId(pagentid);
					ret = msdao.save(sess, ms);
					//对应关系表记录添加
					msExt.setMsId(newmsid);
					msExt.setSimNum("");
					msExt.setDeviceNum("");
					msExt.setEnterpriseId(new Integer(epid));
					msdao.create(sess, msExt);

					controlInfo.setMsId(newmsid);
					controlInfo.setIsLEDBright(0);
					controlInfo.setIsLEDReset(0);
					controlInfo.setEnterpriseId(Integer.parseInt(epid));
					controlInfo.setR01(0);
					controlInfo.setR02((float) 0.0);
					controlInfo.setR03((float) 0.0);
					controlInfo.setR04(150);
					controlInfo.setR05("");
					controldao.add(sess, controlInfo);
				}
			}
			tx.commit();
			return ret;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();

		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * OK 创建一条终端用户信息
	 * 
	 * @param msId
	 *            终端号码
	 * @param msName
	 *            终端名称
	 * @param passwd
	 *            终端密码
	 * @param msType
	 *            终端所属类型
	 * @param flag
	 *            终端用户的状态
	 * @return 新创终端的主键,如果创建失败，返回-1。
	 */
	public String createMs(String msId, int mssl, String msName, String passwd,
			int msType, int flag, String epid, String ms_level, String modid,
			TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,String c03,
			String c04,String netWorkType,String msCategory,int pagentid
			,String roleType,String familyNumbers) {
		Transaction tx = null;
		String ret = null;
		TbMsInfo ms =null;
		TbMsInfoExt msExt = null;
		TbMsControlInfo controlInfo=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			StringBuffer oldmsidsub = new StringBuffer(msId);
			String newmsid = "";
			String qmsid = oldmsidsub.substring(0, 16);
			for (int i = 0; i < mssl; i++) {
				String msid = oldmsidsub.substring(16, 21);
				if(mssl>1){
					int msint=Integer.parseInt(msid) + i;
					msid = String.valueOf(msint);
				}
				String tt = String.valueOf(msid);
				int j = 5 - tt.length();
				for (int s = 0; s < j; s++) {
					tt = "0" + tt;
				}
				if (tt.length() > 5) {
					newmsid = qmsid + tt.substring(tt.length()-5, tt.length());
				} else {
					newmsid = qmsid + tt;
				}
				TbMsInfo tbms = msdao.get(sess, newmsid);
				Date date = new Date();
				if (tbms == null) {
					ms = new TbMsInfo();
					msExt = new TbMsInfoExt();
					controlInfo=new TbMsControlInfo();
					ms.setMsId(newmsid);
					if (mssl < 2) {
						ms.setMsName(msName);
					} else {
						ms.setMsName(msName + newmsid.substring(newmsid.length()-3,newmsid.length()));
					}
					ms.setPasswd(passwd);
					ms.setMsType(msType);
					ms.setFlag(flag);
					ms.setOnlineStatus(0);
					ms.setCallSize(0);
					ms.setListenTime(0);
					ms.setCallTime(0);
					ms.setListenSize(0);
					ms.setCreate_time(date);
					ms.setGrpCalling("");
					ms.setEpid(Integer.parseInt(epid));
					ms.setMsLevel(Integer.parseInt(ms_level));
					ms.setMobileid(modid);
					ms.setIs_Arrearage(0);
					ms.setPackage_fee(0);
					ms.setPf(package_fee);
					ms.setMsMessage_post(1);
					ms.setBalance_cash(0);
					ms.setMsdf(ms_df);
					ms.setMscall(call);
					ms.setMileageas(mileageas);
					ms.setC01(memo);
					ms.setC03(c03);
					ms.setC04(c04);
					ms.setNetWorkType(netWorkType);
					ms.setC07(CnToPYAlpha.getFive(ms.getMsName(), 5));
					ms.setC09(4);
					ms.setC10("60");
                    ms.setC11("0");
                    ms.setMsCategory(msCategory);
                    ms.setAgentId(pagentid);
                    ms.setRoleType(Integer.parseInt(roleType));
                    ms.setFamilyNumbers(familyNumbers);
					ret = msdao.save(sess, ms);
					//对应关系表记录添加
					msExt.setMsId(newmsid);
					msExt.setSimNum("");
					msExt.setDeviceNum("");
					msExt.setEnterpriseId(new Integer(epid));
					msdao.create(sess, msExt);
					
					controlInfo.setMsId(newmsid);
					controlInfo.setIsLEDBright(0);
					controlInfo.setIsLEDReset(0);
					controlInfo.setEnterpriseId(Integer.parseInt(epid));
					controlInfo.setR01(0);
					controlInfo.setR02((float) 0.0);
					controlInfo.setR03((float) 0.0);
					controlInfo.setR04(150);
					controlInfo.setR05("");
					controldao.add(sess, controlInfo);
				}
			}
			tx.commit();
			return ret;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();

		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 返回特定页面所有终端用户信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @return 指定页的用户的状态为1(正常)全部终端用户信息 按终端类型排序
	 */
	public List<TbMsInfo> getAllMs_typeByPage(int pageNo, int pagesize) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findAllMs_typeByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据每页记录数，总记录数获取总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录数
	 * @return 计算得到的总页数
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	/**
	 * 返回特定指定条件终端用户信息
	 * 
	 * @param msId
	 *            终端号码
	 * @param msType
	 *            终端所属类型
	 * @param Flag
	 *            终端用户的状态
	 * @return 指定页的全部终端用户信息
	 */
	public List getTbMsInfo(String msId, int msType, int Flag) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getTbMsInfo(sess, msId, msType, Flag);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					System.out.println(me.getMsId());
					System.out.println(me.getMsName());
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 返回特定指定条件终端用户信息
	 * 
	 * @param msid
	 *            终端号码
	 */
	public TbMsInfo getTBMsinfoby_msid(String msid) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			//tx = sess.beginTransaction();
			TbMsInfo msinfo = msdao.get(sess, msid);
			if (msinfo != null) {
				//tx.commit();
				return msinfo;
			} else {
				//tx.commit();
				return null;
			}
		} catch (Exception e) {
			//if (null != tx)
				//tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 返回特定指定条件终端用户类型信息个数
	 * 
	 * @param ms_type
	 *            终端类型
	 */
	public int getMs_typeCount(int ms_type) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_typeCount(sess, ms_type);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 返回特定指所有终端用户类型信息个数
	 * 
	 */
	public int getAllMs_typeCount() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findAllMs_typeCount(sess);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 返回特定终端用户类型页面所有终端用户信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param ms_type
	 *            终端用户类型
	 * @return 指定终端用户类型,用户的状态为1(正常)页的全部终端用户信息
	 */
	public List<TbMsInfo> getMs_typeByPage(int pageNo, int pagesize, int ms_type) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_typeByPage(sess, pageNo, pagesize, ms_type);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {

			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 获取终端用户在线态信息数量
	 * 
	 * @param ms_Online
	 *            终端用户类型
	 * @return 终端用户在线态信息的数量 对应总记录数
	 */
	public int getMs_OnlineCount(int ms_Online) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_OnlineCount(sess, ms_Online);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}

		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 获取所有终端用户在线态信息数量
	 * 
	 * @return 终端用户在线态信息的数量
	 */
	public int getAllMsOnline_allCount() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findAllMs_OnlineCount(sess);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	public List<TbMsInfo> getAllMsOnline_allByPage(int pageNo, int pagesize) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findAllOnlineByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 返回特定终端用户类型页面所有终端用户信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param ms_onlineStatus
	 *            终端用户在线状态
	 * @return 指定终端用户类型页的全部终端用户信息
	 */
	public List<TbMsInfo> getMs_OnlineByPage(int pageNo, int pagesize,
			int ms_onlineStatus) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_OnlineByPage(sess, pageNo, pagesize,
					ms_onlineStatus);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据flag做查询(所有状态包括有效，件效）
	 * 
	 * @return 终端用户flag做查询的数量
	 */
	public int getAllMs_flagCount() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_flagAllCount(sess);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 获取终端用户状态信息数量
	 * 
	 * @param flag
	 *            终端用户状态对应总记录数
	 * @return 终端用户状态为flag信息的数量
	 */
	public int getMs_flagCount(int flag) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_flagAllCount(sess, flag);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 根据ms_id做模糊查询
	 * 
	 * @return 终端用户ms_id做模糊查询的数量
	 */
	public int getMsms_idCount(String ms_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_idAllCount(sess, ms_id);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 根据ms_id做模糊查询
	 * 
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	public List<TbMsInfo> getTBMsinfoby_msid(int pageNo, int pagesize,
			String ms_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_idlistByPage(sess, pageNo, pagesize, ms_id);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				System.out.println("result.size()=" + result.size());
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据flag做查询(所有状态包括有效，件效）
	 * 
	 * @return 终端用户flag做查询的数量
	 */
	public List<TbMsInfo> getAllMs_flagByPage(int pageNo, int pagesize) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_flaglistByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 返回特定终端所有用户状态为flag页面所有终端用户信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param flag
	 *            终端用户状态
	 * @return 指定终端用户状态为flag页的全部用户信息
	 */
	public List<TbMsInfo> getMs_flagByPage(int pageNo, int pagesize, int flag) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_flaglistByPage(sess, pageNo, pagesize, flag);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				System.out.println("result.size()=" + result.size());
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据ms_id更新记录列表 更新记录列表
	 * 
	 * @param ms_id
	 * @param ms_name
	 * @param ms_type
	 */
	public String modifyMs(String ms_id, String ep_id, String ms_name,
			int ms_type, int ms_flag, int delddms, String modid,
			String password, int ms_level, TbPFInfo packagefee,int ms_df, int call,int mileageas,
			String memo,String ismobile,String c04,String msCategory,int pagentid,String nwType) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if (delddms == 1) { // 需要删除调度信息表中的记录
				ddmsmsdao.deletems_id(sess, ms_id);
			}
			String hql="";
			TbMsInfo tbms = msdao.get(sess, ms_id);
			if (tbms != null) {
				tbms.setEpid(Integer.parseInt(ep_id));
				tbms.setMsName(ms_name);
				tbms.setMsType(ms_type);
				tbms.setFlag(ms_flag);
				tbms.setMobileid(modid);
				tbms.setPasswd(password);
				tbms.setMsLevel(ms_level);
				//tbms.setIs_Arrearage(0);
				//tbms.setPackage_fee(packagefee);
				tbms.setMsdf(ms_df);
				tbms.setMscall(call);
				tbms.setMileageas(mileageas);
				tbms.setC01(memo);
				tbms.setC03(ismobile);
				tbms.setC04(c04);
				tbms.setMsCategory(msCategory);
				tbms.setC07(CnToPYAlpha.getFive(tbms.getMsName(), 5));
				tbms.setAgentId(pagentid);
				tbms.setNetWorkType(nwType);
				sess.update(tbms);
				tx.commit();
				return tbms.getMsId();
			} else {
				tx.commit();
				return null;
			}

		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据ms表中所有信息(falg=1)记录列表
	 */
	public List<TbMsInfo> getAllMs_Info(String pagentid,String childagentid,String ep) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_Info(sess, pagentid,childagentid, ep);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 终端表查询数量
	 * 
	 * @param user_type
	 *            终端类型
	 * @param statue
	 *            在线(离线)状态
	 * @param flag
	 *            有效状态
	 * @param ms_id
	 *            终端号码
	 * @return 终端用户模糊查询的数量
	 */
	public int getMs_sertch(String user_type, String statue, String flag,
			String ms_id, String ms_name, String pagentid, String childagentid,
			String ep, String ismobile,String arrearage) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = msdao.findMs_sertchCount(sess, user_type, statue, flag, ms_id,
							ms_name, pagentid, childagentid, ep,ismobile, arrearage);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * @param user_type
	 *            终端类型
	 * @param statue
	 *            在线(离线)状态
	 * @param flag
	 *            有效状态
	 * @param ms_id
	 *            终端号码
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	public List<TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String user_type, String statue, String flag, String ms_id,
			String ms_name, String pagentid, String childagentid, String ep,String ismobile,
			String arrearage) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_sertchByPage(sess, pageNo, pagesize,
					user_type, statue, flag, ms_id, ms_name, pagentid,
					childagentid, ep, ismobile,arrearage);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public List getAllMs_Info_not_Bygrpid(String grpid, String ep) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			List ml = msdao.findMs_Info_bygrpid(sess, grpid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	public List getAllMs_InfoBygrpid(String grpid, String ep) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			List ml = msdao.findMs_Falg_bygrpid(sess, grpid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List getAllMs_Info_notQF_Bygrpid(String grpid, String ep) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
			String dateNow = dateFormat.format(new Date());
			List ml = msdao.find_notQF_Ms_Info_bygrpid(sess, grpid,ep,dateNow);//不欠费的终端
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List getAllMs_Info_not_Bygpsid(String ep, String gpsid) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMs_Info_bygpsid(sess, ep, gpsid);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public Boolean msdj(String[] msidlist) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < msidlist.length; i++) {
				String emp = msidlist[i];
				TbMsInfo tbms = msdao.get(sess, emp);
				if (tbms != null) {
					tbms.setFlag(0);
					sess.update(tbms);
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}
	
	public List<TbMsInfo> msInfoList(String[] msidlist) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbMsInfo> list = new ArrayList<TbMsInfo>();
			for (int i = 0; i < msidlist.length; i++) {
				String msid = msidlist[i];
				TbMsInfo tbms = msdao.get(sess, msid);
				if (tbms != null) {
					list.add(tbms);
				}
			}
			tx.commit();
			return list;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public List getMsInfo_byEpid(String ep_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getMsInfo_byEpidao(sess, ep_id);
			if (ml != null && ml.size() > 0) {
				return ml;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	
	public List getMsInfo_byAid(String aid,int msCount) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getMsInfo_byAid(sess, aid,msCount);
			if (ml != null && ml.size() > 0) {
				return ml;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List getMsInfo_byEpid(StringBuffer ep_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getMsInfo_byEpidao(sess, ep_id);
			if (ml != null && ml.size() > 0) {
				return ml;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List getMsInfo_byEpid(StringBuffer ep_id,String ms) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getMsInfo_byEpidao(sess, ep_id,ms);
			if (ml != null && ml.size() > 0) {
				return ml;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}	
	//-------------------------------分级调度用到的-----------------------------------
	/**epid（企业ID）和grpid(群组id) 
	 * 
	 */
	public List<TbMsInfo> getNonentityMsinfo_ByGrpid(String epid,String grpid) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.getNonentityMsinfo_ByGrpid(sess, epid,grpid);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	
	/**
	 * 修改对应关系记录
	 */
	public int updateMsExt(String msId, String SIM_Num, String deviceNum,int entid, int carPlateColor,String newmsId) {
		Session session=HibernateUtil.currentSession();
		String hql="UPDATE TbMsInfoExt SET msId=?,simNum=?,deviceNum=?,enterpriseId=?,carPlateColor=? WHERE msId=?";
		 return msdao.update(session, hql,newmsId,SIM_Num,deviceNum,entid,carPlateColor,msId);
	}

	
	/**
	 * 获取一条对象
	 * @author luyun
	 * @date 2011-03-14
	 */
	public TbMsInfoExt getExtById(String msId) {
		Session session=HibernateUtil.currentSession();
		String hql="FROM TbMsInfoExt  WHERE msId=?";
		return (TbMsInfoExt) msdao.getExtById(session, hql, msId);
	}


	
	public void update(String hql, Object... obj) {
		Session session=HibernateUtil.currentSession();
		 msdao.update(session, hql, obj);
	}

	
	public int getMsById(String hql, Object... obj) {
		Session session=HibernateUtil.currentSession();
		return msdao.getMsById(session, hql, obj);
	}
	
   public  List<TbMsInfo> listMsInfo(){
	   Session session =HibernateUtil.currentSession();
	   List <TbMsInfo> list=msdao.listMsInfo(session);
	   return list;
   }

	
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool) {
		Session session=HibernateUtil.currentSession();
		return msdao.listMsInfoByEpId(session, epid,mstype,bool);
	}
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool,String msid) {
		Session session=HibernateUtil.currentSession();
		return msdao.listMsInfoIsByEpId(session, epid,mstype,bool,msid);
	}

	@Override
	public List<TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String userType, String statue, String flag, String msId,
			String msName, String pagentid, String childagentid, String ep,
			String ismobile, String arrearage, int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = msdao.findMsListInfo_sertchByPage(sess, pageNo, pagesize, userType, statue, flag, msId, msName, pagentid, childagentid, ep, ismobile, arrearage, roleId, agentid, epId);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Boolean updateMs(List<TbMsInfo> msList) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if (msList != null && msList.size() > 0) {
				for (int i=0;i<msList.size();i++) {
					TbMsInfo ms = new TbMsInfo();
					ms =msList.get(i);
					msdao.update(sess, ms);
				}
				tx.commit();
				return true;
			} else {
				tx.commit();
				return false;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public Boolean updateFamilyNumbers(TbMsInfo info) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if (info != null) {
				msdao.update(sess, info);
				tx.commit();
				return true;
			} else {
				tx.commit();
				return false;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public Boolean updatePwd(String msID, String pwd) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if (msID != null && !msID.equals("")) {
				TbMsInfo msinfo = msdao.get(sess, msID);
				if(msinfo!=null){
					msinfo.setPasswd(pwd);
					msdao.update(sess, msinfo);
				}else{
					tx.commit();
					return false;
				}
				tx.commit();
				return true;
			} else {
				tx.commit();
				return false;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public Boolean update(TbMsInfo msinfo) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				if(msinfo!=null){
					msdao.update(sess, msinfo);
				}else{
					tx.commit();
					return false;
				}
				tx.commit();
				return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public String findMsIdByPhone(String phone) {
		System.out.println("mIpl"+phone);
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			String msId = "";
				if(phone!=null){
					msId = msdao.findMsIdByPhone(sess, phone);
				}else{
					tx.commit();
					return null;
				}
				tx.commit();
				System.out.println("mIpl"+msId);
				return msId;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


}
