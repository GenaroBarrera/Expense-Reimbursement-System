package com.revature.reimbursement;

import com.revature.dao.ReimbursementDao;
import com.revature.user.User;
import com.revature.util.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class ReimbursementDaoImpl extends SessionUtil implements ReimbursementDao {
    private final Logger logger = LogManager.getLogger(ReimbursementDaoImpl.class.getName()); //set up our logger
    public static User user = null;
    Session session = null;

    @Override
    public boolean addReimb(Reimbursement reimb) {
        Transaction t = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            System.out.println(reimb);
            session.save(reimb);
            t.commit();
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<Reimbursement> getAllReimbPendingByID(int id) {
        SessionUtil.config();
        List<Reimbursement> reimbs;
        try{
            session = sessionFactory.openSession();

            String hql = "from com.revature.reimbursement.Reimbursement where result = 'Pending' and user_id = :i";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setInteger("i", id);

            reimbs = query.list();

            t.commit();

        }finally {
            if (session != null) {
                session.close();
            }
        }
        return reimbs;
    }

    @Override
    public List<Reimbursement> getAllReimbPending() {
        SessionUtil.config();
        List<Reimbursement> reimbs;
        try{
            session = sessionFactory.openSession();

            String hql = "from com.revature.user.User as u, com.revature.reimbursement.Reimbursement as r " +
                    "where u.userId = r.user_id and r.result = 'Pending'";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);

            reimbs = query.list();

            t.commit();

        }finally {
            if (session != null) {
                session.close();
            }
        }
        return reimbs;
    }

    @Override
    public List<Reimbursement> getAllReimbResolved() {
        SessionUtil.config();
        List<Reimbursement> reimbs;
        try{
            session = sessionFactory.openSession();

            String hql = "from com.revature.user.User as u, com.revature.reimbursement.Reimbursement r " +
                    "where u.userId = r.user_id and r.result != 'Pending'";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);

            reimbs = query.list();

            t.commit();

        }finally {
            if (session != null) {
                session.close();
            }
        }
        return reimbs;
    }

    @Override
    public List<Reimbursement> getAllReimbResolvedByID(int id) {
        SessionUtil.config();
        List<Reimbursement> reimbs;
        try{
            session = sessionFactory.openSession();

            String hql = "from com.revature.user.User as u, com.revature.reimbursement.Reimbursement r " +
                    "where u.userId = r.user_id and r.result != 'Pending' and u.userId = :i";
            Transaction t = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setInteger("i", id);

            reimbs = query.list();

            t.commit();

        }finally {
            if (session != null) {
                session.close();
            }
        }
        return reimbs;
    }

    @Override
    public void updatePending(int num, String resolved) {
        SessionUtil.config();
        try{
            session = sessionFactory.openSession();

            String sql = "update reimbursement set result= :res where id = :i";
            Transaction t = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.setString("res", resolved);
            query.setInteger("i", num);

            int exe = query.executeUpdate(); //TODO exe is never used (might need to fix this!)
            t.commit();

        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
