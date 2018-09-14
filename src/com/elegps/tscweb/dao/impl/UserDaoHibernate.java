package com.elegps.tscweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.UserDaoFactory;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class UserDaoHibernate implements UserDaoFactory {

	public Integer findUser_SearchCount(Session sess, String user_name,int agent_id) {
		String hql = "select count(m.userName) from TbUserInfo m where m.userId!=0" ;
		if (user_name.equals("")) { // 表示为全部用户
		}else{
			hql=hql+" and m.userName like '%"+user_name+ "%'";
		}

		if(agent_id != -1){
			hql=hql+" and m.Agent_Id = "+agent_id;
		}
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int menucount = (Integer) obj;
				return menucount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List findUserInfo_SearchByPage(Session sess, int pageNo,
			int pageSize, String user_name,int agent_id) {
		Connection conn = null; 
		Statement ps = null; 
		ResultSet rs=null; 
		List res=new ArrayList();
		try {
		conn=sess.connection();
		int offset = (pageNo - 1) * pageSize;
	      String sql = "select X.User_ID,X.User_Name,ifnull(Y.Role_Name,'无') ,ifnull(Z.Agent_Name,'全部代理商'),ifnull(E.Enterprise_Name,'全部用户单位') from tb_User_Info X left join (select A.User_ID,A.Role_SID,B.Role_Name from tb_Role_User_Info A,tb_Role_Info B where A.Role_SID=B.Role_ID) Y on X.User_ID=Y.User_ID  left join(select * from tb_Agent_Info) Z on Z.Agent_Id=X.Agent_Id left join(select * from tb_EnterPrise_Info)E on E.Enterprise_Id=X.Enterprise_Id where X.User_id!=0";

	      if (!(user_name.equals("")))
	      {
	        sql = sql + " and X.user_name like '%" + user_name + "%'";
	      }
	      //wanglei 代理商查询用户条件  @agent_id
	      if(agent_id!= -1){
	    	  sql = sql + " and X.Agent_Id =" + agent_id + " or Z.Agent_PId = " + agent_id;
	      }
	      sql = sql + " order by X.create_time desc limit " + offset + "," + pageSize;
		ps=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
		rs=ps.executeQuery(sql); 
		while(rs.next()){
			Map map = new HashMap();
			map.put("userid",rs.getString(1));
			map.put("username", rs.getString(2));
			map.put("rolename", rs.getString(3));
			map.put("agent_name", rs.getString(4));
			map.put("enterprise_name", rs.getString(5));
			res.add(map);
		}
		if (res != null && res.size()>0) {
			return res;
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			if(rs!=null){
//		       try {
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			if(ps!=null){
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		}
		return null;
	}

	public TbUserInfo get_byname(Session sess, String user_name) {
		try {
			List user = sess.createQuery("from TbUserInfo m where m.userName='"+user_name+"'").list();
			if (user != null && user.size() > 0) {
				return (TbUserInfo) user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		return null;
	}

	public void delete(Session sess, TbUserInfo m) {
		try {
			sess.delete(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public TbUserInfo get(Session sess, String user_id) {
		try {
			List user = sess.createQuery("from TbUserInfo m where m.userId="+user_id).list();
			if (user != null && user.size() > 0) {
				return (TbUserInfo) user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public TbUserInfo get_byname(Session sess, String user_id, String user_name) {
		try {
			List user = sess.createQuery("from TbUserInfo m where m.userName='"+user_name+"' and userId!="+user_id).list();
			if (user != null && user.size() > 0) {
				return (TbUserInfo) user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public List findAllUserInfo(Session sess) {
		String hql="from TbUserInfo m where m.userId!=0  order  by m.create_time desc";
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return list;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public List findNotUserInfo(Session sess) {
		String hql="from TbUserInfo u where u.userId not in(select ur.userId from TbRoleUserInfo ur) order by u.create_time desc";
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return list;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public List getAllAgentInfo(Session sess) {
		String hql="from TbAgentInfo";
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return list;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public TbUserInfo get_User(Session sess, String name) {
		try {
			List user = sess.createQuery("from TbUserInfo m where m.userName='"+name+"'" ).list();
			if (user != null && user.size() > 0) {
				return (TbUserInfo) user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	@Override
	public void updateInfo(Session sess, TbUserInfo user) {
		try {
			sess.saveOrUpdate(user);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}

}
