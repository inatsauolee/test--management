package ma.map.tm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Scenario;
import ma.map.tm.dao.ScenarioRepository;
import ma.map.tm.service.ScenarioService;

@Service
@Transactional
public class ScenarioServiceImpl implements ScenarioService{

	private final ScenarioRepository scenarioRepository;

	public ScenarioServiceImpl(ScenarioRepository uR) {
		this.scenarioRepository=uR;
	}

	//Save Scenario:
	@Override
	public Scenario saveScenario(Scenario u) { 
		return scenarioRepository.save(u);
	}

	//Update Scenario:
	@Override
	public Scenario updateScenario(Scenario u) {
		return scenarioRepository.save(u);
	}

	//Delete Scenario:
	@Override
	public void deleteScenario(Scenario u) {
		scenarioRepository.delete(u);
	}
	
	//Delete Scenario by ID:
	@Override
	public void deleteScenarioById(int id) {
		scenarioRepository.deleteById(id);
	}

	//Get Scenario by ID:
	@Override
	public Scenario getScenarioById(int id) {
		Optional<Scenario> op = scenarioRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}
	
	//Get Scenario by Project:
	@Override
	public Page<Scenario> findAllByProject(int idProject, PageRequest pageRequest) {
		return scenarioRepository.findAllByProjectIdProject(idProject, pageRequest);
	}

	//Get Scenario by Creator:
	@Override
	public Scenario getScenarioByCreator(int idUser) {
		Optional<Scenario> op = scenarioRepository.findById(idUser);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Find all Scenarios:
	@Override
	public Page<Scenario> findAllScenario(PageRequest pageRequest){
		return scenarioRepository.findAll(pageRequest);
	}

	//Get Scenarios Count:
	@Override
	public long getScenariosCount() {
		// TODO Auto-generated method stub
		return scenarioRepository.count();
	}

	@Override
	public Page<Scenario> findAllScenarioByFilter(Pageable pageable, String filterValue) {
		return scenarioRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(pageable, filterValue, filterValue);
	}

}
