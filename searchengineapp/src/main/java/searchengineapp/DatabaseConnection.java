package searchengineapp;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	static Connection connection = null;
    public static Connection getConnection(){
        if (connection!=null ){
            return connection;
        }
        String user = "root";
        String pwd = "aniketraj123";
        String db = "searchengineapp";
		return getConnection(db, user, pwd);// *************
    }

	private static Connection getConnection(String db, String user, String pwd) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");//*********
			System.out.println("driver done");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pwd);
			System.out.println("pased");

        }
		catch (Exception exception) {
			exception.printStackTrace();
        }
        return connection;
    }
}

