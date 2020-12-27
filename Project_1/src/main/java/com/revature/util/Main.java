package com.revature.util;

import com.revature.user.User;
import com.revature.user.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main extends SessionUtil {
    private static final Logger logger = LogManager.getLogger(Main.class.getName()); //set up our logger
    Session session = null;

    public static void main(String[] args) {
        Main main = new Main();
        //test delete (works!) I'm just gonna the rest of my crud works!
        //UserDaoImpl udi = new UserDaoImpl();
        //udi.deleteUser("test@test.com");
        //test session and some crud
        //main.initEntities();
    }

    private void initEntities(){
        SessionUtil.config();

        User user = new User();
        user.setIsManager(false);
        user.setfName("test");
        user.setlName("testie");
        user.setEmail("test@test.com");
        user.setPassword("abcd1234");

        session = sessionFactory.openSession();
        logger.info("what is my session?" + session);

        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

}
