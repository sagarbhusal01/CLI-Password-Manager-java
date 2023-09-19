package np.com.Sagarbhusal01.CommandHandler;

import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.Main;
import np.com.Sagarbhusal01.SQLHandler.SQL;
import np.com.Sagarbhusal01.Security.Security;
import np.com.Sagarbhusal01.Utility.Utils;

public class AddPassword {
    public AddPassword() {
        SQL sql=new SQL();
        Utils console=new Utils();
        Security sec=new Security();
        String AddPasswordQuery=
                String.format
                        (
                        "INSERT INTO `passmancli`.`passman` (`AppName`, `Password`, `DateModified`, `ID`) VALUES ('%s', '%s', current_timestamp(), NULL)",
                        sec.EncryptData(console.Input("Application name")),
                        sec.EncryptData(console.Input("Application password"))
                        );
        new Draw().Separator();
        sql.RunSQLQuery(AddPasswordQuery);

    }
}
