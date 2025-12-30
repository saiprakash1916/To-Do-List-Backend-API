package com.To_Do_List.service.impl;

import com.To_Do_List.dto.TaskRequestDTO;
import com.To_Do_List.dto.TaskResponseDTO;
import com.To_Do_List.entity.Task;
import com.To_Do_List.entity.User;
import com.To_Do_List.exception.ResourceNotFoundException;
import com.To_Do_List.exception.UnauthorizedAccessException;
import com.To_Do_List.repository.TaskRepository;
import com.To_Do_List.service.TaskService;
import com.To_Do_List.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public TaskResponseDTO createTask(Long userId, TaskRequestDTO taskRequestDTO) {
        User user = userService.getUserById(userId);
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setCompleted(false);
        task.setCreatedAT(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return mapToResponseDTO(savedTask);
    }

    @Override
    public TaskResponseDTO updateTask(Long userId, Long taskId, TaskRequestDTO taskRequestDTO) {
        Task task = getTaskForUser(userId, taskId);

        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());

        Task updatedTask = taskRepository.save(task);
        return mapToResponseDTO(updatedTask);
    }

    @Override
    public void markAsCompleted(Long userId, Long taskId) {
        Task task = getTaskForUser(userId, taskId);

        task.setCompleted(true);
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        Task task = getTaskForUser(userId, taskId);

        taskRepository.delete(task);
    }

    @Override
    public Page<TaskResponseDTO> getTaskByUser(Long userId, Pageable pageable) {
        User user = userService.getUserById(userId);

        return taskRepository.findByUserId(user, pageable).map(this::mapToResponseDTO);
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setCreatedAt(task.getCreatedAT());
        dto.setUpdatedAt(task.getUpdatedAt());

        return dto;
    }

    private Task getTaskForUser(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if (task.getUser().getId() != userId) {
            throw new UnauthorizedAccessException("Task does not belong to user");
        }
        return task;
    }
}
