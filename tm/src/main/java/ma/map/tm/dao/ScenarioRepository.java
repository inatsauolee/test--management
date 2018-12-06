package ma.map.tm.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.map.tm.bean.Scenario;

@Repository
public interface ScenarioRepository extends CrudRepository<Scenario, Integer>{
    Scenario findScenarioByTitle(String title);
    
    Page<Scenario> findAll(Pageable pageable);
    Page<Scenario> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(Pageable pageable, String title, String description);
	Page<Scenario> findAllByProjectIdProject(int idProject, Pageable pageable);


}
