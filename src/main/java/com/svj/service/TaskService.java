package com.svj.service;

import com.svj.model.Task;
import com.svj.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository= taskRepository;
    }

    public Task saveTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    public List<Task> getAllTasks(){
        List<Task> savedTasks = taskRepository.findAll();
        return savedTasks;
    }

    public Task getTask(String taskId){
        Task task= taskRepository.findById(taskId).get();
        return task;
    }

    public Task udpateTask(Task task){
        Task existingTask= taskRepository.findById(task.getTaskId()).get();
        existingTask.setAssignee(task.getAssignee());
        existingTask.setDescription(task.getDescription());
        existingTask.setStoryPoint(task.getStoryPoint());
        existingTask.setPriority(task.getPriority());
        Task savedTask = taskRepository.save(existingTask);
        return savedTask;
    }

    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId.concat(" is deleted!!");
    }

    public List<Task> getTaskByAssigneeAndPriority(String assignee, String priority){
//        return taskRepository.findByAssigneeAndPriority(assignee, priority);
        return taskRepository.findTasksByAssigneeAndPriority(assignee, priority);
    }

    // IN, BETWEEN, LIKE, pagenation & sorting, GTE, LTE, sort on field dynamically
    public List<Task> getTasksInPrioritiesList(List<String> priorities){
        return taskRepository.findByPriorityIn(priorities);
    }

    public List<Task> getTasksWithPrioritiesBetween(int lowPriority, int highPriority){
        return taskRepository.findByStoryPointBetween(lowPriority, highPriority);
    }

    public List<Task> getTasksWithLikeFilter(String filter){
        return taskRepository.findByDescriptionLike(filter);
    }

    public List<Task> getTasksWithSPGTE(int lowSP){
        return taskRepository.findByStoryPointGreaterThan(lowSP);
    }

    public List<Task> getTasksWithSPLTE(int highSP){
        return taskRepository.findByStoryPointLessThan(highSP);
    }

    public List<Task> sortTasksOnField(String fieldName){
        return taskRepository.findAll(Sort.by(fieldName).ascending());
    }

    public List<Task> getTasksPagenated(int offset, int size){
        return taskRepository.findAll(PageRequest.of(offset,size)).getContent();
    }

    public List<Task> getTasksPagenatedAndSorted(String field, int offset, int size){
        return taskRepository.findAll(
                PageRequest.of(offset, size)
                        .withSort(Sort.by(field))
                ).getContent();
    }
}
