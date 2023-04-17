package com.zirconia.employeemanagementapi.dao;

import com.zirconia.employeemanagementapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user_details WHERE username = :username limit 1", nativeQuery = true)
    User getUserDetailsByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM user_details WHERE username = :username and password = :password limit 1", nativeQuery = true)
    User authenticateUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM user_details WHERE is_admin = false", nativeQuery = true)
    List<User> getAllUsers();
}
