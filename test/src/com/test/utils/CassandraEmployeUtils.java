package com.test.utils;

import com.test.beans.Employe;
import com.test.beans.Project;
import com.test.beans.ProjectForEmploye;
import com.test.utils.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.omg.CosNaming.NamingContextPackage.AlreadyBound;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;

public class CassandraEmployeUtils 
{
	public static String SERVER_ADRESS = "127.0.0.1";

	public CassandraEmployeUtils(){
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
	
	public String afficherTable(){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.employees";
         session.execute(cqlStatement3);
         List<Row> res;
         res = session.execute(cqlStatement3).all();
         String resultat="";
         for (int i = 0; i < res.size(); i++) {
        	 resultat += "- ";
        	 for (int j = 0; j < 6; j++) {
				resultat += res.get(i).getString(j) +" ";
			}
        	 resultat += " " + resultat.length() + " ";
			
		}
         cluster.close();
         return resultat;
	}
	public List<String> getAllEmployes(){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.employees";
         session.execute(cqlStatement3);
         List<Row> res;
         res = session.execute(cqlStatement3).all();
         List<String> resultat = new ArrayList<String>();
         for (int i = 0; i < res.size(); i++) {  	
        	resultat.add(res.get(i).getString(4) + " " +res.get(i).getString(6) + "@" + res.get(i).getString(0));			
		}
         cluster.close();
         return resultat;
	}
	 public List<Employe> getAllEmployesObjects(){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.employees";
         session.execute(cqlStatement3);
         List<Row> res;
         res = session.execute(cqlStatement3).all();
         List<Employe> resultat = new ArrayList<Employe>();
         for (int i = 0; i < res.size(); i++) {  	
         	resultat.add(getEmployeByUsername(res.get(i).getString(0)));			
 		}
         cluster.close();
         return resultat;
	  }
	 public Boolean isEmployeeInDatabase(String email){
		 Cluster cluster = Cluster.builder()
	                .addContactPoints(SERVER_ADRESS)
	                .build();
	        Session session = cluster.connect();
	        String cqlWhereEmail = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + email.split("@")[0] +"'";
	        List<Row> resultList  = session.execute(cqlWhereEmail).all();
	        List<com.datastax.driver.core.UDTValue> listprojectres = new ArrayList<com.datastax.driver.core.UDTValue>();   
	        Employe repEmploye = new Employe();
	        session.close();
	        cluster.close();
	        if(resultList.isEmpty()){
	        	return false;
	        }else{
	        return true;}
	 }
	public Employe getEmployeByName(String name){
		Cluster cluster = Cluster.builder()
                .addContactPoints(SERVER_ADRESS)
                .build();
        Session session = cluster.connect();
        String cqlWhereEmail = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + name.split(" ")[1].substring(0,1).toLowerCase()+name.split(" ")[0].toLowerCase() +"'";
        List<Row> resultList  = session.execute(cqlWhereEmail).all();
        Employe repEmploye = new Employe(resultList.get(0).getString(0),resultList.get(0).getString(4),resultList.get(0).getString(6),resultList.get(0).getString(2),resultList.get(0).getString(3),resultList.get(0).getString(1),resultList.get(0).getString(5));
        session.close();

        cluster.close();
        return repEmploye;
	}
	public Employe getEmployeByNameReverse(String name){
		Cluster cluster = Cluster.builder()
                .addContactPoints(SERVER_ADRESS)
                .build();
        Session session = cluster.connect();
        String cqlWhereEmail = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + name.split(" ")[0].substring(0,1).toLowerCase()+name.split(" ")[1].toLowerCase() +"'";
        List<Row> resultList  = session.execute(cqlWhereEmail).all();
        Employe repEmploye = new Employe(resultList.get(0).getString(0),resultList.get(0).getString(4),resultList.get(0).getString(6),resultList.get(0).getString(2),resultList.get(0).getString(3),resultList.get(0).getString(1),resultList.get(0).getString(5));
        session.close();

        cluster.close();
        return repEmploye;
	}
	public Employe getEmployeByUsername(String name){
		Cluster cluster = Cluster.builder()
                .addContactPoints(SERVER_ADRESS)
                .build();
        Session session = cluster.connect();
        String cqlWhereEmail = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + name +"'";
        List<Row> resultList  = session.execute(cqlWhereEmail).all();
        List<com.datastax.driver.core.UDTValue> listprojectres = new ArrayList<com.datastax.driver.core.UDTValue>();   
        Employe repEmploye = new Employe();
        if(!(resultList.isEmpty())){
        listprojectres = resultList.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
        List<String> projects = new ArrayList<String>();
        if(!(listprojectres).isEmpty()){
        	 for (int i = 0; i < listprojectres.size(); i++) {  	
        		projects.add(listprojectres.get(i).getString("name")+";"+listprojectres.get(i).getString("role")+";"+listprojectres.get(i).getString("datedebut")+";"+listprojectres.get(i).getString("datefin")+";"+listprojectres.get(i).getString("implication"));  	         
        	 }
         }
        
        if(!(resultList.isEmpty())){
        repEmploye = new Employe(resultList.get(0).getString(0),resultList.get(0).getString(4),resultList.get(0).getString(6),resultList.get(0).getString(2),resultList.get(0).getString(3),resultList.get(0).getString(1),resultList.get(0).getString(5),projects,resultList.get(0).getString(8));
        }}
        session.close();
        cluster.close();
        return repEmploye;
	}
	
	public Boolean isEmployeAdmin(Employe employe){
		return (employe.getAdmin().equals("yes"));
	}
	public Boolean ajouterEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         String cqlwhere = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + employe.getUsername() +"'";
         List<Row> resultList = session.execute(cqlwhere).all();
         if(!(resultList.isEmpty())){
	         if(resultList.get(0).getString(0).equals(employe.getUsername())){
	        	 return false;//L'employé n'a pas été ajouté il est déja présent dans la base
	         }}
	        	
         String cqlInsert = "INSERT INTO myfirstcassandradb.employees(username, nom, prenom, datedenaissance, email, adresse, password,root) VALUES ('"+employe.getUsername()+"','"+employe.getNom()+"','"+employe.getPrenom()+"','"+employe.getDateDeNaissance()+"','"+employe.getEmail()+"','"+employe.getAdresse()+"','"+employe.getPassword()+"','"+employe.getAdmin()+"')";	     
         session.execute(cqlInsert);
       	 return true;//L'employé est ajouté dans la base.
	         	 
	}
	
	public Boolean supprimerEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         CassandraProjetUtils app = new CassandraProjetUtils();
         List<String> projectslisting = employe.getProjects();
         for (int i = 0; i < projectslisting.size(); i++) {
        	  app.deleteEmployeForProject(employe, app.getProjectByName(projectslisting.get(i).split(";")[0]), projectslisting.get(i).split(";")[1]);
		}      
         String cqlwhere = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + employe.getUsername() +"'";
         if(session.execute(cqlwhere).all().get(0).getString(0).equals(employe.getUsername())){
        	 String cqldelete = "DELETE FROM myfirstcassandradb.employees WHERE username IN ('"+ employe.getUsername() +"')";
        	 session.execute(cqldelete);
        	 session.close();
        	 return true;//L'employé a été supprimé de la base de donnée.
         }else{         
        	 session.close();
        	 return false;//L'employé n'a pas été supprimer il est déja présent dans la base
         }
        	
	
	}
	public String drawStringBuilder(Employe employe){
		List<String> projectlist = employe.getProjects();
		String drawstring=employe.getUsername()+";";
		if(!(projectlist.isEmpty())){
			drawstring += "[";
		}
		for (int i = 0; i < projectlist.size()-1; i++) {
			String[] projecttab = projectlist.get(i).split(";");
			String alerte = "onclick=\"alert(\'You clicked me!\')\"";
			String implication = "";
			if(projecttab.length == 5){
				implication = projecttab[4];
			}
			drawstring += "{id: "+i+", content: '"+projecttab[0]+" ("+projecttab[1]+") "+implication+"', start: '"+projecttab[2]+"', end: '"+projecttab[3]+"'},";
		}
		if(!(projectlist.isEmpty())){
			String[] projecttab = projectlist.get(projectlist.size()-1).split(";");
			String implication = "";
			if(projecttab.length == 5){
				implication = projecttab[4];
			}
			drawstring += "{id: "+(projectlist.size()-1)+", content: '"+projecttab[0]+" ("+projecttab[1]+") "+implication+"', start: '"+projecttab[2]+"', end: '"+projecttab[3]+"'}";
			drawstring += "]@";
		}else{
			drawstring += "@";
		}

		return drawstring;
	}
	
	public String drawStringProjectBuilder(Project project){
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		CassandraProjetUtils app2 = new CassandraProjetUtils();
		List<Employe> employelist = app.getEmployeForProject(project);
		int compt = 0;
		String drawstring=project.getName()+";";
		if(!(employelist.isEmpty())){
			drawstring += "[";
		}
		for (int i = 0; i < employelist.size()-1; i++) {
			List<ProjectForEmploye> projectForEmploye = app2.getProjectsForEmployeExtra(employelist.get(i)); 
			for (int j = 0; j < projectForEmploye.size(); j++) {
				if(projectForEmploye.get(j).getName().equals(project.getName())){
				drawstring += "{id: "+compt+", content: '"+employelist.get(i).getPrenom()+" "+employelist.get(i).getNom()+" ("+projectForEmploye.get(j).getRole()+") "+projectForEmploye.get(j).getImplication()+"', start: '"+projectForEmploye.get(j).getDateDebut()+"', end: '"+projectForEmploye.get(j).getDateFin()+"'},";
				compt+=1;
				}
			}
		}
		if(!(employelist.isEmpty())){
			List<ProjectForEmploye> projectForEmploye = app2.getProjectsForEmployeExtra(employelist.get(employelist.size()-1));
			for (int j = 0; j < projectForEmploye.size(); j++) {
				if(projectForEmploye.get(j).getName().equals(project.getName())){
				drawstring += "{id: "+compt+", content: '"+employelist.get(employelist.size()-1).getPrenom()+" "+employelist.get(employelist.size()-1).getNom()+" ("+projectForEmploye.get(j).getRole()+") "+projectForEmploye.get(j).getImplication()+"', start: '"+projectForEmploye.get(j).getDateDebut()+"', end: '"+projectForEmploye.get(j).getDateFin()+"'},";
				compt+=1;
				}
			}
			drawstring += "]@";
		}else{
			drawstring += "@";
		}
		return drawstring;
	}
	public Boolean modifierEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints(SERVER_ADRESS)
                 .build();
         Session session = cluster.connect();
         String cqlwhere = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + employe.getUsername() +"'";
         if(session.execute(cqlwhere).all().get(0).getString(0).equals(employe.getUsername())){
        	 String cqlUpdate = "UPDATE myfirstcassandradb.employees SET nom ='"+employe.getNom()+"', prenom = '"+employe.getPrenom()+"', adresse = '"+employe.getAdresse()+"', password = '"+ employe.getPassword() +"', datedenaissance = '"+employe.getDateDeNaissance()+"', email ='" +employe.getEmail()+"', root ='" + employe.getAdmin() +"' WHERE username ='"+employe.getUsername()+"'";
        	 session.execute(cqlUpdate);
        		return true;//L'employé a été modifié
         }else{    
        	    return false;//L'employé n'a pas été modifié il n'existe pas dans la base 
         }
	}
  public Boolean addProjectForEmploye(Project project, Employe employe, String datedebut, String datefin, String role, String implication){
	  Cluster cluster = Cluster.builder()
              .addContactPoints(SERVER_ADRESS)
              .build();
      Session session = cluster.connect();
      String cqladdprojectforemploye = "UPDATE myfirstcassandradb.employees SET projectslist = projectslist + [{name: '"+project.getName()+"', datedebut: '"+datedebut+"', datefin: '"+datefin+"', role : '"+role+"', implication : '"+implication+"'}] WHERE username = '"+employe.getUsername()+"'";
      session.execute(cqladdprojectforemploye);
	  session.close();
	  return false;
  }
  
  public Boolean projectAlreadyInEmployeList(Project project, Employe employe){
	  Cluster cluster = Cluster.builder()
              .addContactPoints(SERVER_ADRESS)
              .build();
      Session session = cluster.connect();
      
      String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe.getUsername()+"'";
      List<Row> res;
      res = session.execute(cqlStatementselect).all();
      List<com.datastax.driver.core.UDTValue> listprojectres = new ArrayList<com.datastax.driver.core.UDTValue>();      
      listprojectres = res.get(0).getList("projectslist",com.datastax.driver.core.UDTValue.class);
      if(!(listprojectres).isEmpty()){
     	 for (int i = 0; i < listprojectres.size(); i++) {  	
     		 if(listprojectres.get(i).getString("name").equals(project.getName())){
     			 return true;
     		 }
	         
     	 }
      }
	  	session.close();
        cluster.close();       
		return false;
  }
 public List<Employe> getEmployeForProject(Project project){
	 Cluster cluster = Cluster.builder()
             .addContactPoints(SERVER_ADRESS)
             .build();
     Session session = cluster.connect();
     String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
     List<Row> res;
     res = session.execute(cqlStatementselect).all();
     List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
     listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
     List<Employe> employelist = new ArrayList<Employe>();
     if(!(listemployeres).isEmpty()){
    	 for (int i = 0; i < listemployeres.size(); i++) {
    		 	if(!(employeAlreadyInList(employelist, listemployeres.get(i).getString("name")))){
    		 	Employe employe = getEmployeByUsername(listemployeres.get(i).getString("name"));
    			employelist.add(employe);
    			}
    		 }
         
    	 } 
     session.close();
     cluster.close();    
     return employelist;
     }
 public Boolean employeAlreadyInList(List<Employe> employelist, String name){
	 for (int i = 0; i < employelist.size(); i++) {
		 if (!(employelist.isEmpty())){
		if(employelist.get(i).getUsername().equals(name)){
			return true;
		}
	  }
	}
	 return false;
 }
 public Boolean isAProjectManager(Employe employe){
	 CassandraProjetUtils app = new CassandraProjetUtils();
	 List<ProjectForEmploye> projectlist = app.getProjectsForEmploye(employe);
	 if(projectlist.isEmpty()){
		 return false;
	 }else{
		 for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getRole().equals("Project Manager") || projectlist.get(i).getRole().equals("Chef de projet") ){
				return true;
			}
		}
	 }
	 return false;
 }
 public List<ProjectForEmploye> getProjectWhereManager(Employe employe){
	 CassandraProjetUtils app = new CassandraProjetUtils();
	 List<ProjectForEmploye> projectlist = app.getProjectsForEmploye(employe);
	 List<ProjectForEmploye> res = new ArrayList<ProjectForEmploye>();
	 if(projectlist.isEmpty()){
		 return null;
	 }else{
		 for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getRole().equals("Project Manager") || projectlist.get(i).getRole().equals("Chef de projet") ){
				res.add(projectlist.get(i));
			}
		}
	 }
	 return res;
 }
 public List<Employe> getEmployeForEmpProject(ProjectForEmploye project){
	 Cluster cluster = Cluster.builder()
             .addContactPoints(SERVER_ADRESS)
             .build();
     Session session = cluster.connect();
     String cqlStatementselect = "SELECT * FROM myfirstcassandradb.projects WHERE name = '"+project.getName()+"'";
     List<Row> res;
     res = session.execute(cqlStatementselect).all();
     List<com.datastax.driver.core.UDTValue> listemployeres = new ArrayList<com.datastax.driver.core.UDTValue>();
     listemployeres = res.get(0).getList("employelist",com.datastax.driver.core.UDTValue.class);
     List<Employe> employelist = new ArrayList<Employe>();
     if(!(listemployeres).isEmpty()){
    	 for (int i = 0; i < listemployeres.size(); i++) {
    		 	Employe employe = getEmployeByUsername(listemployeres.get(i).getString("name"));
    		 	int already = 0;
    		 	for (int j = 0; j < employelist.size(); j++) {
    		 		if(listemployeres.get(i).getString("name").equals(employelist.get(j).getUsername())){
    		 			already = 1;
    		 		}
				}
    		 	if (already==0){
    		 	employelist.add(employe); 
    	 	}
    		 	}
         
    	 } 
     session.close();
     cluster.close();    
     return employelist;
     }
 public int getEmployeImplication(Employe employe){
	List<String> projectlist =  employe.getProjects();
	int disponibilité = 100;
		if(projectlist != null){
		for (int i = 0; i < projectlist.size(); i++) {
			//disponibilité -= Integer.parseInt(projectlist.get(i).split(";")[4].substring(0,projectlist.get(i).split(";")[4].length()-1 ));
		}}
	 return disponibilité;
 }
}

