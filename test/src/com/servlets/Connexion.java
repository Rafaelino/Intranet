package com.servlets;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Employe;
import com.beans.IdTokenVerifierAndParser;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.utils.CassandraEmployeUtils;
/**
 * Servlet that handles the Google Sign In 
 * 
 * 
 */
public class Connexion extends HttpServlet{
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{	
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //response.setContentType("text/html");
			System.out.println("Trying to connect");
		 	System.out.println(request.getHeader("data").toString());
		 	//System.out.println(request.getParameter("data").toString());
	        /*try {
	            String idToken = req.getParameter("id_token");
	            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
	            String name = (String) payLoad.get("name");
	            String email = payLoad.getEmail();
	            System.out.println("User name: " + name);
	            System.out.println("User email: " + email);

	            HttpSession session = req.getSession(true);
	            session.setAttribute("userName", name);
	            req.getServletContext()
	               .getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(req, resp);

	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }*/
		 	if (request.getHeader("X-Requested-With") == null) {
		 		  // Without the `X-Requested-With` header, this request could be forged. Aborts.
		 		}

		 		// Set path to the Web application client_secret_*.json file you downloaded from the
		 		// Google API Console: https://console.developers.google.com/apis/credentials
		 		// You can also find your Web application client ID and client secret from the
		 		// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
		 		// object.
		 		String CLIENT_SECRET_FILE = "/client_secret.json";

		 		// Exchange auth code for access token
		 		

		 		//GoogleClientSecrets clientSecrets =
		 		 //   GoogleClientSecrets.load(
		 		 //       JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		 		System.out.println("here");
		 		GoogleTokenResponse tokenResponse =
		 		          new GoogleAuthorizationCodeTokenRequest(
		 		              new NetHttpTransport(),
		 		              JacksonFactory.getDefaultInstance(),
		 		              "https://www.googleapis.com/oauth2/v4/token",
		 		             "812436705620-ksatoad7ksl5s5qlqldvpf1kqs1hsrt2.apps.googleusercontent.com",
		 		            "gTbBqyZI8lIr36jgnm3WmAT2",
		 		              request.getHeader("data").toString(),
		 		              "postmessage")  // Specify the same redirect URI that you use with your web
		 		                             // app. If you don't have a web version of your app, you can
		 		                             // specify an empty string.
		 		              .execute();
		 		System.out.println("here");

		 		String accessToken = tokenResponse.getAccessToken();

		 		// Use access token to call API
		 		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		 		Drive drive =
		 		    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
		 		        .setApplicationName("Auth Code Exchange Demo")
		 		        .build();
		 		//File file = drive.files().get("appfolder").execute();

		 		// Get profile info from ID token
		 		GoogleIdToken idToken = tokenResponse.parseIdToken();
		 		GoogleIdToken.Payload payload = idToken.getPayload();
		 		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		 		String email = payload.getEmail();
		 		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		 		String name = (String) payload.get("name");
		 		
		 		URL pictureUrl = new URL((String) payload.get("picture"));
		 		InputStream in = new BufferedInputStream(pictureUrl.openStream());
		 		ByteArrayOutputStream out = new ByteArrayOutputStream();
		 		byte[] buf = new byte[1024];
		 		int n = 0;
		 		while (-1!=(n=in.read(buf)))
		 		{
		 		   out.write(buf, 0, n);
		 		}
		 		out.close();
		 		in.close();
		 		byte[] finalpic = out.toByteArray();
		 		FileOutputStream fos = new FileOutputStream("profilepicture"+email.split("@")[0]+".jpg");
		 		fos.write(finalpic);
		 		fos.close();
		 		
		 		String locale = (String) payload.get("locale");
		 		String familyName = (String) payload.get("family_name");
		 		String givenName = (String) payload.get("given_name");
		 		System.out.println(name);
		 		  CassandraEmployeUtils app = new CassandraEmployeUtils();
		 		 HttpSession session = request.getSession(true);
		 		 if(app.isEmployeeInDatabase(email)){
		            session.setAttribute("name", name);
		            session.setAttribute("email", email);
		            session.setAttribute("admin", app.getEmployeByUsername(email.split("@")[0]).getAdmin());
		         request.setAttribute("name", name);
		         request.setAttribute("email", email );
		    
		         request.setAttribute("admin", app.getEmployeByUsername(email.split("@")[0]).getAdmin());}
		 		 else{
		 			 if(email.split("@")[1].equals("everbe.com")){
		 			 Employe newemployee = new Employe(email,name.split(" ")[1],name.split(" ")[0]);
		 			 app.ajouterEmploye(newemployee);
		 			 session.setAttribute("name", name);
			            session.setAttribute("email", email);
			            session.setAttribute("admin", app.getEmployeByUsername(email.split("@")[0]).getAdmin());
			         request.setAttribute("name", name);
			         request.setAttribute("email", email );
			    
			         request.setAttribute("admin", app.getEmployeByUsername(email.split("@")[0]).getAdmin());
		 			 }
		 		 }
		       
	    }
	
}
