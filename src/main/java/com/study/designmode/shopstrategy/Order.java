package com.study.designmode.shopstrategy;

public class Order {
    private String goodsId;

    private Double price;
    private Pay pay;

    public Order(String goodsId, Pay pay){
        this.goodsId =goodsId;
        this.pay = pay;
    }

    public void  paying(){
        this.pay.pay(goodsId);
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
