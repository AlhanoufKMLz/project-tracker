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
            if(p.getTitle().equalsIgnoreCase(newProject.getTitle()))
                return new ApiResponse("This project is already exist.");
        }
        projects.add(newProject);
        return new ApiResponse("Project added successfully.");
    }

    @GetMapping("/get-all")
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


    //EXTRA ENDPOINTS
    @PutMapping("/update-status/{id}")
    public ApiResponse updateStatus(@PathVariable String id){
        for (Project project : projects) {
            if (project.getId().equalsIgnoreCase(id)) {
                project.setDone(!project.isDone());
                return new ApiResponse("Project status updated successfully.");
            }
        }
        return new ApiResponse("Project with ID: " + id + " not found.");
    }

    @GetMapping("/get-title/{title}")
    public Project searchByTitle(@PathVariable String title){
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return project;
            }
        }
        return null;
    }

    @GetMapping("/get-company/{companyName}")
    public ArrayList<Project> getByCompanyName(@PathVariable String companyName){
        ArrayList<Project> companyProjects = new ArrayList<>();
        for(Project project: projects){
            if(project.getCompanyName().equalsIgnoreCase(companyName))
                companyProjects.add(project);
        }
        return companyProjects;
    }

}
