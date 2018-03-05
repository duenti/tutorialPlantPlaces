package com.plantplaces.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import com.plantplaces.dao.IFileDAO;
import com.plantplaces.dao.IPhotoDAO;
import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.ISpeciemenDAO;
import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Speciemen;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Named
public class PlantService implements IPlantService {

	@Inject
	private IPlantDAO plantDAO;
	private List<Plant> allPlants;
	
	@Inject
	private ISpeciemenDAO speciemenDAO;
	
	@Inject
	private IFileDAO fileDAO;
	
	@Inject
	private IPhotoDAO photoDAO;
	
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
		plantDAO.save(plant);
	}

	@Override
	public void save(Speciemen speciemen) throws Exception{
		speciemenDAO.save(speciemen);
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
	
	public void savePhoto(Photo photo, InputStream inputStream) throws Exception{
		File directory = new File("/home/neli/git/PlantPlaces/WebContent/resources/images");
		String uniqueImageName = getUniqueImageName();
		File file = new File(directory, uniqueImageName);
		fileDAO.save(inputStream, file);
		
		File thumbnailDirectory = new File("/home/neli/git/PlantPlaces/WebContent/resources/thumbnails");
		File thumbnail = new File(thumbnailDirectory, uniqueImageName);
		
		BufferedImage watermark = ImageIO.read(new File("/home/neli/git/PlantPlaces/WebContent/resources/watermark.png"));
		Thumbnails.of(file).scale(1).watermark(Positions.BOTTOM_RIGHT, watermark, 0.9f).toFile(file);
		
		Thumbnails.of(file).size(100, 100).toFile(thumbnail);
		
		photo.setUri(uniqueImageName);
		//Eventually, save the photo to the database
		photoDAO.save(photo);
	}

	private String getUniqueImageName() {
		String imagePrefix = "PlantPlaces";
		String imageSufix = ".jpg";
		String middle = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		middle = sdf.format(new Date());
		
		return imagePrefix + middle + imageSufix;
	}
	
	@Override
	public List<Photo> fetchPhotos(Speciemen speciemen){
		return photoDAO.fetchPhotos(speciemen);
	}
	
}
