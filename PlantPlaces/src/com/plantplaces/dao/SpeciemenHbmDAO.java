package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Plant;
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
	
	@Override
	public List<Speciemen> fetchByPlantId(int plantId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Create the query [From usa o nome da classe associada e não da tabela]
		Query query = session.createQuery("from Speciemen where plantId = :plantId");
		query.setParameter("plantId", plantId);
		List list = query.list();
		List<Speciemen> speciemen = Collections.checkedList(list, Speciemen.class);
		
		return speciemen;
	}
}
