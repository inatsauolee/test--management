package ma.map.tm.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ma.map.tm.bean.User;

@Service
@Transactional
public interface UserService{

	//Save User:
	public User saveUser(User u);

	//Update User:
	public User updateUser(User u);
	
	//Delete User:
	public void deleteUser(User u);
	
	//Delete User by ID:
	public void deleteUserById(int id);
	
	//Get User by ID:
	public User getUserById(int id);

	//Get User by Username:
	public User getUserByUsername(String username);

	//Get User by Creator:
	public User getUserByCreator(int id);

	//Find all Users:
	public Page<User> findAllUser(PageRequest pageRequest);

	//Get Users Count
	public long getUsersCount();

	public Page<User> findAllUserByFilter(Pageable pageable, String filterValue);

}
