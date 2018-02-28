package com.plantplaces.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.plantplaces.dto.Plant;

@Named("plantDAO")
public class PlantDAOStub implements IPlantDAO {

	@Override
	public List<Plant> fetchPlants() {
		List<Plant> allPlants = new ArrayList<Plant>();
		
		//Create plants and add them to collection
		Plant redbud = new Plant();
		redbud.setName("Easter Redbud");
		redbud.setGenus("Cercis");
		redbud.setSpecies("canadensis");
		redbud.setCommon("Redbud");
		allPlants.add(redbud);
		
		Plant pawpaw = new Plant();
		pawpaw.setCommon("Paw Paw");
		pawpaw.setGenus("Asimina");
		pawpaw.setSpecies("triloba");
		allPlants.add(pawpaw);
		
		Plant nasturtium = new Plant();
		nasturtium.setCommon("Nasturtium");
		nasturtium.setGenus("Trapoleanum");
		nasturtium.setSpecies("spp");
		allPlants.add(nasturtium);
		
		Plant redMaple = new Plant();
		redMaple.setGenus("Acer");
		redMaple.setSpecies("rubrum");
		redMaple.setCommon("Red Maple");
		allPlants.add(redMaple);
		
		Plant redOak = new Plant();
		redOak.setGenus("Quercus");
		redOak.setSpecies("rubra");
		redOak.setCommon("Red Oak");
		allPlants.add(redOak);
		
		return allPlants;
	}

	@Override
	public void insert(Plant plant) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Plant plant) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Plant plant) throws Exception {
		// TODO Auto-generated method stub

	}

}