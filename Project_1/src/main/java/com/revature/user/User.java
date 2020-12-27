package com.revature.user;

import javax.persistence.*;

//We have to tell hibernate that this java obj. represents that sql table
//We do that through annotations
@Entity
@Table(name = "ersuser")   //if you don't supply a table name it will default to the class name
public class User {
    @Id //tells hibernate to make this the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells hibernate to make this a postgres auto incremented value (Identity means that the table generates the id)
    @Column(columnDefinition="serial primary key", nullable = false,  name = "userId") //note: the columnDefinition ties this to postgres syntax
    //OneToMany(targetEntity = Reimbursement.Class)
    @JoinColumn(name="userId")
    private int userId;

    @Column(name="isManager")
    private boolean isManager;

    @Column(nullable = false, name = "fName", length = 30)
    private String fName;

    @Column(nullable = false, name = "lName", length = 30)
    private String lName;

    @Column(nullable = false, name = "email", length = 50) //get rid of unique = true for testing purposes
    private String email; //login this is my username

    @Column(nullable = false, name = "password", length = 50)
    private String password; //login (needs to be hashed?)

    public User() {
    }

    public User(boolean isManager, String fName, String lName, String email, String password) {
        this.isManager = isManager;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int empId) {
        this.userId = empId;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean manager) {
        isManager = manager;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", isManager=" + isManager +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
