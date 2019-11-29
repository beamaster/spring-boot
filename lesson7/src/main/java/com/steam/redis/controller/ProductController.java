package com.steam.redis.controller;

import com.steam.redis.model.ProductModel;
import com.steam.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/productInfo")
    public List<ProductModel> getProductSimpleInfo(){
        List<ProductModel> dataList = productService.findProductSimpleInfo();
        System.out.println(dataList.size());
        return dataList;
    }




    @RequestMapping("/redis")
    public String helloRdis(){
        return "lucky redis...";
    }
}
