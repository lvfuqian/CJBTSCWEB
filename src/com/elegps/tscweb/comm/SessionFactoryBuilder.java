package com.elegps.tscweb.comm;



import org.hibernate.*;
import org.hibernate.cfg.*;

import com.elegps.tscweb.exception.MessageException;

public class SessionFactoryBuilder
{
	private SessionFactory sessionFactory;
	private static SessionFactoryBuilder sfb;

	private SessionFactoryBuilder()
	{
	}

    public static SessionFactoryBuilder instance()
    {
        if (sfb == null)
        {
            sfb = new SessionFactoryBuilder(); 
        }
        return sfb;
    }

	public SessionFactory getSessionFactory()throws MessageException
	{
		if (sessionFactory == null)
		{
			try
			{
				Configuration configuration = new Configuration().configure();
				sessionFactory = configuration.buildSessionFactory();
			}
			catch (Throwable ex)
			{
				System.out.println("��ʼ��SessionFactory�����쳣..." + ex);
				throw new MessageException("��ʼ��SessionFactory�����쳣...");
			}
		}
		return sessionFactory;
	}

}
