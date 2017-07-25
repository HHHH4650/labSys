package com.entity;

public class Jiaoshi {
	private int id;
	private String name;
	private String del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	
	@Override
	public String toString() {
		return "Jiaoshi [id=" + id + ", name=" + name + "]";
	}

}
