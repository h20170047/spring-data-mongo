package com.svj.controller;

import com.svj.model.Task;
import com.svj.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService= taskService;
    }

    @PostMapping
    public Task addNewTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> findAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task findTaskById(@PathVariable String taskId){
        return taskService.getTask(taskId);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task){
         return taskService.udpateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/assignee/{assignee}/priority/{priority}")
    public List<Task> getTaskByAssigneeAndPriority(@PathVariable String assignee, @PathVariable String priority){
        return taskService.getTaskByAssigneeAndPriority(assignee, priority);
    }

    @PostMapping("/priorities")
    public List<Task> getTasksInPrioritiesList(@RequestBody List<String> priorities){
        return taskService.getTasksInPrioritiesList(priorities);
    }

    // between would be applicable in numeric values
    @GetMapping("/storyPoints/low/{lowSP}/high/{highSP}")
    public List<Task> getTasksWithPrioritiesBetween(@PathVariable Integer lowSP, @PathVariable Integer highSP){
        return taskService.getTasksWithPrioritiesBetween(lowSP, highSP);
    }

    // words are case sensitive
    @GetMapping("/filter/{keyWord}")
    public List<Task> getTasksWithLikeFilter(@PathVariable String keyWord){
        return taskService.getTasksWithLikeFilter(keyWord);
    }

    @GetMapping("/storyPoints/low/{lowSP}")
    public List<Task> getTasksWithSPGTE(@PathVariable Integer lowSP){
        return taskService.getTasksWithSPGTE(lowSP);
    }

    @GetMapping("/storyPoints/high/{highSP}")
    public List<Task> getTasksWithSPLTE(@PathVariable Integer highSP){
        return taskService.getTasksWithSPLTE(highSP);
    }

    @GetMapping("/sort/{fieldName}")
    public List<Task> sortTasksOnField(@PathVariable String fieldName){
        return taskService.sortTasksOnField(fieldName);
    }

    @GetMapping("/page/offset/{offset}/size/{size}")
    public List<Task> getTasksPagenated(@PathVariable Integer offset, @PathVariable Integer size){
        return taskService.getTasksPagenated(offset,size);
    }

    @GetMapping("/page/offset/{offset}/size/{size}/field/{field}")
    public List<Task> getTasksPagenatedAndSorted(@PathVariable String field, @PathVariable Integer offset, @PathVariable Integer size){
        return taskService.getTasksPagenatedAndSorted(field, offset, size);
    }
}
