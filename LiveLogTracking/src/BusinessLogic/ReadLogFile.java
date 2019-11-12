/*
 * Provides Business Logic on the read file content
 */
package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shivaprasad Bhat
 */
public class ReadLogFile {
    List<String> fileLines;
    int clientRecordStartFrom;
    int clientRecordEndTill;
    int routeStartFrom;
    int routeEndTill;
    public ReadLogFile(List<String> fileLines)
    {
        this.fileLines = fileLines;
        clientRecordStartFrom = fileLines.indexOf("Common Name,Real Address,Bytes Received,Bytes Sent,Connected Since");
        clientRecordEndTill = fileLines.indexOf("ROUTING TABLE");
        routeStartFrom = clientRecordEndTill;
        routeEndTill = fileLines.indexOf("GLOBAL STATS");
    }
    
    public String[] GetClientColumnNames()
    {
        try
        {
            return fileLines.get(clientRecordStartFrom).split(",");
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public String[][] GetClientRecord()
    {
        List<String> listOfClient = new ArrayList<>();
        String[][] clientRecord;
        try
        {
            for(int iCount = (clientRecordStartFrom+2); iCount < clientRecordEndTill; iCount++)
            {
                listOfClient.add(fileLines.get(iCount));
            }

            clientRecord = new String[listOfClient.size()][GetClientColumnNames().length];
            for(int count = 0; count < listOfClient.size(); count++)
            {
                clientRecord[count] = listOfClient.get(count).split(",");
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return clientRecord;
    }
    
    public String[] GetRouteColumnName()
    {
        try
        {
            return fileLines.get(routeStartFrom+1).split(",");
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public String[][] GetRouteRecord()
    {
        List<String> listOfRoute = new ArrayList<>();
        String[][] routeRecord;
        try
        {
            for(int iCount = (routeStartFrom + 2); iCount < routeEndTill; iCount++)
            {
                listOfRoute.add(fileLines.get(iCount));
            }
            
            routeRecord = new String[listOfRoute.size()][GetRouteColumnName().length];
            for(int count = 0; count < listOfRoute.size(); count++)
            {
                routeRecord[count] = listOfRoute.get(count).split(",");
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return routeRecord;
    }
}