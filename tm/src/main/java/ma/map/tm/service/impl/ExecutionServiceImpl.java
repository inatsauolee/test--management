package ma.map.tm.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.Execution;
import ma.map.tm.dao.ExecutionRepository;
import ma.map.tm.service.ExecutionService;

@Service
@Transactional
public class ExecutionServiceImpl implements ExecutionService{

	private final ExecutionRepository executionRepository;

	public ExecutionServiceImpl(ExecutionRepository uR) {
		this.executionRepository=uR;
	}

	//Save Execution:
	@Override
	public Execution saveExecution(Execution u) { 
		return executionRepository.save(u);
	}

	//Update Execution:
	@Override
	public Execution updateExecution(Execution u) {
		return executionRepository.save(u);
	}

	//Delete Execution:
	@Override
	public void deleteExecution(Execution u) {
		executionRepository.delete(u);
	}

	//Delete Execution by ID:
	@Override
	public void deleteExecutionById(int id) {
		executionRepository.deleteById(id);
	}

	//Get Execution by ID:
	@Override
	public Execution getExecutionById(int id) {
		Optional<Execution> op = executionRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Get Execution by TestCase:
	@Override
	public List<Execution> findAllByTestCase(int idTestCase) {
		return executionRepository.findAllByTestCaseIdTestCase(idTestCase);
	}

	//Get Execution by TestCase with pagination:
	@Override
	public Page<Execution> findAllByTestCase(int idTestCase, PageRequest pageRequest) {
		return executionRepository.findAllByTestCaseIdTestCase(idTestCase, pageRequest);
	}

	//Get Execution by Executor:
	@Override
	public Execution getExecutionByExecutor(int idUser) {
		Optional<Execution> op = executionRepository.findById(idUser);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Find all Executions:
	@Override
	public Page<Execution> findAllExecutions(PageRequest pageRequest){
		return executionRepository.findAll(pageRequest);
	}

	//Get Executions Count:
	@Override
	public long getExecutionsCount() {
		// TODO Auto-generated method stub
		return executionRepository.count();
	}

	@Override
	public Page<Execution> findAllExecutionsByFilter(Pageable pageable, String filterValue) {
		return executionRepository.findByNotesIgnoreCaseContainingOrResultIgnoreCaseContaining(pageable, filterValue, filterValue);
	}

}
