package ma.map.tm.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.map.tm.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	    User findByUsername(String username);
	    
	    Page<User> findAll(Pageable pageable);
	    Page<User> findByNameIgnoreCaseContainingOrUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(Pageable pageable, String name, String username, String email);

}

