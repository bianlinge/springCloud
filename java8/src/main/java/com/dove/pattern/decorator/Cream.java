package com.dove.pattern.decorator;

/**
 * Created by E1041 on 2020/4/14.
 */
public class Cream extends Food {
	private Food basic_food;

	public Cream(Food basic_food) {
		this.basic_food = basic_food;
	}

	@Override
	public String make() {
		return basic_food.make()+"+奶油";
	}
}
