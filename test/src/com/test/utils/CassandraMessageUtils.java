package com.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.test.beans.Employe;

public class CassandraMessageUtils {

	public void sendMessage(Employe sender, Employe receiver, String message){
		 Cluster cluster = Cluster.builder()
	              .addContactPoints("127.0.0.1")
	              .build();
	      Session session = cluster.connect();
	      String cqladdmessagesender = "UPDATE myfirstcassandradb.employees SET rs_messages = rs_messages + [{sender: '"+sender.getUsername()+"', receiver: '"+receiver.getUsername()+"', corpus: '"+message.replace("'", "''")+"'}] WHERE username = '"+sender.getUsername()+"'";
	      String cqladdmessagereceiver = "UPDATE myfirstcassandradb.employees SET rs_messages = rs_messages + [{sender: '"+sender.getUsername()+"', receiver: '"+receiver.getUsername()+"', corpus: '"+message.replace("'", "''")+"'}] WHERE username = '"+receiver.getUsername()+"'";
	  
	      session.execute(cqladdmessagesender);
	      session.execute(cqladdmessagereceiver);
		  session.close();
	}
	@SuppressWarnings("null")
	public List<String> getMessageList(Employe employe1, Employe employe2){
		 Cluster cluster = Cluster.builder()
	              .addContactPoints("127.0.0.1")
	              .build();
	    Session session = cluster.connect();
		List<String> messageList = new ArrayList<String>();
		 String cqlStatementselect = "SELECT * FROM myfirstcassandradb.employees WHERE username = '"+employe1.getUsername()+"'";
	      List<Row> res;
	      res = session.execute(cqlStatementselect).all();
	      
	      List<com.datastax.driver.core.UDTValue> listmessages = new ArrayList<com.datastax.driver.core.UDTValue>();      
	      listmessages = res.get(0).getList("rs_messages",com.datastax.driver.core.UDTValue.class);
	      if(!(listmessages).isEmpty()){
	     	 for (int i = 0; i < listmessages.size(); i++) {  	
	     		 if(listmessages.get(i).getString("sender").equals(employe2.getUsername()) || listmessages.get(i).getString("receiver").equals(employe2.getUsername()) ){
	     			 messageList.add(listmessages.get(i).getString("sender")+";"+listmessages.get(i).getString("corpus"));
	     		 }		         
	     	 }
	      }
		  	session.close();
	        cluster.close();       
		return messageList;
	}
}
