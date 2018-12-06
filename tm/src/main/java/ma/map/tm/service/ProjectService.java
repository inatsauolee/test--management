package ma.map.tm.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Project;

@Service
@Transactional
public interface ProjectService{

	//Save Project:
	public Project saveProject(Project p);

	//Update Project:
	public Project updateProject(Project p);
	
	//Delete Project:
	public void deleteProject(Project p);
	
	//Delete Project by ID:
	public void deleteProjectById(int id);
	
	//Get Project by ID:
	public Project getProjectById(int id);

	//Get Project by Creator:
	public Project getProjectByCreator(int id);

	//Find all Projects:
	public Page<Project> findAllProject(PageRequest pageRequest);

	//Get Projects Count
	public long getProjectsCount();

	public Page<Project> findAllProjectByFilter(Pageable pageable, String filterValue);
}
