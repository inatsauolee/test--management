package ma.map.tm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Project;
import ma.map.tm.dao.ProjectRepository;
import ma.map.tm.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository uR) {
		this.projectRepository=uR;
	}

	//Save Project:
	@Override
	public Project saveProject(Project u) { 
		return projectRepository.save(u);
	}

	//Update Project:
	@Override
	public Project updateProject(Project u) {
		return projectRepository.save(u);
	}

	//Delete Project:
	@Override
	public void deleteProject(Project u) {
		projectRepository.delete(u);
	}
	
	//Delete Project by ID:
	@Override
	public void deleteProjectById(int id) {
		projectRepository.deleteById(id);
	}

	//Get Project by ID:
	@Override
	public Project getProjectById(int id) {
		Optional<Project> op = projectRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Get Project by Creator:
	@Override
	public Project getProjectByCreator(int id) {
		Optional<Project> op = projectRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Find all Projects:
	@Override
	public Page<Project> findAllProject(PageRequest pageRequest){
		return projectRepository.findAll(pageRequest);
	}

	//Get Projects Count:
	@Override
	public long getProjectsCount() {
		// TODO Auto-generated method stub
		return projectRepository.count();
	}

	@Override
	public Page<Project> findAllProjectByFilter(Pageable pageable, String filterValue) {
		
		return projectRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(pageable, filterValue, filterValue);
	}

}
