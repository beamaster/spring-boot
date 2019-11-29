package com.steam.redis.model;
import java.io.Serializable;
import	java.math.BigDecimal;

import java.util.Date;

/**
 *
 */
public class ProductModel implements Serializable {


    private static final long serialVersionUID = -6012369237449139968L;

    private int id;

    private Date createDate;

    private Date modifyDate;

    private long version;

    private int allocatedStock;

    private BigDecimal cost;

    private long exchangePoint;

    private boolean isDefault;

    private BigDecimal marketPrice;

    private BigDecimal price;

    private BigDecimal market;

    private long rewardPoint;

    private String sn;

    private String specificationValues;

    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getAllocatedStock() {
        return allocatedStock;
    }

    public void setAllocatedStock(int allocatedStock) {
        this.allocatedStock = allocatedStock;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public long getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(long exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarket() {
        return market;
    }

    public void setMarket(BigDecimal market) {
        this.market = market;
    }

    public long getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(long rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(String specificationValues) {
        this.specificationValues = specificationValues;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
