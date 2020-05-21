package com.calculator.calculat.or;

public class Calculator {
	private String name;
	private float x;
	private float y;
	private String o;
	private float result;	
	
	public Calculator(String name, float x, float y, String o, float result) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.o = o;
		this.result = result;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	
	public float getResult() {
		return result;
	}
	public void setResult(float result) {
		this.result = result;
	}

}
