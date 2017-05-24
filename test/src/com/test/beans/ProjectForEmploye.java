package com.test.beans;

public class ProjectForEmploye {
	
	private String role;
	private String name;
	private String dateDebut;
	private String dateFin;
	private String implication;

	public String getImplication() {
		return implication;
	}

	public void setImplication(String implication) {
		this.implication = implication;
	}

	public ProjectForEmploye(String role, String name, String dateDebut, String dateFin, String implication) {
		this.role = role;
		this.name = name;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.implication = implication;
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
