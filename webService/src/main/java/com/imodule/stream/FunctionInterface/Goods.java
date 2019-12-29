package com.imodule.stream.FunctionInterface;

/**
 * @author Administrator
 */
public class Goods {
    private String goodName;
    private Double cost;

    public Goods(String goodName, Double cost) {
        this.goodName = goodName;
        this.cost = cost;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodName='" + goodName + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
