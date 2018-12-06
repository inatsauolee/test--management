package ma.map.tm.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Scenario;

@Service
@Transactional
public interface ScenarioService{

	//Save Scenario:
	public Scenario saveScenario(Scenario p);

	//Update Scenario:
	public Scenario updateScenario(Scenario p);
	
	//Delete Scenario:
	public void deleteScenario(Scenario p);
	
	//Delete Scenario by ID:
	public void deleteScenarioById(int id);
	
	//Get Scenario by ID:
	public Scenario getScenarioById(int id);

	//Get Scenario by Project:
	public Page<Scenario> findAllByProject(int id, PageRequest pageRequest);
	
	//Get Scenario by Creator:
	public Scenario getScenarioByCreator(int id);

	//Find all Scenarios:
	public Page<Scenario> findAllScenario(PageRequest pageRequest);

	//Get Scenarios Count
	public long getScenariosCount();

	//Search Scenarios:
	public Page<Scenario> findAllScenarioByFilter(Pageable pageable, String filterValue);
}
