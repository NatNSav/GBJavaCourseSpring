package com.gbjavacourse.hw;

        import com.gbjavacourse.hw.repository.DBBeanConfig;
        import com.gbjavacourse.hw.repository.entities.Task;
        import com.gbjavacourse.hw.repository.entities.TaskRepository;
        import com.gbjavacourse.hw.repository.services.DataBaseTaskService;

        import org.hibernate.SessionFactory;
        import org.hibernate.cfg.Configuration;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class SpringHW {
    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBBeanConfig.class)){
            SessionFactory factory = new Configuration()
                    .configure("hibernate.xml")
                    .buildSessionFactory();
            TaskRepository repository = new TaskRepository(factory);
            DataBaseTaskService service = context.getBean("dataBaseTaskService", DataBaseTaskService.class);
            service.setTaskRepository(repository);

            testTaskDataBase(service);
        }
        catch(Exception e){
            System.out.println(e+"  "+ e.getStackTrace());
        }
    }

    public static void testTaskDataBase(DataBaseTaskService service){
        System.out.println("");
        service.printTasks();
        service.addTask(new Task( "First task", "Ivanov", "Petrov", "very important task", "created"));
        System.out.println("");
        service.printTasks();
        service.addTask(new Task( "Second task", "Ivanov", "Petrov", "very important task", "created"));
        System.out.println("");
        service.printTasks();
        service.addTask(new Task( "Create task", "Sidorov", "Petrov", "very important task", "created"));
        service.addTask(new Task( "Delete task", "Petrov", "Sidorov", "Delete task", "completed"));
        service.addTask(new Task( "Update task", "Ivanov", "Sidorov", "Delete task", "completed"));
        service.addTask(new Task( "Status task", "Sidorov", "Petrov", "Status task", "closed"));
        service.addTask(new Task( "New task", "Sidorov", "Sidorov", "Delete task", "completed"));
        service.addTask(new Task( "Ivanov task", "Sidorov", "Petrov", "Ivanov task", "assigned"));
        service.addTask(new Task( "Petrov task", "Petrov", "Petrov", "Petrov task", "created"));
        service.addTask(new Task( "Sidorov task", "Ivanov", "Petrov", "Petrov task", "created"));
        service.addTask(new Task("Ivanov task", "Petrov", "Sidorov", "Ivanov task", "completed"));

        service.printTasks();
        service.deleteTaskFromDBById(6L);
        System.out.println("");
        service.printTasks();
        System.out.println("");
        service.addTask(new Task( "Ivanov task", "Petrov", "Sidorov", "Ivanov task", "completed"));
        System.out.println("");
        service.printTasks();
        System.out.println("");
        service.deleteTaskFromDBByTitle("Sidorov task");
        System.out.println("");
        service.printTasks();
        System.out.println("");
        service.addTask(new Task("Update task", "Ivanov", "Petrov", "Petrov task", "completed"));
        System.out.println("");

    }
}
