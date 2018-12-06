package ma.map.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.map.tm.bean.User;
import ma.map.tm.service.UserService;


@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public ResponseEntity saveUser(@RequestBody User user) {
		return new ResponseEntity(service.saveUser(user), HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
		User update = service.getUserById(id);
		if (null == update) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}else {
			if(user.getEmail()!=null) update.setEmail(user.getEmail());
			if(user.getName()!=null) update.setName(user.getName());
			if(user.getGender()!=null) update.setGender(user.getGender());
			if(user.getMobile()!=null) update.setMobile(user.getMobile());
			if(user.getUsername()!=null) update.setUsername(user.getUsername());
			if(user.getPassword()!=null) update.setPassword(user.getPassword());
			if(user.getAuthorities()!=null) update.setAuthorities(user.getAuthorities());
			if(user.getEnabled()!=null) update.setEnabled(user.getEnabled());
			if(user.getLastPasswordResetDate()!=null) update.setLastPasswordResetDate(user.getLastPasswordResetDate());
			return new ResponseEntity(service.saveUser(update), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUserById(@PathVariable int id) {
		if (null == service.getUserById(id)) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
		service.deleteUserById(id);
		return new ResponseEntity("User "+ id +" deleted !", HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity getUserById(@PathVariable int id) {
		User user = service.getUserById(id);
		if (user == null) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}
	 
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/users")
	public List<User> allUsers(int page, int size, Direction direction, String sort) {
		PageRequest pageRequest = new PageRequest(page, size, direction, sort);
		Page<User> pages= service.findAllUser(pageRequest);
		return pages.getContent();
	}
	
	@GetMapping("/users/filter")
	public List<User> allUsers(Pageable pageable, String filterValue) {
		 Page<User> page= service.findAllUserByFilter(pageable, filterValue);
		 return page.getContent();
	}
	
	@GetMapping("/users/count")
	public ResponseEntity getUsersCount() {
		 return new ResponseEntity(service.getUsersCount(), HttpStatus.OK);
	}
	
	
	
}

