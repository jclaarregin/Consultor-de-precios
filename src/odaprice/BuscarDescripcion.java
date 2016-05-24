package odaprice;

import db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Krlitos
 */
public class BuscarDescripcion extends javax.swing.JFrame {
    
    DBConnection db=new DBConnection();
    //Principal pri =new Principal();
    
    public String descrip;   
        
    public BuscarDescripcion() {
        initComponents();
        //para que salga 
        this.setLocationRelativeTo(null);
        //Ponemos un icono a la aplicación
        setIconImage (new ImageIcon(getClass().getResource("/imagenes/iso_oda2-01.png")).getImage());
        //para ponerle el nombre de la aplicación
        this.setTitle("Buscando por descripción...");
       
    }

    /*public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buscarText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 0));
        setForeground(new java.awt.Color(255, 255, 51));
        setMinimumSize(new java.awt.Dimension(405, 375));
        setResizable(false);
        setSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(216, 227, 173));
        jPanel1.setMinimumSize(new java.awt.Dimension(410, 350));

        buscarText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarTextKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(buscarText, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarTextKeyReleased
        
        String busqueda=buscarText.getText();
        String sql="select s.DESCRIPCIO,g.precio from dbo.STA11 s, dbo.gva17 g where s.COD_ARTICU=g.COD_ARTICU and s.DESCRIPCIO like '%"+busqueda+"%'"
                + " and g.NRO_DE_LIS=7";
        
        try{
            String [] descripcion={"PRODUCTO","PRECIO"};
                  
            Connection con=db.Conectar();
            
            DefaultTableModel model=new DefaultTableModel(null,descripcion);
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(sql);
            String []fila=new String[2];
            while(rs.next()){
                fila[0]=rs.getString("descripcio");
                fila[1]=rs.getString("precio");
                model.addRow(fila);
            }
           
            jTable1.setModel(model);
            rs.close(); 
            sentencia.close();
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_buscarTextKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        //paso la descripcion al form principal
        Principal.descripLabel.setText(jTable1.getValueAt(row, 0).toString());
        
        //capturo la descripcion para enviarla por correo
        descrip=jTable1.getValueAt(row, 0).toString();
        
        //formateo y paso el precio al form principal
        Float flo=Float.parseFloat(jTable1.getValueAt(row, 1).toString());
        String format=String.format("%.2f",flo); 
        Principal.precioLabel.setText("AR$ "+format);
        //convierto a las diferentes monedas
        Float pG= flo/Principal.cG*1000;
        String pGuarani=String.format("%.2f",pG);
                    
        Float pR=flo/Principal.cR;
        String pReal=String.format("%.2f",pR);
                    
        Float pD=flo/Principal.cD;
        String pDolar=String.format("%.2f",pD);
        
         Principal.precioG.setText("GY$ "+pGuarani);
         Principal.precioR.setText("BR$ "+pReal);
         Principal.precioD.setText("US$ "+pDolar);
               
        //oculto el form de busqueda
        this.setVisible(false);
        
        //envío correo de producto no encontrado
        /*EnviadorDeEmail enviar=new EnviadorDeEmail();
        enviar.setCorreo("carar14187@hotmail.com");
        enviar.setMensaje("El código de barras ' "+pri.getCbarra()+" ' del producto ' "+descrip+" ' no figura en la base de datos de ODA. Chequear");
        enviar.sendEmail();*/
                
    }//GEN-LAST:event_jTable1MouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarDescripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
