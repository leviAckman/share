package com.levi.service;

import com.levi.bean.ReUser;
import org.apache.ibatis.annotations.Param;

public interface ReUserService {
    boolean register(ReUser reUser);
    ReUser login(ReUser reUser);
    double resultBalance(String u_name,String u_password);
}
