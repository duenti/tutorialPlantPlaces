package com.plantplaces.ui;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Speciemen;
import com.plantplaces.service.IPlantService;

@Named
@ManagedBean
@Scope("session")
public class SpeciemenVO {
	private Plant plant;
	
	@Inject
	private Speciemen speciemen;
	
	@Inject
	private IPlantService plantService;

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Speciemen getSpeciemen() {
		return speciemen;
	}

	public void setSpeciemen(Speciemen speciemen) {
		this.speciemen = speciemen;
	}
	
	public String save(){
		//Set the foreign key to plant id before saving
		System.out.println(plant.toString());
		speciemen.setPlantId(plant.getGuid());
		
		try {
			plantService.save(speciemen);
			return "specimensaved";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		
		
	}
}
