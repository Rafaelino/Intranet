package com.test.beans;

public class EmployeSimple {
private String name;
private String role;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public EmployeSimple(String name, String role) {
	super();
	this.name = name;
	this.role = role;
} 

}
