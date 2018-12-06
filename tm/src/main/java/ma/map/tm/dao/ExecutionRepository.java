package ma.map.tm.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.map.tm.bean.Execution;

@Repository
public interface ExecutionRepository extends CrudRepository<Execution, Integer>{
	
    Execution findExecutionByResult(String result);
    
    Page<Execution> findAll(Pageable pageable);
    Page<Execution> findByNotesIgnoreCaseContainingOrResultIgnoreCaseContaining(Pageable pageable, String notes, String result);
	Page<Execution> findAllByTestCaseIdTestCase(int idTestCase, Pageable pageable);
	List<Execution> findAllByTestCaseIdTestCase(int idTestCase);



}
