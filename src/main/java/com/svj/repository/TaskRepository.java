package com.svj.repository;

import com.svj.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByAssigneeAndPriority(String assignee, String priority);

    @Query(value = "{assignee: ?0, priority: ?1}", fields = "{'description':1, 'storyPoint': 1}")
    // Select * from Tasks where assignee: 0 and priority: 1
    List<Task> findTasksByAssigneeAndPriority(String assignee, String priority);

    List<Task> findByPriorityIn(List<String> priorities);

    List<Task> findByDescriptionLike(String filter);

    List<Task> findByStoryPointGreaterThan(int lowSP);

    List<Task> findByStoryPointLessThan(int highSP);

    List<Task> findByStoryPointBetween(int lowSP, int highSP);
}
