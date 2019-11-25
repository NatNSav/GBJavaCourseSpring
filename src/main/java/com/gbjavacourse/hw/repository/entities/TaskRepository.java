package com.gbjavacourse.hw.repository.entities;

import com.gbjavacourse.hw.repository.interfaces.RepositoryInterface;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository implements RepositoryInterface {
   private SessionFactory factory;

    public TaskRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public boolean isTaskInDB(Task task)  {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Task taskRes = em.find(Task.class, task.getId());
        em.getTransaction().commit();
        em.close();
        return taskRes!=null;
    }

    public List<Task> getAllTasksFromDB(){
        EntityManager em = factory.createEntityManager();
        List<Task> res = em.createQuery("SELECT a from Task a", Task.class).getResultList();
        em.close();
        return res;
    }

    public void addTask(Task task) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTaskFromDBById(Long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        for (Task task : em.createQuery("SELECT a from Task a WHERE a.id = '" + id + "'", Task.class).getResultList()) {
            em.remove(task);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTaskFromDBByTitle(String title) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        for (Task task : em.createQuery("SELECT a from Task a WHERE a.title = '" + title + "'", Task.class).getResultList()) {
            em.remove(task);
        }
        em.getTransaction().commit();
        em.close();
    }
}




