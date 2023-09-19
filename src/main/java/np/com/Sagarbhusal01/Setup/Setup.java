package np.com.Sagarbhusal01.Setup;

import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.SQLHandler.SQL;
import np.com.Sagarbhusal01.Security.Security;
import np.com.Sagarbhusal01.Utility.Config;
import np.com.Sagarbhusal01.Utility.Utils;
import np.com.Sagarbhusal01.YAML;

import java.io.File;
import java.util.HashMap;

public class Setup {
    Utils console=new Utils();






    private boolean IfFileExist()
    {
        File file = new File(new Config().getPath());
        return file.exists() || file.isDirectory();
    }






    private void FirstTimeMessage()
    {
        new Draw().Separator();
        console.log("Looks like you are running this program for first time !!!");
        console.log("We have to set few things first before we begin");
        console.log("Simply Answer following questions");
        new Draw().Separator();

    }










    private void AskDetailsAndSaveInSQL()
    {

        String Name=console.Input("UserName");
        String MasterPassword=console.Input("Master Password");



     String CreateDetailsTable="CREATE TABLE `passmancli`.`details` (`ID` INT NOT NULL AUTO_INCREMENT, `Name` VARCHAR(50) NOT NULL , `MasterPassword` VARCHAR(300) NOT NULL , PRIMARY KEY (`ID`));";
     String AddUserNameAndPassword = String.format("INSERT INTO `passmancli`.`details`(Name,MasterPassword) VALUES('%s','%s');",Name,new Security().Hashing(MasterPassword));

     String CreatePassmanTable="CREATE TABLE `passmancli`.`passman` (`AppName` VARCHAR(50) NOT NULL , `Password` VARCHAR(300) NOT NULL , `DateModified` TIMESTAMP NOT NULL , `ID` INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (`ID`(4)));";

        new Draw().Separator();
        new SQL().CreateDatabase();
        new SQL().RunSQLQuery(CreateDetailsTable);
        new SQL().RunSQLQuery(AddUserNameAndPassword);
        new SQL().RunSQLQuery(CreatePassmanTable);
        new Draw().Separator();
    }










    public void RunSetup()
    {
        HashMap<String,String> data=new HashMap<>();

         data.put("URL",console.Input("SQL Database URL"));
         data.put("UserName",console.Input("SQL Database Username"));
         data.put("Password",console.Input("SQL Database Password"));

        new Draw().Separator();

        console.log("The provided connection must be tested before starting the program");

        if(
        new SQL().TestConnection(data.get("URL"),data.get("UserName"),data.get("Password"))
        )
        {
            console.DestroyFileContent();
            YAML yaml=new YAML();
            yaml.setMode("normal");
            yaml.Writer(data,new Config().getPath());
        }
        else
        {
            console.log("Make sure that your SQL server is running!");
            console.log("Make sure of your credentials and try again!");
            RunSetup();
        }
        new Draw().Separator();
    }
















    public void IsFirstTime()
    {
        YAML yaml=new YAML();
        yaml.setMode("normal");
        HashMap<String,String> ReadData=new HashMap<>();

        if(IfFileExist())
        {
            File f=new File(new Config().getPath());
            if(f.length()<=30) RunSetup();

            ReadData=yaml.Reader(new Config().getPath());


            new Draw().Separator();

            System.out.println("Sql Connection ");
            if(
            !new SQL().TestConnection(ReadData.get("URL"),ReadData.get("UserName"),ReadData.get("Password"))
            )
            {
                new Draw().Separator();
               console.log("Make sure your SQL server is started! If you think there is any problem ");
               console.log("type 'reconfig sql' or 'exit' and restart ");
                new Draw().Separator();
                switch (console.Input())
                {
                    case "reconfig sql"-> RunSetup();
                    case "exit"->System.exit(0);
                }
            }
        }
        else
        {
            FirstTimeMessage();
            RunSetup();
            AskDetailsAndSaveInSQL();
        }
    }















}
