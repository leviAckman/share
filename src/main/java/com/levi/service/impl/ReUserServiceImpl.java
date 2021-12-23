package com.levi.service.impl;

import com.levi.bean.ReUser;
import com.levi.mapper.ReUserMapper;
import com.levi.service.ReUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/*ReUser 业务类*/
@Service
public class ReUserServiceImpl implements ReUserService {
    @Autowired
    @Qualifier("reUserMapper")
    ReUserMapper reUserMapper;

    /*注册*/
    public boolean register(ReUser reUser) {
        if(reUserMapper.checkRegister(reUser) > 0){
            return false;
        }
        if(reUserMapper.register(reUser) < 1){
            return false;
        }
        return true;
    }

    /*登录*/
    public ReUser login(ReUser reUser) {
        return reUserMapper.login(reUser);
    }

    /*查询余额*/
    public double resultBalance(String u_name, String u_password) {
        return  reUserMapper.resultBalance(u_name,u_password);
    }
}
