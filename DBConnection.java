import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	static Connection con;

	public static Connection getConnection() {

		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:Stochastic");
			/*Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      
		      con = DriverManager.getConnection("jdbc:mysql://localhost/Stochastic","root","");
*/
		} catch (Exception ex) {

			System.out.println(ex);

		}

		return con;

	}

}
