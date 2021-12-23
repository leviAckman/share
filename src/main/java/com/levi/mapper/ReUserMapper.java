package com.levi.mapper;

import com.levi.bean.ReUser;
import org.apache.ibatis.annotations.Param;

/*ReUser mapper类*/
public interface ReUserMapper {
    int register(ReUser reUser);
    ReUser login(ReUser reUser);
    int checkRegister(ReUser reUser);
    ReUser resultId(@Param("u_name") String u_name,@Param("u_password") String u_password);
    double resultBalance(@Param("u_name") String u_name,@Param("u_password") String u_password);
    int buy(@Param("u_id")int u_id,@Param("d_price")double d_price);
}
