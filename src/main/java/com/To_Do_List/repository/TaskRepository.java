package com.To_Do_List.repository;

import com.To_Do_List.entity.Task;
import com.To_Do_List.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserId(User userId, Pageable pageable);
}
