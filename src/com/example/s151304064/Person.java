package com.example.s151304064;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Person {

	private Long id;
	private String name;
	private String number;
	private  Long photoid;
	
	public Long getPhotoid() {
		return photoid;
	}
	public void setPhotoid(Long photoid) {
		this.photoid = photoid;
	}
	public Person(){
		
	}
	public String toString(){
		return "Person [id="+id+", name="+name+", number="+number+"]";
	}
	public Person(Long id,String name,String number,Long photoid){
		this.id=id;
		this.name=name;
		this.number=number;
		this.photoid=photoid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
