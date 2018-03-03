package com.plantplaces.dao.test;

import java.util.List;

import org.junit.Test;

import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.PlantHbmDAO;
import com.plantplaces.dto.Plant;

import junit.framework.TestCase;

public class TestPlantHbmDAO extends TestCase{
	
	private IPlantDAO plantHbmDAO;
	private List<Plant> plants;

	@Test
	public void testVerifyFetchByCommonName(){
		givenPlantDAOInstatiated();
		whenCommonNameIsRedbud();
		thenVerifyResults();
	}

	private void thenVerifyResults() {
		// TODO Auto-generated method stub
		assertTrue(plants.size() > 0);
		for(Plant p : plants){
			assertEquals(p.getCommon(),"Redbud");
		}
	}

	private void whenCommonNameIsRedbud() {
		// TODO Auto-generated method stub
		Plant p = new Plant();
		p.setCommon("Redbud");
		plants = plantHbmDAO.fetchPlants(p);
	}

	private void givenPlantDAOInstatiated() {
		// TODO Auto-generated method stub
		plantHbmDAO = new PlantHbmDAO();
		
	}
	
}
