package np.com.Sagarbhusal01.CommandHandler;

import io.github.sagarbhusal01.TableBuilder;
import np.com.Sagarbhusal01.SQLHandler.SQL;
import np.com.Sagarbhusal01.Security.Security;
import np.com.Sagarbhusal01.Utility.Config;
import np.com.Sagarbhusal01.Utility.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

class SeeAllPassword {
    protected SeeAllPassword() {
        String Query="SELECT * FROM `passmancli`.`passman`";
        ResultSet rs=new SQL().SQLFetchQuery(Query);
        HashMap<String,String> Passwords=new HashMap<>();

        Security sec=new Security();
      while(true)
      {
          try {
              if (!rs.next()) break;
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
          try {
              Passwords.put(

                      sec.DecryptData(rs.getString("AppName")),
                      sec.DecryptData( rs.getString("Password"))
              );
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
      new TableBuilder().TwoColumnTableWithID(Passwords,"Application name","Passwords");
       new Utils().log("");
    }
}
