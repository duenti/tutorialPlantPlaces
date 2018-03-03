package com.plantplaces.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.ISpeciemenDAO;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Speciemen;

@Named
public class PlantService implements IPlantService {

	@Inject
	private IPlantDAO plantDAO;
	private List<Plant> allPlants;
	
	@Inject
	private ISpeciemenDAO speciemenDAO;
	
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

}
