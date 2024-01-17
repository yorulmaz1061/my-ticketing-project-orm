package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {
    //This portion will be build based on the needs
    //Begin with crud operators
    //Why DTO because I am in the service
    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

    Integer totalNonCompletedTask(String projectCode);
    Integer totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);

    void completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);

    void updateStatus(TaskDTO task);

    List<TaskDTO> listAllTasksByStatus(Status status);

    List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee);
}
