package test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
//import java.util.logging.FileHandler.append=true;


/**
 * Created by yesmi on 9/04/2017.
 */
public class Logging {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());

    public void Logging() {
        LOGGER.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("logfile.log",true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.setUseParentHandlers(false);
            //fh.setLevel(Level.ALL);
            LOGGER.addHandler(fh);
            

        }
        catch(IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

        } catch (SecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}

