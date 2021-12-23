package com.levi.service;

import com.levi.bean.ReData;

import java.util.List;


public interface ReDataService {
    List<ReData> finAll(int pageNum);

    List<ReData> findBuy(String u_name, String u_password, int pageNum, String controller);

    boolean insertReData(ReData reData);

    boolean buy(String u_name, String u_password, int d_id);

    String pageCount(String u_name, String u_password, boolean is);

    String count();
}
