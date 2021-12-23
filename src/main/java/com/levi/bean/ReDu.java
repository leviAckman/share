package com.levi.bean;

public class ReDu {
    private int du_d_id;
    private int du_u_id;

    public int getDu_d_id() {
        return du_d_id;
    }

    public void setDu_d_id(int du_d_id) {
        this.du_d_id = du_d_id;
    }

    public int getDu_u_id() {
        return du_u_id;
    }

    public void setDu_u_id(int du_u_id) {
        this.du_u_id = du_u_id;
    }

    @Override
    public String toString() {
        return "ReDu{" +
                "du_d_id=" + du_d_id +
                ", du_u_id=" + du_u_id +
                '}';
    }
}
