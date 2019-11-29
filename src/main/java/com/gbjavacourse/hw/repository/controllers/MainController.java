package com.gbjavacourse.hw.repository.controllers;

import com.gbjavacourse.hw.repository.entities.Task;
import com.gbjavacourse.hw.repository.services.DataBaseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private DataBaseTaskService repositoryService;

    @Autowired
    public void setRepositoryService(DataBaseTaskService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(){
        return "index";
    }


    @RequestMapping(path = "/task_adding")
    public String simpleProcessForm(@RequestParam("taskTitle") String taskTitle, @RequestParam("taskOwnerName") String taskOwnerName,
                                    @RequestParam("taskExecuterName") String taskExecuterName, @RequestParam("taskDescription") String taskDescription,
                                    @RequestParam("taskStatus") String taskStatus, Model model) {
        Task task = new Task(taskTitle,taskOwnerName,taskExecuterName,taskDescription,taskStatus);

        repositoryService.addTask(task);

        model.addAttribute("task", task);
        return "task_result";
    }
    // http://localhost:8189/app/info/1
    @RequestMapping(path = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getTaskById(@PathVariable Long id) {
        return repositoryService.getTaskById(id).getTaskInfo();
    }

    // http://localhost:8189/app/show
    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public String showAllStudents(Model model) {
        List<Task> tasks = repositoryService.getAllTasksFromDB();
        model.addAttribute("tasks", tasks);
        return "all_students";
    }

    // GET http://localhost:8189/app/path/20/java/100
    @GetMapping("/path/{id}/{text}/100")
    @ResponseBody
    //return page body
    public String pathTest(@PathVariable(value = "id") Long id, @PathVariable(value = "text") String text) {
        return text + ": " + id;
    }

    // GET http://localhost:8189/app/param?data=20
    @GetMapping("/param")
    @ResponseBody
    public String pathTest(@RequestParam(value = "data", required = false) Integer data) {
        if (data != null) {
            return "Your param 'data': " + data;
        }

        return "Your param 'data' is empty";
    }

    // GET http://localhost:8189/app/info?msg=DBData
    @GetMapping("/info")
    public String showInfoPage(Model model, @RequestParam(name = "msg", required = false) String msg, @RequestParam(name = "id", required = false) Long id) {
        model.addAttribute("message", msg);
        model.addAttribute("id", repositoryService.getTaskById(id));
        return "info";
    }
}
