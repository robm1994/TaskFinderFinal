package org.taskfinder.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taskfinder.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByNameLike(String name);

	User findOne(String email);

	User findByEmail(String email);

	@Query(value = "SELECT * FROM user u left join user_roles ur on ur.user_email = u.email left join role r on r.name = ur.role_name WHERE r.name ='WORKER'", nativeQuery = true)
	List<User> findByRolesLike();

	List<User> findBySkillLike(String skill);

	List<User> findByEmailLike(String email);

}
