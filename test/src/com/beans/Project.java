package com.beans;

import java.util.UUID;
/**
 * Class that represents a project
 * 
 * 
 */
public class Project {
	/**
	 * 
	 * 
	 * 
	 */
	private UUID id;
	private String name;
	private String description;
	private Boolean active;
	
	public Project(String nom, String description) {
		super();
		this.name = nom;
		this.description = description;
		this.id = UUID.randomUUID();
		this.active = true;
	}	
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String nom) {
		this.name = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
