package com.revature.reimbursement;

import javax.persistence.*;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
    @Id
    @Column(name="id", columnDefinition="serial primary key")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="amount")
    private String amount;

    @Column(name="reason")
    private String reason;

    @Column(name="result")
    private String result;

    @Column(name="reviewedBy")
    private int reviewedBy;

    public Reimbursement() {
    }

    public Reimbursement(int user_id, String amount, String reason, String result, int reviewedBy) {
        this.user_id = user_id;
        this.amount = amount;
        this.reason = reason;
        this.result = result;
        this.reviewedBy = reviewedBy;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAmount() { return amount; }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() { return result; }

    public void setResult(String result) {this.result = result; }

    public int getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(int reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "userId=" + user_id +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                ", reviewedBy=" + reviewedBy +
                '}';
    }
}
