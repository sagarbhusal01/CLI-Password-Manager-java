package np.com.Sagarbhusal01.Utility;

import np.com.Sagarbhusal01.Drawing.Draw;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Utils {



    public void log(String message)
    {
        System.out.println(message);
    }
    public String Input()
    {
        System.out.print(">> ");
        return new Scanner(System.in).nextLine();
    }

    public String Input(String message)
    {
        System.out.print(message+" >> ");
        return new Scanner(System.in).nextLine();
    }

    public void DestroyFileContent()
    {
        try {
            FileChannel.open(Paths.get(new Config().getPath()), StandardOpenOption.WRITE).truncate(0).close();

        }
        catch (IOException e)
        {
            log("Could not destroy the file content");
            new Draw().Separator();
        }
    }


}
