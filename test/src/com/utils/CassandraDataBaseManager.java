package com.utils;

import com.beans.Employe;
import com.beans.ProjectForEmploye;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraDataBaseManager {
	
	String DEFINE_TYPE_PROJECTS = "";
	
	String CREATE_TABLE_EMPLOYE = "CREATE TABLE employees ( username text PRIMAREY KEY, adresse text, datedenaissance text, email text,nom text, prenom text, projectlist list<frozen<projects>>, root text);";
	
	public void initDataBase(){
		 Cluster cluster = Cluster.builder()
	              .addContactPoints("127.0.0.1")
	              .build();
	      Session session = cluster.connect();
	      session.execute(CREATE_TABLE_EMPLOYE);
		  session.close();
	}
	
	public void addEmployeInHistory(Employe employe){
		 Cluster cluster = Cluster.builder()
	              .addContactPoints("127.0.0.1")
	              .build();
	      String cqlInserthistoryemploye = "INSERT INTO myfirstcassandradb.history_employeinproject(username, projectslist) VALUES ('"+employe.getUsername()+"',null)";	     
	      Session session = cluster.connect();
	      session.execute(cqlInserthistoryemploye);
		  session.close();
	}
	
	public void addProjectInHistoryEmploye(Employe employe, ProjectForEmploye project){
		 Cluster cluster = Cluster.builder()
	              .addContactPoints("127.0.0.1")
	              .build();
	      String cqladdhistoryprojectforemploye = "UPDATE myfirstcassandradb.history_employeinproject SET projectslist = projectslist + [{name: '"+project.getName()+"', datedebut: '"+project.getDateDebut()+"', datefin: '"+project.getDateFin()+"', role : '"+project.getRole()+"', implication : '"+project.getImplication()+"'}] WHERE username = '"+employe.getUsername()+"'";	       
		  Session session = cluster.connect();
	      session.execute(cqladdhistoryprojectforemploye);
		  session.close();
	}
	
	public String extractEmployeeData(){	
		String output = "";
		return output;
		
	}
	
}
