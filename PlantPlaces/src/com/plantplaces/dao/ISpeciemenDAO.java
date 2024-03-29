package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Speciemen;

public interface ISpeciemenDAO {

	List<Speciemen> fetchByPlantId(int plantId);

	void insert(Session session, Speciemen dto) throws Exception;
	
	public void save(Speciemen dto) throws Exception;

}