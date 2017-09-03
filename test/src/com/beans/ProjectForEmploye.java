package com.beans;
/**
 * Class that represents a period of work of an employee on a project
 * 
 * 
 */
public class ProjectForEmploye {
	/**
	 * An employee has a role, an implication, a start and end date for the project identified by its name 
	 * 
	 * 
	 */
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
