package com.levi.mapper;

import com.levi.bean.ReDu;
import org.apache.ibatis.annotations.Param;

public interface ReDuMapper {
    /*使用id 关联用户购买的商品*/
    int insert(@Param("d_id")int d_id,@Param("u_id")int u_id);

    ReDu findReDu(@Param("du_u_id") int du_u_id,@Param("du_d_id") int du_d_id);
}
