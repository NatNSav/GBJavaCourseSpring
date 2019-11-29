package com.gbjavacourse.hw.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "ownerName")
    private String ownerName;
    @Column(name = "executerName")
    private String executerName;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    public Task() {
    }

    public Task(String title, String ownerName, String executerName, String description, String status) {

        this.title = title;
        this.ownerName = ownerName;
        this.executerName = executerName;
        this.description = description;
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getExecuterName() {
        return executerName;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        try{
            if(status!=null)return  status;
        }
        catch(NullPointerException e){}
        return "";
    }

    public void printTask(){
        System.out.println(id + ".  " + title + ". " + executerName + ". " + (status==null?"":status) );
    }

    public String getTaskInfo(){
        return id + ".  " + title + ".  " +(ownerName==null?"":ownerName) +  ".  " +executerName+ ".  " + (description==null?"":description) + ".  " + (status==null?"":status);
    }

    @Override
    public String toString() {
        return id + ".  " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(ownerName, task.ownerName) &&
                Objects.equals(executerName, task.executerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ownerName, executerName, description, status);
    }
}
