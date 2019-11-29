package com.steam.redis.service;

import com.steam.redis.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlTemplate;

    public List<ProductModel> findProductInfo(){
        List<ProductModel> resultList = new ArrayList<>();

        return resultList;
    }


    public List<ProductModel> findProductSimpleInfo(){
        List<ProductModel>  resultList = new ArrayList<>();
        String sql = "select id,create_date,version,allocated_stock,cost,is_default price,market_price,sn,stock from xx_product where goods >=10 ";
        List<Map<String,Object>> dataList =  mysqlTemplate.queryForList(sql);;

        for (Map<String, Object> dataMap : dataList) {
            ProductModel model = new ProductModel();
            model.setId(Integer.valueOf(dataMap.get("id").toString()));
//            model.setCreateDate();
            model.setVersion(Long.valueOf(dataMap.get("version").toString()));
            model.setAllocatedStock(Integer.valueOf(dataMap.get("allocated_stock").toString()));
            double cost = dataMap.get("cost")==null? 0.0 : Double.valueOf(dataMap.get("cost").toString());
            model.setCost(new BigDecimal(cost).setScale(4));
//            model.setPrice(new BigDecimal(Double.valueOf(dataMap.get("price").toString())).setScale(4));
            model.setSn(dataMap.get("sn").toString());
            resultList.add(model);
        }

        return resultList;

    }
}
