package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT "
			+ "u "
			+ "FROM User u "
			+ "WHERE u.userId = :userId "
			+ "AND u.deleteFlag = 0 "
			+ "AND u.userDormitory.deleteFlag = 0")
	Optional<User> findByUserId(@Param("userId") String userId);

}
