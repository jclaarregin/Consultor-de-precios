/*
 * Segunda versión del consultador de Precios ODA
 *desarrollado por el dpto de Sistemas del Grupo PARBRAS S. A.
 */
package odaprice;

import db.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.Toolkit; 
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
 
/**
 *
 * @author Krlitos
 */
public class Principal extends javax.swing.JFrame implements ActionListener {
        
    Cotizaciones coti= new Cotizaciones();
    DBConnection db=new DBConnection();
    
    Statement stmt = null;
    ResultSet rs=null;
    
    
    public static Float cG=null;
    public static Float cR=null;
    public static Float cD=null;
    
    public String cbarra="";
        
    public Principal() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        //Ponemos un icono a la aplicación
        setIconImage (new ImageIcon(getClass().getResource("/imagenes/iso_oda2-01.png")).getImage());
        //para ponerle el nombre de la aplicación
        this.setTitle("Consultor de Precios");
        //cargo las diferentes cotizaciones
        coti.PrecioGuarani();
        coti.PrecioReal();
        coti.PrecioDolar();
        
        cG=Float.parseFloat(coti.PrecioGuarani());
        cR=Float.parseFloat(coti.PrecioReal());
        cD=Float.parseFloat(coti.PrecioDolar());
        
        codigoText.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        
            try {

                //realizamos la conexión
                Connection conn=db.Conectar();
                Statement sentencia=conn.createStatement();

                //hacemos la consulta
                ResultSet rs=sentencia.executeQuery("select top 1 s.descripcio,g.precio\n" +
                    "from dbo.STA11 s,dbo.GVA17 g where s.COD_ARTICU=g.COD_ARTICU \n" +
                    "and s.COD_BARRA='"+codigoText.getText()+"' and g.NRO_DE_LIS=7");

                         
                descripLabel.setText("");
                alertLabel.setText("");

                //pregunto si el codigoText esta vacío
                if(codigoText.getText().equals("")){
                    //por SI
                    descripLabel.setText("no se ha elegido un producto..");
                    precioLabel.setText("");
                }else{
                    //por NO
                    //para saber existe el producto
                    cbarra=codigoText.getText();
                    if(rs.next()){
                        //por SI

                        //trunco los decimales del precio recuperado   
                        Float flo=Float.parseFloat(rs.getString("precio"));
                        String format=String.format("%.2f",flo); 
                        //convierto a las diferentes monedas
                        Float pG= flo/cG*1000;
                        String pGuarani=String.format("%.2f",pG);

                        Float pR=flo/cR;
                        String pReal=String.format("%.2f",pR);

                        Float pD=flo/cD;
                        String pDolar=String.format("%.2f",pD);

                        descripLabel.setText(rs.getString("descripcio"));
                        precioLabel.setText("$ "+format);
                        precioG.setText("GY$ "+pGuarani);
                        precioR.setText("BR$ "+pReal);
                        precioD.setText("US$ "+pDolar);

                    }else{
                        //por NO

                        //Propiedad del sistema para salto de línea:
                        String nl = System.getProperty("line.separator");

                        JOptionPane.showMessageDialog(null, "CODIGO DE BARRAS DESCONOCIDO" + 
                                nl + "buscaremos por descripción...","Ups!",JOptionPane.INFORMATION_MESSAGE);


                        //pri.setCbarra(codigoText.getText());
                        BuscarDescripcion bdesc=new BuscarDescripcion();
                        bdesc.setVisible(true);

                    }
                }

                codigoText.setText("");
                //cerramos las conexiones despues de reaizar la consulta            
                rs.close(); 
                sentencia.close();
                conn.close();

            }catch (Exception ex){
                //JOptionPane.showMessageDialog(null, ex);
                ex.printStackTrace();
            }
    }
                           
});
        
}
   

   /* public static String getCbarra() {
        return cbarra;
    }

    public static void setCbarra(String cbarra) {
        Principal.cbarra = cbarra;
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        codigoText = new javax.swing.JTextField();
        descripLabel = new javax.swing.JLabel();
        precioLabel = new javax.swing.JLabel();
        consulteLabel = new javax.swing.JLabel();
        alertLabel = new javax.swing.JLabel();
        imgGuaraní = new javax.swing.JLabel();
        imgReal = new javax.swing.JLabel();
        imgDolar = new javax.swing.JLabel();
        imgArg = new javax.swing.JLabel();
        precioG = new javax.swing.JLabel();
        precioR = new javax.swing.JLabel();
        precioD = new javax.swing.JLabel();
        cotizacionG = new javax.swing.JLabel();
        cotizacionR = new javax.swing.JLabel();
        cotizacionD = new javax.swing.JLabel();
        imgFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuCorreo = new javax.swing.JMenuItem();
        itemInformacion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("ISO Parbras-01.png"));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 615));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 300));
        getContentPane().setLayout(null);

        panelPrincipal.setMaximumSize(new java.awt.Dimension(900, 700));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(900, 700));
        panelPrincipal.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                panelPrincipalComponentAdded(evt);
            }
        });
        panelPrincipal.setLayout(null);

        codigoText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codigoText.setToolTipText("");
        codigoText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoTextKeyTyped(evt);
            }
        });
        panelPrincipal.add(codigoText);
        codigoText.setBounds(40, 210, 170, 30);

        descripLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        descripLabel.setForeground(new java.awt.Color(255, 255, 255));
        panelPrincipal.add(descripLabel);
        descripLabel.setBounds(330, 230, 390, 40);

        precioLabel.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        precioLabel.setForeground(new java.awt.Color(255, 255, 255));
        panelPrincipal.add(precioLabel);
        precioLabel.setBounds(360, 260, 390, 120);

        consulteLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        consulteLabel.setForeground(new java.awt.Color(255, 255, 255));
        consulteLabel.setText("Consulte los precios aqui");
        panelPrincipal.add(consulteLabel);
        consulteLabel.setBounds(40, 150, 300, 29);

        alertLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        alertLabel.setForeground(new java.awt.Color(255, 255, 0));
        panelPrincipal.add(alertLabel);
        alertLabel.setBounds(50, 250, 150, 30);

        imgGuaraní.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/py.png"))); // NOI18N
        panelPrincipal.add(imgGuaraní);
        imgGuaraní.setBounds(380, 390, 30, 20);

        imgReal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/br.png"))); // NOI18N
        panelPrincipal.add(imgReal);
        imgReal.setBounds(380, 440, 30, 20);

        imgDolar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/us.png"))); // NOI18N
        panelPrincipal.add(imgDolar);
        imgDolar.setBounds(380, 490, 30, 20);

        imgArg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ar.png"))); // NOI18N
        panelPrincipal.add(imgArg);
        imgArg.setBounds(290, 320, 40, 20);

        precioG.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        precioG.setForeground(new java.awt.Color(255, 255, 255));
        panelPrincipal.add(precioG);
        precioG.setBounds(440, 380, 310, 30);

        precioR.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        precioR.setForeground(new java.awt.Color(255, 255, 255));
        panelPrincipal.add(precioR);
        precioR.setBounds(440, 430, 270, 30);

        precioD.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        precioD.setForeground(new java.awt.Color(255, 255, 255));
        panelPrincipal.add(precioD);
        precioD.setBounds(440, 480, 280, 30);

        cotizacionG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cotizacionG.setForeground(new java.awt.Color(255, 255, 0));
        cotizacionG.setText("$ 393.31");
        panelPrincipal.add(cotizacionG);
        cotizacionG.setBounds(300, 380, 70, 30);

        cotizacionR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cotizacionR.setForeground(new java.awt.Color(255, 255, 0));
        cotizacionR.setText("$ 3.63");
        panelPrincipal.add(cotizacionR);
        cotizacionR.setBounds(300, 430, 60, 30);

        cotizacionD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cotizacionD.setForeground(new java.awt.Color(255, 255, 0));
        cotizacionD.setText("$ 14.10");
        panelPrincipal.add(cotizacionD);
        cotizacionD.setBounds(300, 490, 60, 17);

        imgFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bg_consulta_precios_ODA-01.jpg"))); // NOI18N
        imgFondo.setMaximumSize(new java.awt.Dimension(900, 600));
        panelPrincipal.add(imgFondo);
        imgFondo.setBounds(0, -40, 870, 690);

        getContentPane().add(panelPrincipal);
        panelPrincipal.setBounds(0, -10, 790, 640);

        jMenu2.setText("Opciones");

        menuCorreo.setText("Correo");
        menuCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCorreoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuCorreoMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuCorreoMousePressed(evt);
            }
        });
        jMenu2.add(menuCorreo);

        itemInformacion.setText("Información");
        itemInformacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemInformacionMousePressed(evt);
            }
        });
        jMenu2.add(itemInformacion);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//para evitar que se introduzcan letras
    private void codigoTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTextKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            precioLabel.setText("");
            descripLabel.setText("");
            alertLabel.setText("¡ingresa solo números!");
        }
    }//GEN-LAST:event_codigoTextKeyTyped

    private void panelPrincipalComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_panelPrincipalComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrincipalComponentAdded

    private void menuCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCorreoMouseClicked
        
        
    }//GEN-LAST:event_menuCorreoMouseClicked

    private void menuCorreoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCorreoMouseEntered
       
        
    }//GEN-LAST:event_menuCorreoMouseEntered

    private void menuCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCorreoMousePressed
        Autorizacion auto=new Autorizacion();
        auto.setVisible(true);
        
    }//GEN-LAST:event_menuCorreoMousePressed

    private void itemInformacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemInformacionMousePressed
        String fechag=coti.FechaUltimaCotizacionGuarani();
        String fechar=coti.FechaUltimaCotizacionReal();
        String fechad=coti.FechaUltimaCotizacionDolar();
        String nl = System.getProperty("line.separator");
        JOptionPane.showMessageDialog(null, "Fecha de actualización de las monedas:"
                +nl+" Guarani, el "+fechag
                +nl+" Real, el "+fechar
                +nl+" Dolar Americano, el "+fechad,"Cotizaciones",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemInformacionMousePressed
     
    public static void main(String args[]) {
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
                     
                     
            }
        });
        
       
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertLabel;
    private javax.swing.JTextField codigoText;
    private javax.swing.JLabel consulteLabel;
    public static javax.swing.JLabel cotizacionD;
    public static javax.swing.JLabel cotizacionG;
    public static javax.swing.JLabel cotizacionR;
    public static javax.swing.JLabel descripLabel;
    private javax.swing.JLabel imgArg;
    private javax.swing.JLabel imgDolar;
    private javax.swing.JLabel imgFondo;
    private javax.swing.JLabel imgGuaraní;
    private javax.swing.JLabel imgReal;
    private javax.swing.JMenuItem itemInformacion;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuCorreo;
    private javax.swing.JPanel panelPrincipal;
    public static javax.swing.JLabel precioD;
    public static javax.swing.JLabel precioG;
    public static javax.swing.JLabel precioLabel;
    public static javax.swing.JLabel precioR;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
