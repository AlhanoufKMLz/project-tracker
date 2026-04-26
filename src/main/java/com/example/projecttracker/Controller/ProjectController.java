package com.example.projecttracker.Controller;

import com.example.projecttracker.ApiResponse.ApiResponse;
import com.example.projecttracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    //BASIC CRUD ENDPOINTS
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project newProject){
        for(Project p: projects){
            if(p.getId().equalsIgnoreCase(newProject.getId()))
                return new ApiResponse("The ID: " + newProject.getId() + " is already used please enter another ID.");
        }
        projects.add(newProject);
        return new ApiResponse("Project added successfully.");
    }

    @GetMapping("/get/all")
    public ArrayList<Project> getAllProjects(){
        return projects;
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateProject(@PathVariable String id, @RequestBody Project newProject){
        for(int i=0; i < projects.size(); i++){
            if(projects.get(i).getId().equalsIgnoreCase(id)){
                projects.set(i, newProject);
                return new ApiResponse("Project updated successfully.");
            }
        }
        return new ApiResponse("Project with ID: " + id + " not found.");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable String id){
        for(int i=0; i < projects.size(); i++) {
            if (projects.get(i).getId().equalsIgnoreCase(id)) {
                projects.remove(i);
                return new ApiResponse("Project deleted successfully");
            }
        }
        return new ApiResponse("Project with ID: " + id + " not found.");
    }

}
