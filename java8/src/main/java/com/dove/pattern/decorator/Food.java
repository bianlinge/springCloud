package com.dove.pattern.decorator;

/**
 * Created by E1041 on 2020/4/14.
 */
public class Food {
	private String food_name;

	public Food() {
	}

	public Food(String food_name) {
		this.food_name = food_name;
	}

	public String make() {
		return food_name;
	}
}
