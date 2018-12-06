package ma.map.tm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Execution;

@Service
@Transactional
public interface ExecutionService{

	//Save Execution:
	public Execution saveExecution(Execution e);

	//Update Execution:
	public Execution updateExecution(Execution e);
	
	//Delete Execution:
	public void deleteExecution(Execution e);
	
	//Delete Execution by ID:
	public void deleteExecutionById(int id);
	
	//Get Execution by ID:
	public Execution getExecutionById(int id);
	

	//Get Execution by TestCase:
	public List<Execution> findAllByTestCase(int idTestCase);

	//Get Execution by TestCase with Pagination:
	public Page<Execution> findAllByTestCase(int idTestCase, PageRequest pageRequest);
	
	//Get Execution by Executor:
	public Execution getExecutionByExecutor(int idExecutor);

	//Find all Executions:
	public Page<Execution> findAllExecutions(PageRequest pageRequest);

	//Get Executions Count
	public long getExecutionsCount();

	//Search Executions:
	public Page<Execution> findAllExecutionsByFilter(Pageable pageable, String filterValue);
}
