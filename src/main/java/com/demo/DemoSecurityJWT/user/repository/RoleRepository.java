package com.demo.DemoSecurityJWT.user.repository;

import com.demo.DemoSecurityJWT.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = """
        SELECT r.name FROM role r
        JOIN user_roles ur ON r.id = ur.role_id
        JOIN user u ON u.id = ur.user_id
        WHERE u.username = :username
    """, nativeQuery = true)
    List<String> findRolesByUsername(@Param("username") String username);
}
