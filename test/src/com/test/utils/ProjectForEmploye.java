package com.test.utils;

public class ProjectForEmploye {
	
	private String role;
	private String name;
	private String dateDebut;
	private String dateFin;
	
	public ProjectForEmploye(String role, String name, String dateDebut, String dateFin) {
		this.role = role;
		this.name = name;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	
}
