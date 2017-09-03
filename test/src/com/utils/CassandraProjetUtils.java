package com.utils;

/**
 * Class that handles database interaction for project management 
 * 
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.beans.Employe;
import com.beans.Project;
import com.beans.ProjectForEmploye;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.utils.*;

public class CassandraProjetUtils {
	public static String SERVER_ADRESS = "127.0.0.1";

	public CassandraProjetUtils(){
		super();
		final Properties properties = new Properties();
		try (final InputStream stream =
		           this.getClass().getResourceAsStream("config.properties")) {
		    try {
				properties.load(stream);
			} catch (IOException e) {
			}
		    SERVER_ADRESS = properties.getProperty("SERVER_ADRESS");
		} catch (IOException e1) {
		}
	}
	

		public Boolean ajouterProjet(Project projet){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlwhere = "SELECT * FROM myfirstcassandradb.projects WHERE name = '" + projet.getName() +"'";
	         List<Row> resultList = session.execute(cqlwhere).all();
	         if(!(resultList.isEmpty())){
		         if(resultList.get(0).getString(1).equals(projet.getName())){
		        	 return false;//L'employé n'a pas été ajouté il est déja présent dans la base
		         }}	
		        	
	         String cqlInsert = "INSERT INTO myfirstcassandradb.projects(name, description, employelist) VALUES ('"+projet.getName()+"','"+projet.getDescription().replace("'", "''")+"',null)";	     
	         session.execute(cqlInsert);
		      session.close();

	       	 return true;//L'employé est ajouté dans la base.
		}
		public List<Project> getAllProjectsObjects(){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.projects";
	         session.execute(cqlStatement3);
	         List<Row> res;
	         res = session.execute(cqlStatement3).all();
	         List<Project> resultat = new ArrayList<Project>();
	         for (int i = 0; i < res.size(); i++) {  	
	        	resultat.add(getProjectByName(res.get(i).getString(0)));			
			}
		      session.close();

	         cluster.close();
	         return resultat;
		}
		public List<String> getAllProjects(){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
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
		public String getAllProjectsForExport(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
	         session.execute(cqlStatement3);
	         List<Row> res;
	         res = session.execute(cqlStatement3).all();
	      
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
		       
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<com.datastax.driver.core.UDTValue> listemploye = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<String> resultat = new ArrayList<String>();
	         resultat.add("");
	         listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
	         String output= "[";
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
		        	listemploye.add(listemployeres.get(i));	
		        	if(app2.getEmployeByUsername(listemploye.get(i).getString("name")).getNom() != null){
		        		

			        	output+=("{name: '"+listemployeres.get(i).getString("name")+ "', role: '"+listemployeres.get(i).getString("role")+"'},");		
		        	}
	        	 }
	         }else{
	        	 output += " ";
	         }
	         output = output.substring(0,output.length()-1);
	         output+="]";
        
		      session.close();

	         cluster.close();
	         return output;
		}
		public List<String> getAllWorkers(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
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
		        		if(listemploye.get(i).getString("role").equals("Collaborateur") || listemploye.get(i).getString("role").equals("Collaborator")){
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
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
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
		        		if(listemploye.get(i).getString("role").equals("Chef de projet") || listemploye.get(i).getString("role").equals("Project manager")){
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
	                .addContactPoints(SERVER_ADRESS)
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
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlwhere = "SELECT * FROM myfirstcassandradb.projects WHERE name = '" + projet.getName() +"'";
	         if(session.execute(cqlwhere).all().get(0).getString(0).equals(projet.getName())){
	        	 String cqlUpdate = "UPDATE myfirstcassandradb.projects SET description = '"+projet.getDescription().replaceAll("'", "''")+"' WHERE name ='"+projet.getName()+"'";
	        	 session.execute(cqlUpdate); 
	        	 session.close();
	        		return true;//L'employé a été modifié
	         }else{    
	        	    return false;//L'employé n'a pas été modifié il n'existe pas dans la base 
	         }
	        
		}
		public Boolean addEmployeForProject(Project project, Employe employe, String role){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints(SERVER_ADRESS)
		              .build();
		      Session session = cluster.connect();
		      if(!(employeAlreadyInProjectRole(employe, project,role))){
			      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist + [{name: '"+employe.getUsername()+"', role: '"+role+"'}] WHERE name = '"+project.getName()+"'";
			      session.execute(cqladdprojectforemploye);
		      }
		      session.close();
		      cluster.close();
			  return false;
		  }
		
		public Boolean employeAlreadyInProject(Employe employe, Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
			
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
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
		public Boolean employeAlreadyInProjectRole(Employe employe, Project project, String role){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
			
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();      
	         listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 if(listemployeres.get(i).getString("name").equals(employe.getUsername()) && listemployeres.get(i).getString("role").equals(role) ){
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
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqldelemployeforproject = "UPDATE myfirstcassandradb.projects SET employelist = employelist - [{name: '"+employe.getUsername()+"', role: '"+role+"'}] WHERE name = '"+project.getName()+"'";
	         session.execute(cqldelemployeforproject);
			
			return false;
		}
		public void deleteProject(Project project){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         CassandraEmployeUtils app = new CassandraEmployeUtils();
	         List<Employe> employelist = app.getAllEmployesObjects();
	         for (int i = 0; i < employelist.size(); i++) {
				List<String> projectlist = employelist.get(i).getProjects();
				for (int j = 0; j < projectlist.size(); j++) {
					if(projectlist.get(j).split(";")[0].equals(project.getName())){
							ProjectForEmploye projectforemploye = new ProjectForEmploye(projectlist.get(j).split(";")[1], projectlist.get(j).split(";")[0], projectlist.get(j).split(";")[2], projectlist.get(j).split(";")[3],projectlist.get(j).split(";")[4]);
							deleteProjectForEmploye(projectforemploye, employelist.get(i));
					}
				}
			}
	       
	         String cqldeleteproject="DELETE FROM myfirstcassandradb.projects WHERE name='"+project.getName()+"'";
	         
	         session.execute(cqldeleteproject);
			
		}
		public ProjectForEmploye getProjectForEmploye(Employe employe, Project project, String role){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 if(listemployeres.get(i).getString("name").equals(project.getName()) && listemployeres.get(i).getString("role").equals(role)){
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), project.getName(), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			return projectforemploye;
	        		 }
		         
	        	 }
	         }
	         cluster.close();    
	         return null;
		}
		public ProjectForEmploye getProjectForEmployeDate(Employe employe, Project project, String role, String datedebut, String datefin){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         CassandraEmployeUtils app2 = new CassandraEmployeUtils();
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 if(listemployeres.get(i).getString("name").equals(project.getName()) && listemployeres.get(i).getString("role").equals(role) && listemployeres.get(i).getString("datedebut").equals(datedebut) && listemployeres.get(i).getString("datefin").equals(datefin)){
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), project.getName(), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			return projectforemploye;
	        		 }
		         
	        	 }
	         }
	         cluster.close();    
	         return null;
		}
		 public Boolean modifyProjectForEmploye(ProjectForEmploye projectForEmploye,Project project, Employe employe, String datedebut, String datefin, String role, String implication){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints(SERVER_ADRESS)
		              .build();
		      Session session = cluster.connect();
		      String cqlsubprojectforemploye = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist - [{name: '"+projectForEmploye.getName()+"', datedebut: '"+projectForEmploye.getDateDebut()+"', datefin: '"+projectForEmploye.getDateFin()+"', role : '"+projectForEmploye.getRole()+"', implication : '"+projectForEmploye.getImplication()+"'}] WHERE username = '"+employe.getUsername()+"'";
		      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist + [{name: '"+project.getName()+"', datedebut: '"+datedebut+"', datefin: '"+datefin+"', role : '"+role+"', implication : '"+implication+"'}] WHERE username = '"+employe.getUsername()+"'";

		      session.execute(cqlsubprojectforemploye);
		      session.execute(cqladdprojectforemploye);
			  session.close();
			  return false;
		  }
		 public Boolean modifyEmployeForProject(Project project, Employe employe, String oldrole, String newrole){
			  Cluster cluster = Cluster.builder()
		              .addContactPoints(SERVER_ADRESS)
		              .build();
		      Session session = cluster.connect();
		      String cqlsubprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist - [{name: '"+employe.getUsername()+"', role: '"+oldrole+"'}] WHERE name = '"+project.getName()+"'";
		      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.projects SET employelist = employelist + [{name: '"+employe.getUsername()+"', role: '"+newrole+"'}] WHERE name = '"+project.getName()+"'";

		      session.execute(cqlsubprojectforemploye);
		      session.execute(cqladdprojectforemploye);
			  session.close();
			  return false;
		  }
		public List<ProjectForEmploye> getProjectsForEmploye(Employe employe){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<ProjectForEmploye> projectforemployelist = new ArrayList<ProjectForEmploye>();
	         if (!(res.isEmpty())){
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 	int already = 0;
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), listemployeres.get(i).getString("name"), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			for (int j = 0; j < projectforemployelist.size(); j++) {
							
							System.out.println("----"+ projectforemployelist.get(j).getName()+"equals"+listemployeres.get(i).getString("name"));
	        				if(projectforemployelist.get(j).getName().equals(listemployeres.get(i).getString("name"))){
								already = 1;
							}	        				
						}
	        			if (already == 0 ){
	        			projectforemployelist.add(projectforemploye);
	        			}
	        		 }
		         
	        	 } 
	         }
	         session.close();
	         cluster.close();    
	         return projectforemployelist;
	         }
		public List<ProjectForEmploye> getProjectsForEmployeExtra(Employe employe){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<ProjectForEmploye> projectforemployelist = new ArrayList<ProjectForEmploye>();
	         if (!(res.isEmpty())){
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 
	        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), listemployeres.get(i).getString("name"), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
	        			projectforemployelist.add(projectforemploye);
	        			
	        		 }
		         
	        	 } 
	         }
	         session.close();
	         cluster.close();    
	         return projectforemployelist;
	         }
		public List<ProjectForEmploye> getProjectsForEmploye(Employe employe, Project projet){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<ProjectForEmploye> projectforemployelist = new ArrayList<ProjectForEmploye>();
	         if (!(res.isEmpty())){
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 	if(listemployeres.get(i).getString("name").equals(projet.getName())){
		        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), listemployeres.get(i).getString("name"), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
		        			projectforemployelist.add(projectforemploye);
		        		}
	        		 }
		         
	        	 } 
	         }
	         session.close();
	         cluster.close();    
	         return projectforemployelist;
	         }
		public List<ProjectForEmploye> getProjectsForEmploye(Employe employe, Project projet, String role){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
	         List<Row> res;
	         res = session.execute(cqlStatementselect).all();
	         List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
	         List<ProjectForEmploye> projectforemployelist = new ArrayList<ProjectForEmploye>();
	         if (!(res.isEmpty())){
	         listemployeres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
	         if(!(listemployeres).isEmpty()){
	        	 for (int i = 0; i < listemployeres.size(); i++) {  	
	        		 	if(listemployeres.get(i).getString("name").equals(projet.getName()) && listemployeres.get(i).getString("role").equals(role)){
		        			ProjectForEmploye projectforemploye = new ProjectForEmploye(listemployeres.get(i).getString("role"), listemployeres.get(i).getString("name"), listemployeres.get(i).getString("datedebut"), listemployeres.get(i).getString("datefin"),listemployeres.get(i).getString("implication"));
		        			projectforemployelist.add(projectforemploye);
		        		}
	        		 }
		         
	        	 } 
	         }
	         session.close();
	         cluster.close();    
	         return projectforemployelist;
	         }
	        public Boolean isProjectForEmployeSamePeriod(ProjectForEmploye oldproject, ProjectForEmploye newproject){
	        	int datedebutoldproject = Integer.parseInt(oldproject.getDateDebut().replaceAll("-",""));
	        	int datefinoldproject = Integer.parseInt(oldproject.getDateFin().replaceAll("-",""));

	        	int datedebutnewproject = Integer.parseInt(newproject.getDateDebut().replaceAll("-",""));
	        	int datefinnewproject = Integer.parseInt(newproject.getDateFin().replaceAll("-",""));
	        	if (datedebutnewproject < datedebutoldproject){
	        		if(datefinnewproject < datedebutoldproject){
	        			return false;
	        		}else{
	        			return true;
	        		}
	        	}
	        	if(datedebutnewproject > datefinoldproject){
	        		return false;
	        	}else{
	        		return true;
	        	}
	        }
		public Boolean isPeriodProjectOk(Employe employe, ProjectForEmploye projectForEmploye){
			List<ProjectForEmploye> projectlist = getProjectsForEmploye(employe, getProjectByName(projectForEmploye.getName()));
			for (int i = 0; i < projectlist.size(); i++) {
				Boolean tempres;
				//System.out.println("==+>"+projectlist.get(i).getDateDebut()+"->"+projectlist.get(i).getDateFin());
				tempres = isProjectForEmployeSamePeriod(projectlist.get(i),projectForEmploye);
				if (tempres == true){
					return false;
				}
			}
			
			return true;
		}
		public Boolean deleteProjectForEmploye(ProjectForEmploye projectforemploye, Employe employe){
			 Cluster cluster = Cluster.builder()
	                 .addContactPoints(SERVER_ADRESS)
	                 .build();
	         Session session = cluster.connect();
	         List<ProjectForEmploye> listprojet = getProjectsForEmploye(employe, getProjectByName(projectforemploye.getName()), projectforemploye.getRole());
	         String cqldelemployeforproject = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist - [{name: '"+projectforemploye.getName()+"', role: '"+projectforemploye.getRole()+"', datedebut: '"+projectforemploye.getDateDebut()+"', datefin: '"+projectforemploye.getDateFin()+"', implication :'"+projectforemploye.getImplication()+"'}] WHERE username = '"+employe.getUsername()+"'";
	         session.execute(cqldelemployeforproject);
	         if (listprojet.size() == 1){
		     deleteEmployeForProject(employe, getProjectByName(projectforemploye.getName()),projectforemploye.getRole());}
	         session.close();

			return false;
		}
}
