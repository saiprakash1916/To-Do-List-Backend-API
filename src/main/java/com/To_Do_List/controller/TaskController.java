package com.To_Do_List.controller;

import com.To_Do_List.dto.TaskRequestDTO;
import com.To_Do_List.dto.TaskResponseDTO;
import com.To_Do_List.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@PathVariable Long userId, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(userId, taskRequestDTO);
    }

    @PutMapping("/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Long userId, @PathVariable Long taskId, @Valid @RequestBody TaskRequestDTO taskRequestDTO){
        return taskService.updateTask(userId, taskId, taskRequestDTO);
    }

    @PatchMapping("/{taskId}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markAsCompleted(@PathVariable Long userId, @PathVariable Long taskId){
        taskService.markAsCompleted(userId, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long userId, @PathVariable Long taskId){
        taskService.deleteTask(userId, taskId);
    }

    @GetMapping
    public Page<TaskResponseDTO> getTaskByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction){
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return taskService.getTaskByUser(userId, pageable);
    }
}
