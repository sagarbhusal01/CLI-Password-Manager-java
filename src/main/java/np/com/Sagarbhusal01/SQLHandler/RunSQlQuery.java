package np.com.Sagarbhusal01.SQLHandler;

import np.com.Sagarbhusal01.Drawing.Draw;

import java.sql.Connection;
import java.sql.PreparedStatement;

 class RunSQlQuery {
    protected RunSQlQuery(String SQLCommand) {
        Connection con=new SQL().getConnection();
        PreparedStatement query=null;

        try {
            new Draw().Separator();
            query = con.prepareStatement(SQLCommand);
            query.execute();
            con.close();

            System.out.println("status : successful");
            new Draw().Separator();
        }
        catch (Exception e) {
            System.out.println("Status : unsuccessful   "+e.toString());
            new Draw().Separator();

        }
    }
}
