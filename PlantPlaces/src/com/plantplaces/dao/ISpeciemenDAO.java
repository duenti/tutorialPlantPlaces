package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Speciemen;

public interface ISpeciemenDAO {

	void insert(Speciemen speciemen) throws Exception;

	List<Speciemen> fetchByPlantId(int plantId);

}