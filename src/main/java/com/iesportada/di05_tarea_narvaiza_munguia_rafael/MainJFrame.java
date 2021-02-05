
package com.iesportada.di05_tarea_narvaiza_munguia_rafael;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author Rafa Narvaiza
 */
public class MainJFrame extends javax.swing.JFrame {

    private static final String PATH_FROM_ORIGINAL_REPORT ="src\\main\\resources\\report1.jasper";
    private static final String PATH_PDF_REPORT = "src\\main\\resources\\informe.pdf";
    private static final String PATH_HTML_REPORT = "src\\main\\resources\\informe.html";
    private static final int TIME_VISIBLE = 2800;
    private static final String MESSAGE_PDF = "Manténgase a la espera mientras se abre el PDF generado";
    private static final String MESSAGE_HTML = "Manténgase a la espera mientras se abre el HTML generado";
    public MainJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGenerateIreport1 = new java.awt.Button();
        pdfGenerateButton = new java.awt.Button();
        generateHTMLButton = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGenerateIreport1.setActionCommand("vistaDeInforme");
        buttonGenerateIreport1.setLabel("Vista de informe");
        buttonGenerateIreport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateIreport1ActionPerformed(evt);
            }
        });

        pdfGenerateButton.setActionCommand("PDFButton");
        pdfGenerateButton.setLabel("Generar PDF");
        pdfGenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfGenerateButtonActionPerformed(evt);
            }
        });

        generateHTMLButton.setActionCommand("HTMLGenerate");
        generateHTMLButton.setLabel("Generar HTML");
        generateHTMLButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateHTMLButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonGenerateIreport1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(generateHTMLButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pdfGenerateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(buttonGenerateIreport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(pdfGenerateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(generateHTMLButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGenerateIreport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateIreport1ActionPerformed
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(PATH_FROM_ORIGINAL_REPORT);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);   
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonGenerateIreport1ActionPerformed

    private void pdfGenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfGenerateButtonActionPerformed

        JOptionPane pane = new JOptionPane(MESSAGE_PDF, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(rootPane, MESSAGE_PDF);
        dialog.setModal(false);
        dialog.setVisible(true);
        
        new Timer(TIME_VISIBLE, (ActionEvent e) -> {
            dialog.setVisible(false);
        }).start();
        
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(PATH_FROM_ORIGINAL_REPORT);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            JasperExportManager.exportReportToPdfFile(jprint, PATH_PDF_REPORT);
            
        } catch (JRException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            Thread.sleep(3000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            try {
                File pdfFile = new File(PATH_PDF_REPORT);
                Desktop.getDesktop().open(pdfFile);
            } catch (IOException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    
    }//GEN-LAST:event_pdfGenerateButtonActionPerformed

    private void generateHTMLButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateHTMLButtonActionPerformed
        JOptionPane pane = new JOptionPane(MESSAGE_HTML, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(rootPane, MESSAGE_HTML);
        dialog.setModal(false);
        dialog.setVisible(true);
        
        new Timer(TIME_VISIBLE, (ActionEvent e) -> {
            dialog.setVisible(false);
        }).start();
        
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(PATH_FROM_ORIGINAL_REPORT);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            JasperExportManager.exportReportToHtmlFile(jprint, PATH_HTML_REPORT);
            
        } catch (JRException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            try {
                File htmlFile = new File(PATH_HTML_REPORT);
                Desktop.getDesktop().open(htmlFile);
            } catch (IOException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }//GEN-LAST:event_generateHTMLButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button buttonGenerateIreport1;
    private java.awt.Button generateHTMLButton;
    private java.awt.Button pdfGenerateButton;
    // End of variables declaration//GEN-END:variables
}
