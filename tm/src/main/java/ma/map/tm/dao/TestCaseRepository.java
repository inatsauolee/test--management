package ma.map.tm.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.map.tm.bean.TestCase;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCase, Integer>{
	
    TestCase findTestCaseByTitle(String title);
    
    Page<TestCase> findAll(Pageable pageable);
    Page<TestCase> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(Pageable pageable, String title, String description);
	Page<TestCase> findAllByScenarioIdScenario(int idScenario, Pageable pageable);
	List<TestCase> findAllByScenarioIdScenario(int idScenario);



}
