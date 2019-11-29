package com.gbjavacourse.hw.repository.interfaces;

import com.gbjavacourse.hw.repository.entities.Task;

import java.util.List;

public interface DataBaseTaskServiceInterface  {
    void printTasks();
    boolean isTaskInDB(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasksFromDB();
    void addTask(Task task);
    void deleteTaskFromDBById(Long id);
    void deleteTaskFromDBByTitle(String title);

}





