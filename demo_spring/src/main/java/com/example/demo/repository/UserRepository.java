package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
	
	@Query(value = "SELECT * FROM app_user WHERE"
			+ " user_id IN (SELECT DISTINCT(user_id) from user_role WHERE role_id in (:roleIds))", nativeQuery = true)
	List<User> getAllUserByRolesNativeSQL(@Param("roleIds") List<Long> roleIds);
	
	@Query(value = "SELECT DISTINCT u FROM User u INNER JOIN u.roles r WHERE r.roleId IN :roleIds")
	List<User> getAllUserByRoles(@Param("roleIds") List<Long> roleIds);
	
	List<User> findByUserNameIgnoreCaseContaining(String userName);
}
