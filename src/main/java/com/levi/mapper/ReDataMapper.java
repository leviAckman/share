package com.levi.mapper;

import com.levi.bean.ReData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReDataMapper {
    List<ReData> finAll(int pageNum);

    List<ReData> findBuy(@Param("u_id")int u_id,@Param("pageNum")int pageNum,@Param("controller")String controller);

    ReData findById(int d_id);

    int insertReData(ReData reData);

    int buy(@Param("d_id")String d_id,@Param("u_id")String u_id);

    int pageCount(@Param("u_id")int u_id,@Param("controller")String controller);

    int count();
}
