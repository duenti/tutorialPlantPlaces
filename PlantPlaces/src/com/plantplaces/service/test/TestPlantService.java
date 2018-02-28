package com.plantplaces.service.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dto.Plant;
import com.plantplaces.service.PlantService;

import junit.framework.TestCase;

public class TestPlantService extends TestCase{
	
	private PlantService plantService;
	private List<Plant> filterPlants;
	private IPlantDAO plantDAO;

	@Test
	public void testFilterPlants(){
		givenThatPlantServiceIsPopulatedWithPlantDAO();
		whenFilteredWithRed();
		thenVerifyTwoResults();
		
		
	}

	private void thenVerifyTwoResults() {
		assertEquals(2,filterPlants.size());
		
	}

	private void whenFilteredWithRed() {
		// TODO Auto-generated method stub
		filterPlants = plantService.filterPlants("Red");
		//Verifica quantas vezes fetchPlants foi chamado. Se foi 1 vez, está correto
		verify(plantDAO,times(1)).fetchPlants();
	}

	private void givenThatPlantServiceIsPopulatedWithPlantDAO() {
		plantService = new PlantService();
		
		plantDAO = mock(IPlantDAO.class);
		//A known set of hardcoded plants.
		List<Plant> plantList = constructPlantList();
		//Tell the mock object to return our known set of hardcoded plants, when request.
		when(plantDAO.fetchPlants()).thenReturn(plantList);
		
		//Associate the mock object with the object we are testing
		plantService.setPlantDAO(plantDAO);
		
	}
	
	private List<Plant> constructPlantList(){
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
		
		return allPlants;
	}

}
