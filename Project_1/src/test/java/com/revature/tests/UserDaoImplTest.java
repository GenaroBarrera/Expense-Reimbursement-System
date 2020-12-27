package com.revature.tests;

import com.revature.reimbursement.ReimbursementDaoImpl;
import com.revature.user.User;
import com.revature.user.UserDaoImpl;
import com.revature.util.SessionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImplTest {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    //Test whether we can implement the interface UserDao
     //into our UserDaoImpl and create an instance
    @Test
    public void createUserDaoImpl() {
        Assert.assertNotNull(userDaoImpl);
        Assert.assertTrue(userDaoImpl instanceof UserDaoImpl);
    }

    //TODO test your dao rud methods!!!
    @Test
    public void getAllEmployeesTest(){
    }
}
