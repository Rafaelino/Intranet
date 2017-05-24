package com.test.utils;


import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.test.beans.Employe;
import com.test.beans.Project;
import com.test.beans.ProjectForEmploye;
import com.test.utils.*;

public class CassandraProjetUtils {

		public Boolean ajouterProjet(Project projet){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqlwhere = "SELECT * FROM myfirstcassandradb.projects WHERE name = '" + projet.getNom() +"'";
	         List<Row> resultList = session.execute(cqlwhere).all();
	         if(!(resultList.isEmpty())){
		         if(resultList.get(0).getString(1).equals(projet.getNom())){
		        	 return false;//L'employé n'a pas été ajouté il est déja présent dans la base
		         }}
		        	
	         String cqlInsert = "INSERT INTO myfirstcassandradb.projects(name, description, employelist) VALUES ('"+projet.getNom()+"','"+projet.getDescription().replace("'", "''")+"',null)";	     
	         System.out.println(cqlInsert);
	         session.execute(cqlInsert);
		      session.close();

	       	 return true;//L'employé est ajouté dans la base.
		}
		
		public List<String> getAllProjects(){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.projects";
	         session.execute(cqlStatement3);
	         List<Row> res;
	         res = session.execute(cqlStatement3).all();
	         List<String> resultat = new ArrayList<String>();
	         for (int i = 0; i < res.size(); i++) {  	
	        	resultat.add(res.get(i).getString(0));			
			}
		      session.close();

	         cluster.close();
	         return resultat;
		}
		public List<String> getAllWorkers(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         System.out.println(project.getNom());
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getNom()+"'";
	         List<Row> res;
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<com.datastax.driver.core.UDTValue> listemploye = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<String> resultat = new ArrayList<String>();
	         resultat.add("");
	         listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
		        	listemploye.add(listemployeres.get(i));	
		        	if(app2.getEmployeByUsername(listemploye.get(i).getString("name")).getNom() != null){
		        		if(listemploye.get(i).getString("role").equals("Collaborateur")){
		        	resultat.add(app2.getEmployeByUsername(listemploye.get(i).getString("name")).getNom()+ " "+app2.getEmployeByUsername(listemploye.get(i).getString("name")).getPrenom());	
		        		}
		        	}
	        	 }
	         }
	         resultat.remove(0);
		      session.close();

	         cluster.close();
	         return resultat;
		}
		public List<String> getAllManagers(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         System.out.println(project.getNom());
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getNom()+"'";
	         List<Row> res;
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<com.datastax.driver.core.UDTValue> listemploye = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<String> resultat = new ArrayList<String>();
	         resultat.add("");
	         listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
		        	listemploye.add(listemployeres.get(i));	
		        	if(app2.getEmployeByUsername(listemploye.get(i).getString("name")).getNom() != null){
		        		if(listemploye.get(i).getString("role").equals("Chef de projet")){
		        	resultat.add(app2.getEmployeByUsername(listemploye.get(i).getString("name")).getNom()+ " "+app2.getEmployeByUsername(listemploye.get(i).getString("name")).getPrenom());	
		        		}
		        	}
	        	 }
	         }
	         resultat.remove(0);
	         session.close();
	         cluster.close();
	         return resultat;
		}
		public Project getProjectByName(String name){
			Cluster cluster = Cluster.builder()
	                .addContactPoints("127.0.0.1")
	                .build();
	        Session session = cluster.connect();
	        String cqlWhereName = "SELECT * FROM myfirstcassandradb.projects WHERE name = '" + name +"'";
	        List<Row> resultList  = session.execute(cqlWhereName).all();
		        Project repProject = new Project(resultList.get(0).getString(0),resultList.get(0).getString(1));
		    session.close();
	        cluster.close();
	        return repProject;
		}
		
		public Boolean modifierProjet(Project projet){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqlwhere = "SELECT * FROM myfirstcassandradb.projects WHERE name = '" + projet.getNom() +"'";
	         if(session.execute(cqlwhere).all().get(0).getString(0).equals(projet.getNom())){
	        	 String cqlUpdate = "UPDATE myfirstcassandradb.projects SET description = '"+projet.getDescription().replaceAll("'", "''")+"' WHERE name ='"+projet.getNom()+"'";
	        	System.out.println(cqlUpdate);	
	        	 session.execute(cqlUpdate); 
	        	 session.close();
	        		return true;//L'employé a été modifié
	         }else{    
	        	    return false;//L'employé n'a pas été modifié il n'existe pas dans la base 
	         }
	        
		}
		public Boolean addEmployeForProject(Project project, Employe employe, String role){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints("127.0.0.1")
		              .build();
		      Session session = cluster.connect();
		      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist + [{name: '"+employe.getUsername()+"', role: '"+role+"'}] WHERE name = '"+project.getNom()+"'";
			  System.out.println(cqladdprojectforemploye);
		      session.execute(cqladdprojectforemploye);
		      session.close();
		      cluster.close();
			  return false;
		  }
		
		public Boolean employeAlreadyInProject(Employe employe, Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
			
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getNom()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();      
	         listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 if(listemployeres.get(i).getString("name").equals(employe.getUsername())){
	        			 return true;
	        		 }
		         
	        	 }
	         }
		      session.close();

	         cluster.close();       
			return false;
		}
		
		public Boolean deleteEmployeForProject(Employe employe, Project project, String role){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqldelemployeforproject = "UPDATE myfirstcassandradb.projects SET employelist = employelist - [{name: '"+employe.getUsername()+"', role: '"+role+"'}] WHERE name = '"+project.getNom()+"'";
	         System.out.println(cqldelemployeforproject);
	         session.execute(cqldelemployeforproject);
			
			return false;
		}
		public void deleteProject(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         CassandraEmployeUtils app = new CassandraEmployeUtils();
	         List<Employe> employelist = app.getAllEmployesObjects();
	         for (int i = 0; i < employelist.size(); i++) {
				List<String> projectlist = employelist.get(i).getProjects();
				for (int j = 0; j < projectlist.size(); j++) {
					if(projectlist.get(j).split(";")[0].equals(project.getNom())){
							ProjectForEmploye projectforemploye = new ProjectForEmploye(projectlist.get(j).split(";")[1], projectlist.get(j).split(";")[0], projectlist.get(j).split(";")[2], projectlist.get(j).split(";")[3],projectlist.get(j).split(";")[4]);
							deleteProjectForEmploye(projectforemploye, employelist.get(i));
					}
				}
			}
	       
	         String cqldeleteproject="DELETE FROM myfirstcassandradb.projects WHERE name='"+project.getNom()+"'";
	         
	         session.execute(cqldeleteproject);
			
		}
		public ProjectForEmploye getProjectForEmploye(Employe employe, Project project, String role){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
	         System.out.println(employe.getUsername());
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 if(listemployeres.get(i).getString("name").equals(project.getNom()) && listemployeres.get(i).getString("role").equals(role)){
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), project.getNom(), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			return projectforemploye;
	        		 }
		         
	        	 }
	         }
	         cluster.close();    
	         return null;
		}
		
		 public Boolean modifyProjectForEmploye(ProjectForEmploye projectForEmploye,Project project, Employe employe, String datedebut, String datefin, String role, String implication){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints("127.0.0.1")
		              .build();
		      Session session = cluster.connect();
		      String cqlsubprojectforemploye = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist - [{name: '"+projectForEmploye.getName()+"', datedebut: '"+projectForEmploye.getDateDebut()+"', datefin: '"+projectForEmploye.getDateFin()+"', role : '"+projectForEmploye.getRole()+"', implication : '"+projectForEmploye.getImplication()+"'}] WHERE username = '"+employe.getUsername()+"'";
		      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist + [{name: '"+project.getNom()+"', datedebut: '"+datedebut+"', datefin: '"+datefin+"', role : '"+role+"', implication : '"+implication+"'}] WHERE username = '"+employe.getUsername()+"'";

		      System.out.println(cqladdprojectforemploye);
		      session.execute(cqlsubprojectforemploye);
		      session.execute(cqladdprojectforemploye);
			  session.close();
			  return false;
		  }
		 public Boolean modifyEmployeForProject(Project project, Employe employe, String oldrole, String newrole){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints("127.0.0.1")
		              .build();
		      Session session = cluster.connect();
		      String cqlsubprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist - [{name: '"+employe.getUsername()+"', role: '"+oldrole+"'}] WHERE name = '"+project.getNom()+"'";
		      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist + [{name: '"+employe.getUsername()+"', role: '"+newrole+"'}] WHERE name = '"+project.getNom()+"'";

		      session.execute(cqlsubprojectforemploye);
		      session.execute(cqladdprojectforemploye);
			  session.close();
			  return false;
		  }
		public List<ProjectForEmploye> getProjectsForEmploye(Employe employe){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         List<ProjectForEmploye> projectforemployelist = new ArrayList<ProjectForEmploye>();
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), listemployeres.get(i).getString("name"), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			projectforemployelist.add(projectforemploye);
	        		 }
		         
	        	 } 
	         session.close();
	         cluster.close();    
	         return projectforemployelist;
	         }
		
	        
		
		public Boolean deleteProjectForEmploye(ProjectForEmploye projectforemploye, Employe employe){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints("127.0.0.1")
	                 .build();
	         Session session = cluster.connect();
	         String cqldelemployeforproject = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist - [{name: '"+projectforemploye.getName()+"', role: '"+projectforemploye.getRole()+"', datedebut: '"+projectforemploye.getDateDebut()+"', datefin: '"+projectforemploye.getDateFin()+"'}] WHERE username = '"+employe.getUsername()+"'";
	         System.out.println(cqldelemployeforproject);
	         session.execute(cqldelemployeforproject);
		      session.close();

			return false;
		}
}
