package ma.map.tm.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.TestCase;
import ma.map.tm.dao.TestCaseRepository;
import ma.map.tm.service.TestCaseService;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService{

	private final TestCaseRepository testCaseRepository;

	public TestCaseServiceImpl(TestCaseRepository uR) {
		this.testCaseRepository=uR;
	}

	//Save TestCase:
	@Override
	public TestCase saveTestCase(TestCase u) { 
		return testCaseRepository.save(u);
	}

	//Update TestCase:
	@Override
	public TestCase updateTestCase(TestCase u) {
		return testCaseRepository.save(u);
	}

	//Delete TestCase:
	@Override
	public void deleteTestCase(TestCase u) {
		testCaseRepository.delete(u);
	}

	//Delete TestCase by ID:
	@Override
	public void deleteTestCaseById(int id) {
		testCaseRepository.deleteById(id);
	}

	//Get TestCase by ID:
	@Override
	public TestCase getTestCaseById(int id) {
		Optional<TestCase> op = testCaseRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Get TestCase by Scenario:
	@Override
	public List<TestCase> findAllByScenario(int idScenario) {
		return testCaseRepository.findAllByScenarioIdScenario(idScenario);
	}

	//Get TestCase by Scenario with pagination:
	@Override
	public Page<TestCase> findAllByScenario(int idScenario, PageRequest pageRequest) {
		return testCaseRepository.findAllByScenarioIdScenario(idScenario, pageRequest);
	}

	//Get TestCase by Creator:
	@Override
	public TestCase getTestCaseByCreator(int idUser) {
		Optional<TestCase> op = testCaseRepository.findById(idUser);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Find all TestCases:
	@Override
	public Page<TestCase> findAllTestCase(PageRequest pageRequest){
		return testCaseRepository.findAll(pageRequest);
	}

	//Get TestCases Count:
	@Override
	public long getTestCasesCount() {
		// TODO Auto-generated method stub
		return testCaseRepository.count();
	}

	@Override
	public Page<TestCase> findAllTestCaseByFilter(Pageable pageable, String filterValue) {
		return testCaseRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(pageable, filterValue, filterValue);
	}

}
