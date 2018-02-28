package com.plantplaces.ui;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

@Named
@ManagedBean
@Scope("request")//Request cria um objeto novo a cada requisição, enquanto que o scope usa o mesmo objeto
public class AddPlant {
	final static Logger logger = Logger.getLogger(AddPlant.class);
	
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
		logger.info("Entering the execute method");
		String returnValue = "success";
		//Get faces context
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		try {
			plantService.save(plant);
			logger.info("Save Succesfull " + plant.toString());
			//What is the message that we want to show?
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved","Plant Saved");
			//Display the message
			currentInstance.addMessage(null, fm);
		} catch (Exception e) {
			logger.error("Error while saving plant " + e.getMessage());
			
			e.printStackTrace();
			returnValue = "fail";
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unable to Save","Plant not Saved");
			//Display the message
			currentInstance.addMessage(null, fm);
		}
		return returnValue;
	}
}
