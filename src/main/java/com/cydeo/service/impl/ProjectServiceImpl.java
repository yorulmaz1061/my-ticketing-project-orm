package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserService userService, UserMapper userMapper, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);

        return projectMapper.convertToDto(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
         List<Project> list=projectRepository.findAll();
         return list.stream().map(projectMapper::convertToDto).collect(Collectors.toList());

    }

    @Override
    public void save(ProjectDTO dto) {
        //Get dto convert it tp entity and save it.
        //When we save something new status project status will be "open"
        dto.setProjectStatus(Status.OPEN);
        Project project=projectMapper.convertToEntity(dto);
        projectRepository.save(project);


    }
    @Override
    public void update(ProjectDTO dto) {
        //1.find from db
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        //2. This doesn't have id
        Project convertedProject = projectMapper.convertToEntity(dto);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);

    }
    @Override
    public void delete(String code) {
        //we don't delete from table we just update flag
        Project project=projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        projectRepository.save(project);


    }
    @Override
    public void complete(String projectCode) {
        Project project=projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        //herold@manager.com
        // I need to get all project assigned to this manager
        //I take from UI with the help of UserDTO
        //I convert it to entity
        UserDTO currentUserDTO = userService.findByUserName("harold@manager.com");
        User user = userMapper.convertToEntity(currentUserDTO);
        List<Project> list = projectRepository.findAllByAssignedManager(user);
        //There is no test counts in list above.
        return list.stream().map(p->{
            ProjectDTO obj=projectMapper.convertToDto(p);
            //??3
            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(p.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTask(p.getProjectCode()));
            return obj;
        }).collect(Collectors.toList());






    }
}
