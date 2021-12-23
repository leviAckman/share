package com.levi.bean;

/*用户实体类*/
public class ReUser {
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_email;
    private double u_balance;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public double getU_balance() {
        return u_balance;
    }

    public void setU_balance(double u_balance) {
        this.u_balance = u_balance;
    }

    @Override
    public String toString() {
        return "ReUser{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_balance=" + u_balance +
                '}';
    }
}
