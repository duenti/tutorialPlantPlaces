package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Speciemen;

@Named
public class PhotoHbmDAO extends PlantPlacesHbmDAO<Photo> implements IPhotoDAO {

	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPhotoDAO#insert(org.hibernate.Session, com.plantplaces.dto.Photo)
	 */
	@Override
	public void insert(Session session, Photo dto) throws Exception {
		// TODO Auto-generated method stub
		session.save(dto);
	}
	
	@Override
	public List<Photo> fetchPhotos(Speciemen speciemen){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Create the query [From usa o nome da classe associada e não da tabela]
		Query query = session.createQuery("from Photo where speciemen_id = :speciemenId");
		query.setParameter("speciemenId", speciemen.getId());
		List list = query.list();
		List<Photo> photos = Collections.checkedList(list, Photo.class);
		
		return photos;
	}
	
}
