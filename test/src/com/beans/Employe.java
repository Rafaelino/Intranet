package com.beans;

import java.util.List;

/**
 * Class that represents an employee
 * 
 * 
 */
public class Employe {
	/**
	 * 
	 * Different constructors according to available data on the employee 
	 * 
	 */
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String username;
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String email;
	private String adresse;
	private String password;
	private String admin;
	private List<String> projects;
	private String projectlistdraw;
	
	public String getProjectlistdraw() {
		return projectlistdraw;
	}

	public void setProjectlistdraw(String projectlistdraw) {
		this.projectlistdraw = projectlistdraw;
	}

	public List<String> getProjects() {
		return projects;
	}

	public Employe(String username, String nom, String prenom, String dateDeNaissance, String email, String adresse,
			String password, List<String> projects, String admin) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.email = email;
		this.adresse = adresse;
		this.password = password;
		this.admin = admin;
		this.projects = projects;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public Employe(String username, String nom, String prenom, String dateDeNaissance, String email, String adresse,
			String password, List<String> projects) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.email = email;
		this.adresse = adresse;
		this.password = password;
		this.projects = projects;
	}

	public Employe(String username, String nom, String prenom, String dateDeNaissance, String email, String adresse, String admin){
		
		this.username = username;
		this.dateDeNaissance = dateDeNaissance;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.admin = admin;
		
	}
public Employe(String email, String nom, String prenom){
		
		this.email = email;
		this.username = email.split("@")[0];
		this.nom = nom;
		this.prenom = prenom;
		if(email.split("@")[0].equals("rboulch") || email.split("@")[0].equals("rfetita") || email.split("@")[0].equals("amarzin")){
			this.admin = "yes";
		}else{
			this.admin = "no";
		}
		this.adresse="";
		this.password="";
		this.dateDeNaissance="....-..-..";
		
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
