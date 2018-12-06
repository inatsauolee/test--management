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

import constant.CodeError;
import ma.map.tm.bean.Project;
import ma.map.tm.dto.Message;
import ma.map.tm.service.MessageService;
import ma.map.tm.service.ProjectService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/projects")
	public ResponseEntity saveProject(@RequestBody Project project) {
		return new ResponseEntity(projectService.saveProject(project), HttpStatus.OK);
	}
	
	@PutMapping("/projects/{id}")
	public ResponseEntity updateProject(@PathVariable int id, @RequestBody Project project) {
		Project update = projectService.getProjectById(id);
		if (null == update) {
			return new ResponseEntity("No Project found for ID " + id, HttpStatus.NOT_FOUND);
		}else {
			if(project.getDescription()!=null) update.setDescription(project.getDescription());
			if(project.getName()!=null) update.setName(project.getName());
			if(project.getPrefix()!=-1) update.setPrefix(project.getPrefix());
			if(project.getCreator()!=null) update.setCreator(project.getCreator());
			return new ResponseEntity(projectService.saveProject(update), HttpStatus.OK);
		}
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity getProjectById(@PathVariable int id) {
		Project project = projectService.getProjectById(id);
		if (project == null) {
			return new ResponseEntity("No Project found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(project, HttpStatus.OK);
	}
	 
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/projects")
	public List<Project> allProjects(int page, int size, Direction direction, String sort) {
		PageRequest pageRequest = new PageRequest(page, size, direction, sort);
		Page<Project> pages= projectService.findAllProject(pageRequest);
		return pages.getContent();
	}
	
	@GetMapping("/projects/filter")
	public List<Project> allProjects(Pageable pageable, String filterValue) {
		 Page<Project> page= projectService.findAllProjectByFilter(pageable, filterValue);
		 return page.getContent();
	}
	
	@GetMapping("/projects/count")
	public ResponseEntity getProjectsCount() {
		 return new ResponseEntity(projectService.getProjectsCount(), HttpStatus.OK);
	}
	
	

}