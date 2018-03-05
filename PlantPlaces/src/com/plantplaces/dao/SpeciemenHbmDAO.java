package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Speciemen;

/**
 * A Hibernate persistence implementation of our speciemen DAO
 * @author neli
 *
 */
@Named
public class SpeciemenHbmDAO extends PlantPlacesHbmDAO<Speciemen> implements ISpeciemenDAO {
	//Precisou do Named pq ISpeciemenDAO já foi injetado em outra classe
	
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

	@Override
	public void insert(Session session, Speciemen dto) throws Exception {
		// TODO Auto-generated method stub
		session.save(dto);
	}
}
