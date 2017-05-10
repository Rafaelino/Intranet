package com.test.beans;

import java.util.UUID;

public class Project {
	
	private UUID id;
	private String nom;
	private String description;
	
	public Project(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
		this.id = UUID.randomUUID();
	}	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
