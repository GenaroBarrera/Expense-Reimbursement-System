package com.revature.dao;

import com.revature.reimbursement.Reimbursement;

import java.util.List;

//TODO add documentation
public interface ReimbursementDao {
    boolean addReimb(Reimbursement reimb);

    List<Reimbursement> getAllReimbPendingByID(int id);

    List<Reimbursement> getAllReimbPending();

    List<Reimbursement> getAllReimbResolved();

    List<Reimbursement> getAllReimbResolvedByID(int id);

    void updatePending(int id, String resolved);
}
