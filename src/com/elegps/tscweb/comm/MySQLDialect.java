package com.elegps.tscweb.comm;

import java.sql.Types;

import org.hibernate.Hibernate;

public class MySQLDialect extends org.hibernate.dialect.MySQLDialect {
	public MySQLDialect() {
		registerHibernateType(Types.REAL, Hibernate.BIG_INTEGER.getName());
		registerHibernateType(Types.REAL, Hibernate.FLOAT.getName());
		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	}
}
