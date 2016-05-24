package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Krlitos
 */
public class DBConnection {
    
    
    public Connection Conectar() throws ClassNotFoundException {
        Connection conn = null;
        try{
             
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            /*conn=DriverManager.getConnection("jdbc:sqlserver://Krlitos-pc:1433;databaseName=BASEODA",
                                "sa","39476");*/
            conn=DriverManager.getConnection("jdbc:sqlserver://192.168.1.3:1433;databaseName=ODA","sa","SysSrv11"); 
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
       
        return conn; 
                
    }
    
}
