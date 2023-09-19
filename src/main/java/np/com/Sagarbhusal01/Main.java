package np.com.Sagarbhusal01;

import np.com.Sagarbhusal01.CommandHandler.Command;
import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.SQLHandler.SQL;
import np.com.Sagarbhusal01.Setup.Setup;
import np.com.Sagarbhusal01.Utility.Utils;


public class Main {

    public static void main(String[] args) {

     Utils console=new Utils();
     SQL sql=new SQL();
     Setup setup=new Setup();


     new Draw().Logo();
     setup.IsFirstTime();



     while (sql.CheckPassword())
     {
         new Command(console.Input());
     }









    }
}