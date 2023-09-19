package np.com.Sagarbhusal01.CommandHandler;

import np.com.Sagarbhusal01.Setup.Setup;

public class Command {
    public Command(String command) {
        switch (command)
        {
            case "reconfig sql"->new Setup().RunSetup();
            case "-help" -> new Help();
            case "exit"->System.exit(0);


            default -> {
                System.out.println("Your Input is invalid!! Here is the list of all commands available in Passman");
                new Help();
            }

        }
    }
}
