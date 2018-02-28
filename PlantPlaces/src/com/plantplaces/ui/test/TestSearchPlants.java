package com.plantplaces.ui.test;

import org.junit.Test;

import com.plantplaces.dto.Plant;
import com.plantplaces.ui.SearchPlants;

import junit.framework.TestCase;

public class TestSearchPlants extends TestCase {
	private SearchPlants searchPlants;
	private String execute;
	
	@Test
	public void testSearchPlantsExecute(){
		givenSearchPlantsCreatedWithRedbud();
		whenInvokeExecute();
		thenVerifyReturnSuccess();
	}
	
	@Test
	public void testSearchPlantsNoRedbud(){
		givenSearchPlantsCreatedWithoutRedbud();
		whenInvokeExecute();
		thenVerifyReturnNoResults();
	}

	@Test
	public void testSearchPlantsNull(){
		givenSearchPlantsCreatedWithNull();
		whenInvokeExecute();
		thenVerifyReturnNoResults();
	}
	
	private void givenSearchPlantsCreatedWithNull() {
		searchPlants = new SearchPlants();
		
	}

	private void thenVerifyReturnNoResults() {
		assertEquals("noresults",execute);
		
	}

	private void givenSearchPlantsCreatedWithoutRedbud() {
		searchPlants = new SearchPlants();
		Plant plant = new Plant();
		plant.setName("Paw Paw");
		searchPlants.setPlant(plant);
		
	}

	private void thenVerifyReturnSuccess() {
		assertEquals("search", execute);
		
	}

	private void whenInvokeExecute() {
		execute = searchPlants.execute();
		
	}

	private void givenSearchPlantsCreatedWithRedbud() {
		searchPlants = new SearchPlants();
		Plant plant = new Plant();
		plant.setName("Redbud");
		searchPlants.setPlant(plant);
	}
}
