/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Gasana
 */
@Entity
public class Village implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    private Cell cell;
    @OneToOne(mappedBy = "village")
    private User user;
    @OneToMany(mappedBy = "village", fetch = FetchType.EAGER)
    private List<Resident> residents;
    @OneToMany(mappedBy = "village")
    private List<Numerator> numerators;
    @OneToMany(mappedBy = "village")
    private List<Support> supports;
    @OneToMany(mappedBy = "village")
    private List<Allocated> allocates;
    
    @OneToMany(mappedBy = "village")
    private List<Message> messages;
    @OneToMany(mappedBy = "village")
    private List<Notification> notifications;

   

    public Village() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<Numerator> getNumerators() {
        return numerators;
    }

    public void setNumerators(List<Numerator> numerators) {
        this.numerators = numerators;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void setSupports(List<Support> supports) {
        this.supports = supports;
    }

    public List<Allocated> getAllocates() {
        return allocates;
    }

    public void setAllocates(List<Allocated> allocates) {
        this.allocates = allocates;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    
    
}
