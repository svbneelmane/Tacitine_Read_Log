/*
 * This file supports to display the file contents into the JFrame using JTable
 */
package livelogtracking;

import BusinessLogic.JFilePicker;
import BusinessLogic.LogException;
import BusinessLogic.ReadLogFile;
import FileOperation.FileRead;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prajwal Bhat
 */
public class LiveLogTracking extends JFrame implements ActionListener 
{
    private final String logErrorPath = "C:\\LiveLogTrackerError\\LiveTracker.log";
    private final int refreshSecond = 6000;//60000;
    
    private JTabbedPane tabbedPane;
    private JFrame liveLogFrame;
    JPanel configurationPanel;
    JPanel configurationActionPanel;
    JFilePicker filePicker;
    JFileChooser fileChooser;
    JButton submit;
    
    private DefaultTableModel client;
    private DefaultTableModel route;
public final static int ONE_SECOND = 5000;
Timer timer;
    /**
     * Constructor
     */
    public LiveLogTracking()
    {
        liveLogFrame = new JFrame("Live Log Tracking");
        submit = new JButton("Display");
        
        
        timer = new Timer(ONE_SECOND, new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
	//...Update the progress bar...
System.out.println("In Repaintr");
        repaint();
    }    
});
timer.start();
    }
    
    /**
     * Show the screen to select the file
     */
    public void ShowFilePathEntryScreen()
    {
        try
        {
            // File Picker
            filePicker = new JFilePicker("Select log file", "Browse...");
            filePicker.setMode(JFilePicker.MODE_SAVE);
            filePicker.addFileTypeFilter(".log", "Logs");
            // Access JFileChooser class directly
            fileChooser = filePicker.getFileChooser();
            fileChooser.setCurrentDirectory(new File("C:/"));        
            // Panel
            configurationPanel = new JPanel(new GridBagLayout());
            configurationActionPanel = new JPanel();
            // Button
            configurationActionPanel.add(submit);  
            submit.addActionListener((ActionListener) this); 
            // JFrame
            liveLogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            liveLogFrame.add(filePicker, BorderLayout.CENTER);
            liveLogFrame.add(configurationActionPanel, BorderLayout.PAGE_END);
            liveLogFrame.pack();
            liveLogFrame.setLocationRelativeTo(null);
            liveLogFrame.setVisible(true);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    /**
     * Show the screen to display the file content in JTable
     * @param filePath 
     */
    private void ShowLogTracking(String choosenFilePath) 
    {
        try
        {            
            tabbedPane = new JTabbedPane();
            // Read File Content
            FileRead fileRead = new FileRead(choosenFilePath);
            // Business Logic on file content
            ReadLogFile readLogFiles = new ReadLogFile(fileRead.GetLinesOfFile());
            // Client Data
            client = new DefaultTableModel(readLogFiles.GetClientRecord(), readLogFiles.GetClientColumnNames()) {
                private static final long serialVersionUID = 1L;

                @Override
                public Class getColumnClass(int column) {
                    return getValueAt(0, column).getClass();
                }
            };
            // Route Data
            route = new DefaultTableModel(readLogFiles.GetRouteRecord(), readLogFiles.GetRouteColumnName()) {
                private static final long serialVersionUID = 1L;

                @Override
                public Class getColumnClass(int column) {
                    return getValueAt(0, column).getClass();
                }
            };
            
            JPanel homePanel = new JPanel();
            JLabel homeLabel = new JLabel("File Logger");
            homeLabel.setHorizontalAlignment(JLabel.CENTER);
            homeLabel.setFont(new Font("Serif", Font.BOLD, 25));

            homePanel.add(homeLabel);
            //homePanel.add( RefreshLogRecord());
//
//            
            tabbedPane.addTab("Home", homePanel);
            // Bind to JFrame
            tabbedPane.addTab("Client", new JScrollPane(new JTable(client)));
            tabbedPane.addTab("Route", new JScrollPane(new JTable(route)));
            tabbedPane.setTabPlacement(JTabbedPane.TOP);
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            liveLogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            liveLogFrame.add(tabbedPane, BorderLayout.CENTER);
            liveLogFrame.pack();
            liveLogFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            liveLogFrame.setLocationRelativeTo(null);
            liveLogFrame.setVisible(true);
            //tabbedPane.setEnabledAt(0, false );
            //tabbedPane.setSelectedIndex(1);
        }
        catch(Exception ex)
        {
            LogException.LogApplicationException(ex.getMessage(), logErrorPath);
        }        
    }
    
    /**
     * Display Action Event
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try{
            // Display Action Event
            String choosenFilePath = filePicker.getSelectedFilePath();
            liveLogFrame.dispose(); 
            liveLogFrame = new JFrame();
            LiveLogTracking logTracking = new LiveLogTracking();
            logTracking.ShowLogTracking(choosenFilePath);
            
            
            
//            javax.swing.Timer t = new javax.swing.Timer(5000, new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              try {
//                  logTracking.ShowLogTracking(choosenFilePath, RefreshLogRecord(choosenFilePath));
//              } catch (Exception ex) {
//                  Logger.getLogger(LiveLogTracking.class.getName()).log(Level.SEVERE, null, ex);
//              }
//              System.out.println("IN Timer");
//          }
//       });
//            t.start();

            
            
//            java.util.Timer t = new java.util.Timer();
//            t.schedule( 
//                    new java.util.TimerTask() {
//                        @Override
//                        public void run() {
//                            LiveLogTracking logTracking = new LiveLogTracking();
//                            logTracking.ShowLogTracking(choosenFilePath);
//                            System.out.println("In Timer");
//                            t.cancel();
//                        }
//                    }, 
//                    5000 
//            );
            
            
            //liveLogFrame.revalidate();
            
            
            //LiveLogTracking logTracking = new LiveLogTracking();
            //logTracking.RefreshLogRecord(filePath);
        }
        catch(HeadlessException ex)
        {
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(LiveLogTracking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//    public ReadLogFile RefreshLogRecord(String choosenFilePath) throws Exception
//    {
//        try {
//            
//            return new ReadLogFile(fileRead.GetLinesOfFile());
//        }
//        catch(Exception ex)
//        {
//            throw ex;
//        }
//    }
    
//    public void RefreshLogRecord(String filePath)
//    {
//        try
//        {
//            new Thread(new Runnable()
//            {
//                public void run()
//                {
//                    try
//                    {
//                        
//                        while (true)
//                        {                            
//                            try
//                            {
////                                liveLogFrame = new JFrame();
////                                liveLogFrame.revalidate();
////                                LiveLogTracking logTracking = new LiveLogTracking();
////                                logTracking.ShowLogTracking(filePath);
//                                Thread.sleep(refreshSecond);
//                                repaint();
//                                
//                                
//                            }
//                            catch(Exception ex)
//                            {
//                                LogException.LogApplicationException(ex.getMessage(), logErrorPath);
//                            }
//                            finally
//                            {
//                                
//                                //liveLogFrame.dispose();
//                                
//                                //liveLogFrame.removeAll(); 
//                            }
//                        }
//                    }
//                    catch(Exception ex)
//                    {
//                        LogException.LogApplicationException(ex.getMessage(), logErrorPath);
//                    }
//                }
//            }).start();
//        }
//        catch(Exception ex)
//        {
//            throw ex;
//        }
//    }
}