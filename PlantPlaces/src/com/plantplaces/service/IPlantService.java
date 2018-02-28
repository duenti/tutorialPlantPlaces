package com.plantplaces.service;

import java.util.List;

import com.plantplaces.dto.Plant;

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
}
