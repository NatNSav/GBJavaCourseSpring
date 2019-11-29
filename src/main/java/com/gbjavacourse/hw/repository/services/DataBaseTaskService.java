package com.gbjavacourse.hw.repository.services;

import com.gbjavacourse.hw.repository.entities.Task;
import com.gbjavacourse.hw.repository.entities.TaskRepository;
import com.gbjavacourse.hw.repository.interfaces.DataBaseTaskServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseTaskService implements  DataBaseTaskServiceInterface {
    private TaskRepository repository;

    public void setTaskRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public void printTasks()  {
        List<Task> tasksList = getAllTasksFromDB();
        for (Task t:tasksList) {
            t.printTask();
        }
    }

    public boolean isTaskInDB(Task task)  {
        return repository.isTaskInDB(task);
    }

    public Task getTaskById(Long id){return  repository.getTaskById(id);}

    public List<Task> getAllTasksFromDB(){
        return repository.getAllTasksFromDB();
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public void deleteTaskFromDBById(Long id) {
        repository.deleteTaskFromDBById(id);
    }

    public void deleteTaskFromDBByTitle(String title) {
        repository.deleteTaskFromDBByTitle(title);
    }
}




