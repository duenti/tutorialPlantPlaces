package com.plantplaces.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Speciemen;

/**
 * IPLantService includes all business related function for a Plant and related entities
 * @author Neli Fonseca
 *
 */
public interface IPlantService {
	
	/**
	 * Return a collection of plant objects that contain the given filter text
	 * @param filter: a substring that should be contained in returned plants.
	 * @return a collection of matching plants.
	 */
	public List<Plant> filterPlants(String filter);

	
	/**
	 * Save the plant and perform a validation check.
	 * @param plant the plant we are persisting.
	 * @throws Exception if unable to save.
	 */
	void save(Plant plant) throws Exception;

	/**
	 * Return a list of plants that matches the given search criterea
	 * @param plant a parameter that contains the search criterea
	 * @return a list of matching plants
	 */
	List<Plant> fetchPlants(Plant plant);


	void save(Speciemen speciemen) throws Exception;

	/**
	 * Load speciemens for a given plant.
	 * @param plant
	 */
	public void loadSpeciemens(Plant plant);


	public void savePhoto(Photo photo, InputStream inputStream) throws IOException;
}
