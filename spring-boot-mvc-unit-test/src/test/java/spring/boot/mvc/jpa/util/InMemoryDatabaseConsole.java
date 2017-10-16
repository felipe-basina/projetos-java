package spring.boot.mvc.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.Server;

public class InMemoryDatabaseConsole {

	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:springboot");
			
			Server server = Server.createTcpServer().start();
			System.out.println("Server started and connection is open.");
	        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:springboot");
	        
	        System.out.println("Press [Enter] to stop.");
	        System.in.read();

	        System.out.println("Stopping server and closing the connection");
	        server.stop();
	        conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
