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

import ma.map.tm.bean.Execution;
import ma.map.tm.service.ExecutionService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@RestController
public class ExecutionController {

	@Autowired
	private ExecutionService executionService;
	
	@PostMapping("/testCases/{idTestCase}/executions")
	public ResponseEntity saveExecution(@RequestBody Execution execution) {
		return new ResponseEntity(executionService.saveExecution(execution), HttpStatus.OK);
	}
	
	@PutMapping("/testCases/{idTestCase}/executions/{id}")
	public ResponseEntity updateExecution(@PathVariable int id, @RequestBody Execution execution) {
		Execution update = executionService.getExecutionById(id);
		if (null == update) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}else {
			if(execution.getNotes()!=null) update.setNotes(execution.getNotes());
			if(execution.getResult()!=null) update.setResult(execution.getResult());
			if(execution.getDuration()!=-1) update.setDuration(execution.getDuration());
			if(execution.getTestCase()!=null) update.setTestCase(execution.getTestCase());
			if(execution.getExecutor()!=null) update.setExecutor(execution.getExecutor());
			return new ResponseEntity(executionService.saveExecution(update), HttpStatus.OK);
		}
	}
	@DeleteMapping("/testCases/{idTestCase}/executions/{id}")
	public ResponseEntity deleteExecutionById(@PathVariable int id) {
		if (null == executionService.getExecutionById(id)) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}
		executionService.deleteExecutionById(id);
		return new ResponseEntity("Test Case "+ id +" deleted !", HttpStatus.OK);
	}
	
	@GetMapping("/testCases/{idTestCase}/executions/{id}")
	public ResponseEntity getExecutionById(@PathVariable int id) {
		Execution execution = executionService.getExecutionById(id);
		if (execution == null) {
			return new ResponseEntity("No Test Case found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(execution, HttpStatus.OK);
	}
	
	@GetMapping("/testCases/{idTestCase}/executions/all")
	public List<Execution> allExecutions(@PathVariable int idTestCase) {
		return executionService.findAllByTestCase(idTestCase);
	}
	 
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/testCases/{idTestCase}/executions")
	public List<Execution> allExecutions(@PathVariable int idTestCase, int page, int size, Direction direction, String sort) {
		PageRequest pageRequest = new PageRequest(page, size, direction, sort);
		Page<Execution> pages= executionService.findAllByTestCase(idTestCase, pageRequest);
		return pages.getContent();
	}

	@GetMapping("/testCases/{idTestCase}/executions/filter")
	public List<Execution> allExecutions(Pageable pageable, String filterValue) {
		 Page<Execution> page= executionService.findAllExecutionsByFilter(pageable, filterValue);
		 return page.getContent();
	}

	@GetMapping("/testCases/{idTestCase}/executions/count")
	public ResponseEntity getExecutionsCount() {
		 return new ResponseEntity(executionService.getExecutionsCount(), HttpStatus.OK);
	}

}