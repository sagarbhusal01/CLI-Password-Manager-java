package np.com.Sagarbhusal01.CommandHandler;

import io.github.sagarbhusal01.TableBuilder;
import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.Utility.Utils;

import java.util.HashMap;

class Help {

    protected Help() {
        Utils console=new Utils();
        new Draw().Separator();
        TableBuilder tb=new TableBuilder();
        HashMap<String ,String> HelpData=new HashMap<>();
        HelpData.put("'reconfig sql'","reset the SQl URL, UserName and Password");

        tb.TwoColumnTableWithID(HelpData,"Commands","Description");
        console.log("");
        new Draw().Separator();

    }
}
