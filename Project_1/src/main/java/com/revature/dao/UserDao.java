package com.revature.dao;

import com.revature.user.User;

import java.util.List;

/**
 * The Data Access Object (DAO) pattern is a structural pattern that allows us
 * to isolate the application/business layer from the persistence layer (usually a relational database, but it could be any other persistence mechanism)
 * using an abstract API.
 * This Dao interface defines an abstract API that performs CRUD operations for our Employee and Manager objects (maybe reimbursements as well)
 */

public interface UserDao {
    List<User> getAllEmployees();
    User getUserByEmail(String email);
    User getUserById(int userId);
    boolean addUser(User user);
    boolean deleteUser(String email);
}
