package com.levi.service.impl;

import com.levi.bean.ReData;
import com.levi.bean.ReUser;
import com.levi.mapper.ReDataMapper;
import com.levi.mapper.ReDuMapper;
import com.levi.mapper.ReUserMapper;
import com.levi.service.ReDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/*ReData 业务类*/
@Service
public class ReDataServiceImpl implements ReDataService {
    @Autowired
    @Qualifier("reDataMapper")
    ReDataMapper reDataMapper;

    @Autowired
    @Qualifier("reUserMapper")
    ReUserMapper reUserMapper;

    @Autowired
    @Qualifier("reDuMapper")
    ReDuMapper reDurMapper;

    /*查询全部data数据*/
    public List<ReData> finAll(int pageNum) {
        return reDataMapper.finAll(pageNum);
    }

    /*添加*/
    public boolean insertReData(ReData reData) {
        int i = reDataMapper.insertReData(reData);
        if(i < 0){
            return false;
        }
        return true;
    }

    /*拿到已经购买的*/
    public List<ReData> findBuy(String u_name,String u_password,int pageNum,String controller) {
        ReUser reUser = reUserMapper.resultId(u_name, u_password);
        if(reUser != null){
            return reDataMapper.findBuy(reUser.getU_id(),pageNum,controller);
        }
        return null;
    }

    /*查询页码*/
    public String pageCount(String u_name,String u_password,boolean is){
        ReUser reUser=reUserMapper.resultId(u_name, u_password);
        if(reUser == null){
            return null;
        }
        if(is) {
            int aTrue = reDataMapper.pageCount(reUser.getU_id(), "true");
            Double ceil =(aTrue%2) == 0 ? 1:Math.ceil(aTrue / 2);
            return String.valueOf(ceil.intValue()+1)+" count: "+aTrue;
        }else{
            int aTrue = reDataMapper.pageCount(reUser.getU_id(), "false");
            Double ceil =(aTrue%3) == 0 ? 1:Math.ceil(aTrue / 3);
            return String.valueOf(ceil.intValue()+1)+" count: "+aTrue;
        }
    }

    /*购买*/
    public boolean buy(String u_name, String u_password, int d_id) {
        ReUser reUser= reUserMapper.resultId(u_name,u_password);
        ReData reData= reDataMapper.findById(d_id);
        /*判断 余额是否大于或等于要购买的商品*/
        if(reUser.getU_balance() >= reData.getD_price()) {
            /*查询要购买的商品是否存在*/
            if (reDurMapper.findReDu(reUser.getU_id(),reData.getD_id()) == null) {
                /*扣钱*/
                if (reUserMapper.buy(reUser.getU_id(), reData.getD_price()) > 0) {
                    /*添加购买商品*/
                    if (reDurMapper.insert(reData.getD_id(), reUser.getU_id()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String count() {
        int count = reDataMapper.count();
        Double ceil =(count%6) == 0 ? 1:Math.ceil(count / 6);
        return String.valueOf(ceil.intValue()+1)+" count: "+count;
    }
}
