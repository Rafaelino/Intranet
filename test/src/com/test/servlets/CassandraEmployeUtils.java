package com.test.servlets;

import com.test.utils.*;
import java.util.*;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;

public class CassandraEmployeUtils 
{
	
	public String afficherTable(){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints("127.0.0.1")
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
         return resultat;
         //System.out.println(session.execute(cqlStatement3).toString());
	}
	public List<String> getAllEmployes(){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints("127.0.0.1")
                 .build();
         Session session = cluster.connect();
         String cqlStatement3 = "SELECT * FROM myfirstcassandradb.employees";
         session.execute(cqlStatement3);
         List<Row> res;
         res = session.execute(cqlStatement3).all();
         List<String> resultat = new ArrayList<String>();
         for (int i = 0; i < res.size(); i++) {  	
        	resultat.add(res.get(i).getString(4) + " " +res.get(i).getString(6));			
		}
         return resultat;
	}
	public Boolean ajouterEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints("127.0.0.1")
                 .build();
         Session session = cluster.connect();
         String cqlwhere = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + employe.getUsername() +"'";
         List<Row> resultList = session.execute(cqlwhere).all();
         if(!(resultList.isEmpty())){
	         if(resultList.get(0).getString(0).equals(employe.getUsername())){
	        	 return false;//L'employé n'a pas été ajouté il est déja présent dans la base
	         }}
	        	
         String cqlInsert = "INSERT INTO myfirstcassandradb.employees(username, nom, prenom, datedenaissance, email, adresse, password) VALUES ('"+employe.getUsername()+"','"+employe.getNom()+"','"+employe.getPrenom()+"','"+employe.getDateDeNaissance()+"','"+employe.getEmail()+"','"+employe.getAdresse()+"','"+employe.getPassword()+"')";	     
         session.execute(cqlInsert);
       	 return true;//L'employé est ajouté dans la base.
	         	 
	}
	
	public Boolean supprimerEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints("127.0.0.1")
                 .build();
         Session session = cluster.connect();
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
	
	public Boolean modifierEmploye(Employe employe){
		 Cluster cluster = Cluster.builder()
                 .addContactPoints("127.0.0.1")
                 .build();
         Session session = cluster.connect();
         String cqlwhere = "SELECT * FROM myfirstcassandradb.employees WHERE username = '" + employe.getUsername() +"'";
         if(session.execute(cqlwhere).all().get(0).getString(0).equals(employe.getUsername())){
        	 String cqlUpdate = "UPDATE myfirstcassandradb.employees SET nom ='"+employe.getNom()+"', prenom = '"+employe.getPrenom()+"', adresse = '"+employe.getAdresse()+"', password = '"+ employe.getPassword() +"', datedenaissance = '"+employe.getDateDeNaissance()+"', email ='" +employe.getEmail()+"' WHERE username ='"+employe.getUsername()+"'";
        	System.out.println(cqlUpdate);	
        	 session.execute(cqlUpdate);
        		return true;//L'employé a été modifié
         }else{    
        	    return false;//L'employé n'a pas été modifié il n'existe pas dans la base 
         }
	}
    /*public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder()	
                          .addContactPoints("127.0.0.1")
                          .build();
        Session session = cluster.connect();
        String cqlStatement = "CREATE KEYSPACE myfirstcassandradb WITH " + 
                              "replication = {'class':'SimpleStrategy','replication_factor':1}";        
        session.execute(cqlStatement);

        String cqlStatement2 = "CREATE TABLE myfirstcassandradb.users (" + 
                               " user_name varchar PRIMARY KEY," + 
                               " password varchar " + 
                               ");";
        session.execute(cqlStatement2);
        String cqlStatement3 = "SELECT * FROM myfirstcassandradb.users";
        session.execute(cqlStatement3);
        
        System.out.println(session.execute(cqlStatement3).toString());
        System.exit(0);
    }*/
}