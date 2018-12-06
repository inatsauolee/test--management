package ma.map.tm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.TestCase;

@Service
@Transactional
public interface TestCaseService{

	//Save TestCase:
	public TestCase saveTestCase(TestCase tc);

	//Update TestCase:
	public TestCase updateTestCase(TestCase tc);
	
	//Delete TestCase:
	public void deleteTestCase(TestCase tc);
	
	//Delete TestCase by ID:
	public void deleteTestCaseById(int id);
	
	//Get TestCase by ID:
	public TestCase getTestCaseById(int id);
	

	//Get TestCase by Scenario:
	public List<TestCase> findAllByScenario(int idScenario);

	//Get TestCase by Scenario with Pagination:
	public Page<TestCase> findAllByScenario(int idScenario, PageRequest pageRequest);
	
	//Get TestCase by Creator:
	public TestCase getTestCaseByCreator(int idCreator);

	//Find all TestCases:
	public Page<TestCase> findAllTestCase(PageRequest pageRequest);

	//Get TestCases Count
	public long getTestCasesCount();

	//Search TestCases:
	public Page<TestCase> findAllTestCaseByFilter(Pageable pageable, String filterValue);
}
