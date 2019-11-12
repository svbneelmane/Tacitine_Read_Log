/*
 * Logs the application exceptions
 */
package BusinessLogic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Prajwal Bhat
 */
public class LogException {
    
    /**
     * Exception log
     * @param stackTrace
     * @param filePath
     */
    public static void LogApplicationException(String stackTrace, String filePath)
    {
        BufferedWriter writer = null;
        try
        {
            Path path = Paths.get(filePath);
            if(!Files.exists(path))
            {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            
            writer = new BufferedWriter(
                new FileWriter(filePath, true)
            ); 
            writer.newLine();
            writer.write(stackTrace);
            
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            if(writer != null)
            {
                try
                {
                    writer.close();
                }
                catch(IOException ex)
                {
                    
                }
                catch(Exception ex)
                {
                    
                }
            }
        }
    }
}