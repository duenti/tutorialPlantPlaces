package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Speciemen;

public interface IPhotoDAO {

	void insert(Session session, Photo dto) throws Exception;
	public void save(Photo dto) throws Exception;
	List<Photo> fetchPhotos(Speciemen speciemen);

}