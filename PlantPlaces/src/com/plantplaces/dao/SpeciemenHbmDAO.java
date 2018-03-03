package com.plantplaces.dao;

import javax.inject.Named;

import org.hibernate.Session;

import com.plantplaces.dto.Speciemen;

/**
 * A Hibernate persistence implementation of our speciemen DAO
 * @author neli
 *
 */
@Named
public class SpeciemenHbmDAO implements ISpeciemenDAO {
	//Precisou do Named pq ISpeciemenDAO já foi injetado em outra classe
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.ISpeciemenDAO#insert(com.plantplaces.dto.Speciemen)
	 */
	@Override
	public void insert(Speciemen speciemen) throws Exception{
		//Save the plant to the database
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(speciemen);
		
		session.getTransaction().commit();
	}
}
