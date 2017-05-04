package com.test.utils;

import java.util.List;

public class Employe {
	
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
	private List<String> projects;
	
	public List<String> getProjects() {
		return projects;
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

	public Employe(String username, String nom, String prenom, String dateDeNaissance, String email, String adresse, String password){
		
		this.username = username;
		this.dateDeNaissance = dateDeNaissance;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.password = password;
		
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
