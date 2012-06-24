package com.cs.shopper.client.entities.user;


import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.cs.shopper.client.entities.location.City;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class User extends GWTSerializable{
	@Id private String username;
	private City city;
	private String email;
	private String password;
	public User(){
		setUsername("");
		setPassword("");
		
	}
	public User(String username, String password,City city, String email){
		this.setUsername(username);
		this.setPassword(password);
		this.setCity(city);
		this.setEmail(email);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString(){
		return "Username: "+username+"\nPassword: "+password;
	}
	public boolean same(User user){
		if(this.username.equals(user.username)&&
				this.password.equals(user.password)) return true;
		return false;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
