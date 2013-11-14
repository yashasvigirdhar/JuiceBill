package com.itsdark.apps.juicebill;

public class JuiceData {

	public String name;
	public int count;
	public int cost;

	public JuiceData(String name, int count, int cost) {
		this.name = name;
		this.count = count;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}

	public int getcost() {
		return cost;
	}

	public void setcost(int cost) {
		this.cost = cost;
	}

}