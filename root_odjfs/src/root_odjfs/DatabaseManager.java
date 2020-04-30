package root_odjfs;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection connection;                                             // The database connection object.
    private Statement statement;                                               // the database statement object, used to execute SQL commands.

    public DatabaseManager () {               // the constructor for the database manager.
        String url = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";       // our database--username is your O'Reilly login username and is passed in.
        String username = "root";
        String password = "Lkjhg1727#";
        
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");                           // get the driver for this database.
            System.out.println("Driver is set; ready to go!");
        }
        catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return;                                                            // cannot even find the driver--return to caller since cannot do anything.
        }

        try {                                                                     // Establish the database connection, create a statement for execution of SQL commands.
            connection = DriverManager.getConnection (url, username, password );  // username and password are passed into this Constructor.
            statement  = connection.createStatement();                            // statement used to do things in the database (e.g., create the PhoneBook table).
            return;
        
        }
        catch (SQLException exception ) {
            System.out.println ("\n*** SQLException caught ***\n");
            while (exception != null) 
            {                                                                     // grab the exception caught to tell us the problem.
                System.out.println ("SQLState:   " + exception.getSQLState()  );
                System.out.println ("Message:    " + exception.getMessage()   );
                System.out.println ("Error code: " + exception.getErrorCode() );
                exception = exception.getNextException ();
                System.out.println ( "" );
            }
        }
        catch (java.lang.Exception exception) {                                  // perhaps there is an exception that was not SQL related.
            exception.printStackTrace();                                         // shows a trace of the exception error--like we see in the console.
        }
    }
    
    public String getFromTable(String query,String columnName,String columnValue) {
    	try {
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			columnValue = rs.getString(columnName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columnValue;
	}
    
    public void insertIntoTable(String query) {
    	try {
			statement.executeUpdate(query);
			System.out.println("Record was succesfull inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
} 
