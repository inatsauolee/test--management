package ma.map.tm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.User;
import ma.map.tm.dao.UserRepository;
import ma.map.tm.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository uR) {
		this.userRepository=uR;
	}

	//Save User:
	@Override
	public User saveUser(User u) { 
		return userRepository.save(u);
	}

	//Update User:
	@Override
	public User updateUser(User u) {
		return userRepository.save(u);
	}

	//Delete User:
	@Override
	public void deleteUser(User u) {
		userRepository.delete(u);
	}
	
	//Delete User by ID:
	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	//Get User by ID:
	@Override
	public User getUserById(int id) {
		Optional<User> op = userRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}
	
	//Get User by Username:
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	//Get User by Creator:
	@Override
	public User getUserByCreator(int id) {
		Optional<User> op = userRepository.findById(id);
		if(op.isPresent()) return op.get();
		return null;
	}

	//Find all Users:
	@Override
	public Page<User> findAllUser(PageRequest pageRequest){
		return userRepository.findAll(pageRequest);
	}

	//Get Users Count:
	@Override
	public long getUsersCount() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public Page<User> findAllUserByFilter(Pageable pageable, String filterValue) {
		
		return userRepository.findByNameIgnoreCaseContainingOrUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(pageable, filterValue, filterValue, filterValue);
	}

}
