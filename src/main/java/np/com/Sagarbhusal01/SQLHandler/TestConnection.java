package np.com.Sagarbhusal01.SQLHandler;

import np.com.Sagarbhusal01.Drawing.Draw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class TestConnection {
    protected Boolean TestSQLConnection(String URL,String UserName,String Password)  {

        // Establishing Connection
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,UserName,Password);
        } catch (SQLException e) {
            System.out.println("Some error occurred while reaching the SQL Connection");
        }

        if (con != null)
        {
            System.out.println("Status: Connected");
            new Draw().Separator();

            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Some error occurred while closing the SQL connection");
            }
            return true;
        }
        else
        {
            System.out.println("status: Not Connected");
            new Draw().Separator();
            return false;
        }
    }
}
