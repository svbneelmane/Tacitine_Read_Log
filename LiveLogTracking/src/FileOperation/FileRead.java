/*
 * This file supports to read the number of lines from the text like file
 */
package FileOperation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Shivaprasad Bhat
 */
public class FileRead {
    String filePath;
    List<String> listOfFileLine;
    public FileRead(String filePath)
    {
        this.filePath = filePath;
    }
    
    public List<String> GetLinesOfFile() throws IOException, Exception
    {
        if(isNullOrEmpty(filePath))
            throw new Exception("File path can not be null or empty");
        try
        {
            listOfFileLine = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
        }
        catch(IOException ex)
        {
            throw ex;
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return listOfFileLine;
    }
    
    public static boolean isNullOrEmpty(String content) {
        return !(content != null && !content.isEmpty());
    }
}