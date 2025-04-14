package com.demo.DemoSecurityJWT.task.repository;

import com.demo.DemoSecurityJWT.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}