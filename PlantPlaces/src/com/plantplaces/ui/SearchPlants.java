package com.plantplaces.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;

@Named
@ManagedBean
@Scope("session")
public class SearchPlants {
	
	@Inject
	private Plant plant;
	
	public String execute(){
		if(plant != null && plant.getName().equalsIgnoreCase("Redbud")){
			return "search";
		}
			
		return "noresults";
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public List<Plant> completePlants(String query){
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		//Create plants and add them to the collection
		Plant redbul = new Plant();
		redbul.setName("Eastern Redbul");
		allPlants.add(redbul);
		
		Plant pawpaw = new Plant();
		pawpaw.setName("Paw Paw");
		allPlants.add(pawpaw);
		
		Plant nasturtiuim = new Plant();
		nasturtiuim.setName("Nasturtiuium");
		allPlants.add(nasturtiuim);
		
		return allPlants;
	}
	
}
