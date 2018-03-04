package com.plantplaces.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.plantplaces.dao.IFileDAO;
import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.ISpeciemenDAO;
import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Speciemen;

@Named
public class PlantService implements IPlantService {

	@Inject
	private IPlantDAO plantDAO;
	private List<Plant> allPlants;
	
	@Inject
	private ISpeciemenDAO speciemenDAO;
	
	@Inject
	private IFileDAO fileDAO;
	
	@Override
	public List<Plant> filterPlants(String filter) {
		if(allPlants == null){
			allPlants = getPlantDAO().fetchPlants();
		}
		
		//The collection we are returning
		List<Plant> returnPlants = new ArrayList<Plant>();
		
		//filter the list
		for(Plant plant : allPlants){
			if(plant.toString().contains(filter)){
				//This plant matches our criteria, so add it to the collection that will be returned
				returnPlants.add(plant);
			}
		}
		
		return returnPlants;
	}
	
	//Throws significa que vai passar a mensagem de erro para um nível mais alto
	public void save(Plant plant) throws Exception{
		if(plant.getGenus() == null || plant.getGenus().isEmpty()){
			throw new Exception("Genus required");
		}
		plantDAO.insert(plant);
	}

	@Override
	public void save(Speciemen speciemen) throws Exception{
		speciemenDAO.insert(speciemen);
	}
	
	@Override
	public List<Plant> fetchPlants(Plant plant){
		List<Plant> plants = plantDAO.fetchPlants(plant);
		
		return plants;
	}
	
	public IPlantDAO getPlantDAO() {
		return plantDAO;
	}

	public void setPlantDAO(IPlantDAO plantDAO) {
		this.plantDAO = plantDAO;
	}

	@Override
	public void loadSpeciemens(Plant plant) {
		// TODO Auto-generated method stub
		List<Speciemen> speciemens = speciemenDAO.fetchByPlantId(plant.getGuid());
		plant.setSpeciemens(speciemens);
	}
	
	public void savePhoto(Photo photo, InputStream inputStream) throws IOException{
		File directory = new File("/home/neli/git/PlantPlaces/WebContent/images");
		String uniqueImageName = getUniqueImageName();
		File file = new File(directory, uniqueImageName);
		fileDAO.save(inputStream, file);
		
		photo.setUri(uniqueImageName);
		//Eventually, save the photo to the database
	}

	private String getUniqueImageName() {
		String imagePrefix = "PlantPlaces";
		String imageSufix = ".jpg";
		String middle = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		middle = sdf.format(new Date());
		
		return imagePrefix + middle + imageSufix;
	}
	
}
