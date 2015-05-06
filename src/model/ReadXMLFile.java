package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class ReadXMLFile {

	private static String arrays[];
	private static Connection conn;

	public static void main(String argv[]) throws FileNotFoundException {


		setupdb();

		File file = new File("C:/Users/mihh.EXF/Downloads/db.txt");
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String tmp = "";
			boolean append = false;
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '"') {
					if (append) {
						append = false;
					}else{
						append = true;
					}
				}
				if (append) {
					tmp = tmp + line.charAt(j + 1);
				}
			} //end of for
			arrays = tmp.split("\"");
			System.out.println(Arrays.toString(arrays));
			db();
		} //end of while
		sc.close();
	} //end of main 

	private static void setupdb() {
		//SETTINGS
		String userName = "root";
		String passWord = "root";
		String dataBase = "airportinvoices";

		//Creating class
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBase, userName, passWord);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void db(){
		try{
			//Do we have connection?
			if (!conn.isClosed()) {
				Statement s = conn.createStatement();
				s.executeUpdate("INSERT INTO aircraftinfo values (null,'"+arrays[0]+"', '"+arrays[1]+"', '"+arrays[2]+"')");
				
				s.close();
			}

		}catch(Exception e){
			System.err.println("Exception: " + e.getMessage());
		}
	}
} //end of class