package com.To_Do_List.service;

import com.To_Do_List.dto.TaskRequestDTO;
import com.To_Do_List.dto.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskResponseDTO createTask(Long userId, TaskRequestDTO taskRequestDTO);

    TaskResponseDTO updateTask(Long userId, Long taskId, TaskRequestDTO taskRequestDTO);

    void markAsCompleted(Long userId, Long taskId);

    void deleteTask(Long userId, Long taskId);

    Page<TaskResponseDTO> getTaskByUser(Long userId, Pageable pageable);
}
