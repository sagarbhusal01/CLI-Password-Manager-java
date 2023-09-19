package np.com.Sagarbhusal01.SQLHandler;

import np.com.Sagarbhusal01.CommandHandler.Command;
import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.Security.Security;
import np.com.Sagarbhusal01.Setup.Setup;
import np.com.Sagarbhusal01.Utility.Config;
import np.com.Sagarbhusal01.Utility.Utils;
import np.com.Sagarbhusal01.YAML;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

public class SQL {


Utils console=new Utils();

    public boolean TestConnection(String URL, String UserName, String Password)
    {
        return new TestConnection().TestSQLConnection(URL,UserName,Password);
    }




    public void RunSQLQuery(String SQLCommand)
    {
        new RunSQlQuery(SQLCommand);
    }



    public Connection getConnection()
    {
        YAML yaml=new YAML();
        yaml.setMode("normal");
        HashMap<String,String> Credentials=new HashMap<>(
                yaml.Reader(new Config().getPath())
        );

        try {
            return DriverManager.getConnection(Credentials.get("URL"),
                    Credentials.get("UserName"),
                    Credentials.get("Password")
            );

        } catch (SQLException e) {
            System.out.println("Some error occurred while reaching the SQL Connection" +
                    "Try again after restarting or " +
                    "To change the server credential type:-   reconfigure database");
            new Draw().Separator();
            return null;
        }
    }



    public  void CreateDatabase()
    {
        RunSQLQuery("CREATE DATABASE `passmancli`;");
    }



    public ResultSet SQLfetchQuery(String Query)
    {
        try {
             return getConnection().prepareStatement(Query).executeQuery();
        }

        // Catch block to handle exception
        catch (SQLException e) {
            System.out.println("Error occurred while fetching the data "+e.toString());
            new Draw().Separator();
            return null;
        }
    }


public boolean CheckPassword()
{
    String MasterPassword=console.Input("Master Password");
    if(Objects.equals(MasterPassword,"reconfigure sql"))
    {
        new Setup().RunSetup();
    }

    String HashedMasterPassword="";
    String Name="";


    String fetchPasswordQuery="SELECT * FROM`passmancli`.`details` WHERE ID=(SELECT MAX(ID) FROM `passmancli`.`details`);";
    ResultSet rs=SQLfetchQuery(fetchPasswordQuery);


    while(true)
    {
        try {
            if (!rs.next()) break;
            HashedMasterPassword=rs.getString("MasterPassword");
            Name=rs.getString("Name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        if(Objects.equals(HashedMasterPassword, new Security().Hashing(MasterPassword)))
        {
            new Draw().Separator();
            System.out.println("Welcome "+Name+" !!");
            new Draw().Separator();
          return true;
        }
        else {
            console.log("Your Password is incorrect!! try again after restarting the project!! ");
            System.exit(0);
            return false;
        }

}











}


