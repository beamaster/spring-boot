package com.steam.lesson2.service.impl;

import com.steam.lesson2.model.ClientPowerModel;
import com.steam.lesson2.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    @Qualifier("localTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 避免重复插入
     * @param cpModelList
     */
    @Override
    public void avoidInesert(List<ClientPowerModel> cpModelList){
        String sql = "insert into se_client_power\n" +
                "(host_name,client_id,client_name,data_time,charge_power,discharge_power)\n" +
                "select ?,?,?,?,?,? from dual where not exists(\n" +
                "select host_name,client_id,client_name,data_time,charge_power,discharge_power from se_client_power where client_id=? and data_time=?);\n";
        List<Object[]> params = new ArrayList<>();
        for (ClientPowerModel cpModel : cpModelList){
            params.add(new Object[]{
                    cpModel.getHostName(),
                    cpModel.getClientId(),
                    cpModel.getClientName(),
                    cpModel.getDataTime(),
                    cpModel.getChargePower(),
                    cpModel.getDiscChargePower(),
                    cpModel.getClientId(),cpModel.getDataTime()//最后两个问号不能漏掉
            });
        }
        jdbcTemplate.batchUpdate(sql,params);
    }
}
