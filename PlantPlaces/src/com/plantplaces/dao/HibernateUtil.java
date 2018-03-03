package com.plantplaces.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static final SessionFactory buildSessionFactory(){
		try{
			//Create the session factory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			//Log the exception
			System.err.println("Initial sessionFactory create failed " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		//Close caches and connection pools
		getSessionFactory().close();
	}
}
