package com.plantplaces.ui;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

@Named
@ManagedBean
@Scope("request")//Request cria um objeto novo a cada requisição, enquanto que o scope usa o mesmo objeto
public class addPlant {
	
	@Inject
	private Plant plant;
	
	@Inject
	private IPlantService plantService;
	
	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public IPlantService getPlantService() {
		return plantService;
	}

	public void setPlantService(IPlantService plantService) {
		this.plantService = plantService;
	}

	public String execute(){
		String returnValue = "success";
		try {
			plantService.save(plant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnValue = "fail";
		}
		return returnValue;
	}
}
