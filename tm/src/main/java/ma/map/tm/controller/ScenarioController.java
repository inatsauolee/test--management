package ma.map.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.map.tm.bean.Project;
import ma.map.tm.bean.Scenario;
import ma.map.tm.service.ScenarioService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@RestController
public class ScenarioController {

	@Autowired
	private ScenarioService scenarioService;
	
	@PostMapping("projects/{idProject}/scenarios")
	public ResponseEntity saveScenario(@RequestBody Scenario scenario) {
		return new ResponseEntity(scenarioService.saveScenario(scenario), HttpStatus.OK);
	}
	
	@PutMapping("/projects/{idProject}/scenarios/{id}")
	public ResponseEntity updateScenario(@PathVariable int id, @RequestBody Scenario scenario) {
		Scenario update = scenarioService.getScenarioById(id);
		if (null == update) {
			return new ResponseEntity("No Scenario found for ID " + id, HttpStatus.NOT_FOUND);
		}else {
			if(scenario.getDescription()!=null) update.setDescription(scenario.getDescription());
			if(scenario.getTitle()!=null) update.setTitle(scenario.getTitle());
			if(scenario.getProject()!=null) update.setProject(scenario.getProject());
			if(scenario.getCreator()!=null) update.setCreator(scenario.getCreator());
			return new ResponseEntity(scenarioService.saveScenario(update), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/projects/{idProject}/scenarios/{id}")
	public ResponseEntity deleteScenarioById(@PathVariable int id) {
		if (null == scenarioService.getScenarioById(id)) {
			return new ResponseEntity("No Scenario found for ID " + id, HttpStatus.NOT_FOUND);
		}
		scenarioService.deleteScenarioById(id);
		return new ResponseEntity("Scenario "+ id +" deleted !", HttpStatus.OK);
	}
	
	@GetMapping("/projects/{idProject}/scenarios/{id}")
	public ResponseEntity getScenarioById(@PathVariable int idProject, @PathVariable int id) {
		Scenario scenario = scenarioService.getScenarioById(id);
		if (scenario == null) {
			return new ResponseEntity("No Scenario found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(scenario, HttpStatus.OK);
	}
	 
	 
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/projects/{idProject}/scenarios")
	public List<Scenario> allProjects(@PathVariable int idProject, int page, int size, Direction direction, String sort) {
		PageRequest pageRequest = new PageRequest(page, size, direction, sort);
		Page<Scenario> pages= scenarioService.findAllByProject(idProject, pageRequest);
		return pages.getContent();
	}

	@GetMapping("/projects/{idProject}/scenarios/filter")
	public List<Scenario> allProjects(Pageable pageable, String filterValue) {
		 Page<Scenario> page= scenarioService.findAllScenarioByFilter(pageable, filterValue);
		 return page.getContent();
	}

	@GetMapping("/projects/{idProject}/scenarios/count")
	public ResponseEntity getProjectsCount() {
		 return new ResponseEntity(scenarioService.getScenariosCount(), HttpStatus.OK);
	}
	

}