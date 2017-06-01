package com.test.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.test.beans.Employe;

public class DummyDB {
public DummyDB() {
System.out.println("Dummy DB Constructor");

}

public List<String> getData(String query) throws SQLException {

	/*
//conn = ConnectDatabase.getConnection();
//stmt = conn.createStatement();
String country = null;
query = query.toLowerCase();
String sqlqueary="SELECT * FROM states where name like ‘"+query+"%'";
System.out.println("InstantSearch.doGet ==> sqlstmt is " + sqlqueary);
ResultSet rs = stmt.executeQuery(sqlqueary);
while (rs.next())
{

country = rs.getString(1);
matched.add(country);
}*/
	 Cluster cluster = Cluster.builder()
             .addContactPoints("127.0.0.1")
             .build();
     Session session = cluster.connect();
     String cqlStatement3 = "SELECT * FROM myfirstcassandradb.employees";
     session.execute(cqlStatement3);
     List<Row> res;
     res = session.execute(cqlStatement3).all();
     Pattern pattern;
     
     List<String> resultat = new ArrayList<String>();
     for (int i = 0; i < res.size(); i++) {  	
    	    pattern = Pattern.compile(query);
    	   if(pattern.matcher(res.get(i).getString(4).toLowerCase()+" "+res.get(i).getString(6).toLowerCase()).find()){
     	resultat.add(res.get(i).getString(4)+" "+res.get(i).getString(6));}
		}
     cluster.close();

	    System.out.println("/////"+query);
	  
    


return resultat;
}
}