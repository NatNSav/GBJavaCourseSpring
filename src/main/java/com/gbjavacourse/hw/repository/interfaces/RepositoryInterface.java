package com.gbjavacourse.hw.repository.interfaces;

import com.gbjavacourse.hw.repository.entities.Task;

import java.util.List;

public interface RepositoryInterface {
    boolean isTaskInDB(Task task);
    List<Task> getAllTasksFromDB();
    void addTask(Task task);
    void deleteTaskFromDBById(Long id)  ;
    void deleteTaskFromDBByTitle(String title);
}


