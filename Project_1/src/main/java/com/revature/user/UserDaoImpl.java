package com.revature.user;

import com.revature.dao.UserDao;
import com.revature.util.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl extends SessionUtil implements UserDao {
    private final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName()); //set up our logger
    public static User user = null;
    Session session = null;

    @Override
    public List<User> getAllEmployees() {
        SessionUtil.config();
        List<User> employees = null;
        try {
            session = sessionFactory.openSession();

            String hql = "from com.revature.user.User where fName != 'noone'";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);

            employees = query.list();

            t.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public User getUserByEmail(String email) {
        SessionUtil.config();
        try{
            session = sessionFactory.openSession();

            String hql = "from com.revature.user.User as u where u.email = '" + email + "'";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);

            @SuppressWarnings("unchecked")
            List<User> result = query.list();

            t.commit();
            for(User u:result) {
                user = u;
            }
        }finally{
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User getUserById(int userId) {
        session = sessionFactory.openSession();
        user = (User) session.get(User.class, userId);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Transaction t = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            session.save(user);
            t.commit();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return true;
    }

    @Override
    public boolean deleteUser(String email) {
        SessionUtil.config();
        try {
            session = sessionFactory.openSession();

            String hql = "delete from com.revature.user.User where email = '" + email + "'";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.executeUpdate();

            t.commit();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }
}
