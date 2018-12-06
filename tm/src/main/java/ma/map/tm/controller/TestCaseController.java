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

import ma.map.tm.bean.TestCase;
import ma.map.tm.service.TestCaseService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@RestController
public class TestCaseController {

	@Autowired
	private TestCaseService testCaseService;
	
	@PostMapping("/scenarios/{idScenario}/testCases")
	public ResponseEntity saveTestCase(@RequestBody TestCase testCase) {
		return new ResponseEntity(testCaseService.saveTestCase(testCase), HttpStatus.OK);
	}
	
	@PutMapping("/scenarios/{idScenario}/testCases/{id}")
	public ResponseEntity updateTestCase(@PathVariable int id, @RequestBody TestCase testCase) {
		TestCase update = testCaseService.getTestCaseById(id);
		if (null == update) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}else {
			if(testCase.getDescription()!=null) update.setDescription(testCase.getDescription());
			if(testCase.getTitle()!=null) update.setTitle(testCase.getTitle());
			if(testCase.getDuration()!=-1) update.setDuration(testCase.getDuration());
			if(testCase.getStatus()!=null) update.setStatus(testCase.getStatus());
			if(testCase.getScenario()!=null) update.setScenario(testCase.getScenario());
			if(testCase.getCreator()!=null) update.setCreator(testCase.getCreator());
			return new ResponseEntity(testCaseService.saveTestCase(update), HttpStatus.OK);
		}
	}
	@DeleteMapping("/scenarios/{idScenario}/testCases/{id}")
	public ResponseEntity deleteTestCaseById(@PathVariable int id) {
		if (null == testCaseService.getTestCaseById(id)) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}
		testCaseService.deleteTestCaseById(id);
		return new ResponseEntity("Test Case "+ id +" deleted !", HttpStatus.OK);
	}
	
	@GetMapping("/scenarios/{idScenario}/testCases/{id}")
	public ResponseEntity getTestCaseById(@PathVariable int id) {
		TestCase testCase = testCaseService.getTestCaseById(id);
		if (testCase == null) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(testCase, HttpStatus.OK);
	}
	
	@GetMapping("/scenarios/{idScenario}/testCases/all")
	public List<TestCase> allProjects(@PathVariable int idScenario) {
		return testCaseService.findAllByScenario(idScenario);
	}
	 
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/scenarios/{idScenario}/testCases")
	public List<TestCase> allProjects(@PathVariable int idScenario, int page, int size, Direction direction, String sort) {
		PageRequest pageRequest = new PageRequest(page, size, direction, sort);
		Page<TestCase> pages= testCaseService.findAllByScenario(idScenario, pageRequest);
		return pages.getContent();
	}

	@GetMapping("/scenarios/{idScenario}/testCases/filter")
	public List<TestCase> allProjects(Pageable pageable, String filterValue) {
		 Page<TestCase> page= testCaseService.findAllTestCaseByFilter(pageable, filterValue);
		 return page.getContent();
	}

	@GetMapping("/scenarios/{idScenario}/testCases/count")
	public ResponseEntity getProjectsCount() {
		 return new ResponseEntity(testCaseService.getTestCasesCount(), HttpStatus.OK);
	}

}