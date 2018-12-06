package ma.map.tm.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.map.tm.bean.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer>{
    Project findProjectByName(String projectName);
    
    Page<Project> findAll(Pageable pageable);
    Page<Project> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(Pageable pageable, String name, String description);


}
