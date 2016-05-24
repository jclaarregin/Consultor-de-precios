/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odaprice;

import db.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Sistemas3
 */
public class Cotizaciones {
    DBConnection db=new DBConnection();
    DecimalFormat decimales=new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd  HH:mm", Locale.getDefault());
    Statement stmt = null;
    
    ResultSet rsG=null;
    ResultSet rsR=null;
    ResultSet rsD=null;
    ResultSet rsFecha=null;
    
    String Guarani;
    String Real;
    String Dolar;
    String fecha;
    
    public String PrecioGuarani(){
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaG=conn.createStatement();
            //hacemos la consulta
           
            ResultSet rsG=sentenciaG.executeQuery("select top 1 c.cotizacion\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Guarani'\n" +
                "order by c.ID_COTIZACION desc;");
            
            if(rsG.next()){
                //por SI
                Guarani=rsG.getString("cotizacion");
                Float flo=Float.parseFloat(Guarani);
                String Gua=String.format("%.2f",flo); 
                Principal.cotizacionG.setText("$ "+Gua);
            }else{
                //por NO
                System.out.println(rsG);
            }
        //cerramos las conexiones despues de reaizar la consulta   
         
        rsG.close(); 
        sentenciaG.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return Guarani;
    }
    public String PrecioReal(){
        
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaR=conn.createStatement();
            //hacemos la consulta
            //id_moneda=4 corresponde a moneda real
            ResultSet rsR=sentenciaR.executeQuery("select top 1 c.cotizacion\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Real'\n" +
                "order by c.ID_COTIZACION desc;");
            if(rsR.next()){
                //por SI
                Real=rsR.getString("cotizacion");
                Float flo=Float.parseFloat(Real);
                String Real=String.format("%.2f",flo); 
                Principal.cotizacionR.setText("$ "+Real);
                
            }else{
                //por NO
                System.out.println(rsG);
            }
        //cerramos las conexiones despues de reaizar la consulta            
        rsR.close(); 
        sentenciaR.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return Real;
    }
    public String PrecioDolar(){
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaD=conn.createStatement();
            //hacemos la consulta
            ResultSet rsD=sentenciaD.executeQuery("select top 1 c.cotizacion\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Dolar Americano'\n" +
                "order by c.ID_COTIZACION desc;");
            if(rsD.next()){
                //por SI
                Dolar=rsD.getString("cotizacion");
                Float flo=Float.parseFloat(Dolar);
                String Dolar=String.format("%.2f",flo); 
                Principal.cotizacionD.setText("$ "+Dolar);
            }else{
                //por NO
                System.out.println(rsD);
            }
        //cerramos las conexiones despues de reaizar la consulta            
        rsD.close(); 
        sentenciaD.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return Dolar;
    }
    public String FechaUltimaCotizacionGuarani(){
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaF=conn.createStatement();
            //hacemos la consulta
            ResultSet rsFecha=sentenciaF.executeQuery("select top 1 c.FECHA_HORA\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Guarani'\n" +
                "order by c.FECHA_HORA desc;");
            if(rsFecha.next()){
                //por SI
                fecha = rsFecha.getDate(1).toString();
                //fecha = sdf.format(rsFecha.getDate("FECHA_HORA"));
                //System.out.println("rs fecha no viene vacio " +fecha);
            }
        //cerramos las conexiones despues de reaizar la consulta            
        rsFecha.close(); 
        sentenciaF.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return fecha;
    }
    public String FechaUltimaCotizacionReal(){
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaF=conn.createStatement();
            //hacemos la consulta
            ResultSet rsFecha=sentenciaF.executeQuery("select top 1 c.FECHA_HORA\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Real'\n" +
                "order by c.FECHA_HORA desc;");
            if(rsFecha.next()){
                //por SI
                fecha = rsFecha.getDate(1).toString();
                
            }
        //cerramos las conexiones despues de reaizar la consulta            
        rsFecha.close(); 
        sentenciaF.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return fecha;
    }
    public String FechaUltimaCotizacionDolar(){
        try {
            //realizamos la conexión
            Connection conn=db.Conectar();
            Statement sentenciaF=conn.createStatement();
            //hacemos la consulta
            ResultSet rsFecha=sentenciaF.executeQuery("select top 1 c.FECHA_HORA\n" +
                "from MONEDA m, COTIZACION c\n" +
                "where  m.ID_MONEDA=c.ID_MONEDA\n" +
                "and m.DESC_MONEDA='Dolar Americano'\n" +
                "order by c.FECHA_HORA desc;");
            if(rsFecha.next()){
                //por SI
                fecha = rsFecha.getDate(1).toString();
                //fecha = sdf.format(rsFecha.getDate("FECHA_HORA"));
                //System.out.println("rs fecha no viene vacio " +fecha);
            }
        //cerramos las conexiones despues de reaizar la consulta            
        rsFecha.close(); 
        sentenciaF.close();
        conn.close();
       }catch (Exception ex){
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return fecha;
    }
}
