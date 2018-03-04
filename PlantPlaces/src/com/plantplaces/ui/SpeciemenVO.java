package com.plantplaces.ui;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Photo;
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
	private Photo photo;
	
	@Inject
	private IPlantService plantService;
	
	private UploadedFile file;

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
		loadSpeciemens();
	}

	private void loadSpeciemens() {
		// TODO Auto-generated method stub
		plantService.loadSpeciemens(plant);
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
	
	public void onRowSelect(SelectEvent event){
		Speciemen speciemen = (Speciemen)event.getObject();
		
		//Push the selected speciemen into SpeciemenVO
		setSpeciemen(speciemen);
	}
	
	public UploadedFile getFile(){
		return file;
	}
	
	public void setFile(UploadedFile file){
		this.file = file;
	}
	
	public void upload(){
		if(speciemen.getId() == 0){
			FacesMessage message = new FacesMessage("You have not yet selected a specimen. Please select one before saving the image.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}else if(file != null){
			try {
				InputStream inputStream = file.getInputstream();
				
				//Set the specimen id
				photo.setSpeciemen_id(speciemen.getId());
				
				//Pass the photo data and the photo metadata to our business logic layer
				plantService.savePhoto(getPhoto(),inputStream);
				
				FacesMessage message = new FacesMessage("Succesful",file.getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FacesMessage message = new FacesMessage("There was a problem, your file was not uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
	