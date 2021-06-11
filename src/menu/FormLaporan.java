package menu;

import java.io.InputStream;
import koneksi.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FormLaporan extends javax.swing.JInternalFrame {

    private Connection conn = Koneksi.getKoneksi();

    public FormLaporan() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        jBarangMasuk = new javax.swing.JButton();
        jSupplier = new javax.swing.JButton();
        jBarangKeluar = new javax.swing.JButton();
        jLaba = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1080, 400));

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.setForeground(new java.awt.Color(0, 0, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FORM LAPORAN");
        jPanel6.add(jLabel2);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Dari :");
        jLabel3.setPreferredSize(new java.awt.Dimension(10, 16));
        jPanel3.add(jLabel3);

        jPanel5.add(jPanel3);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Sampai :");
        jLabel1.setPreferredSize(new java.awt.Dimension(10, 16));
        jPanel1.add(jLabel1);

        jPanel5.add(jPanel1);

        jPanel4.add(jPanel5);

        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jBarangMasuk.setText("LAPORAN BARANG MASUK");
        jBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBarangMasukActionPerformed(evt);
            }
        });

        jSupplier.setText("LAPORAN SUPPLIER");
        jSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSupplierActionPerformed(evt);
            }
        });

        jBarangKeluar.setText("LAPORAN BARANG KELUAR");
        jBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBarangKeluarActionPerformed(evt);
            }
        });

        jLaba.setText("LAPORAN LABA");
        jLaba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jBarangMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLaba, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBarangMasuk)
                    .addComponent(jSupplier)
                    .addComponent(jBarangKeluar)
                    .addComponent(jLaba)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBarangMasukActionPerformed
        barangMasuk();
    }//GEN-LAST:event_jBarangMasukActionPerformed

    private void jSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSupplierActionPerformed
        supplier();
    }//GEN-LAST:event_jSupplierActionPerformed

    private void jBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBarangKeluarActionPerformed
        barangKeluar();
    }//GEN-LAST:event_jBarangKeluarActionPerformed

    private void jLabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabaActionPerformed
        laba();
    }//GEN-LAST:event_jLabaActionPerformed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
        // TODO add your handling code here:
        jDateChooser1.setDateFormatString("dd MMMM yyyy");
        System.out.println(jDateChooser1.getDate());
    }//GEN-LAST:event_jDateChooser1MouseClicked

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormLaporan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBarangKeluar;
    private javax.swing.JButton jBarangMasuk;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JButton jLaba;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jSupplier;
    // End of variables declaration//GEN-END:variables

    private void supplier() {
        try {
            HashMap parameter = new HashMap();
            InputStream file = getClass().getResourceAsStream("/report/report2.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, conn );
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void barangMasuk() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(jDateChooser1.getDate());
        String date2 = sdf.format(jDateChooser2.getDate());
        try {
            HashMap parameter = new HashMap();
            parameter.put("awal", date1);
            parameter.put("akhir", date2);
            System.out.println(date1);
            System.out.println(date2);
            InputStream file = getClass().getResourceAsStream("/report/report3.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, conn );
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void barangKeluar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(jDateChooser1.getDate());
        String date2 = sdf.format(jDateChooser2.getDate());
        try {
            HashMap parameter = new HashMap();
            parameter.put("awal", date1);
            parameter.put("akhir", date2);
            InputStream file = getClass().getResourceAsStream("/report/report4.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, conn );
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

//    private void StockBarang() {
//        String reportSource;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date1 = sdf.format(jDateChooser1.getDate());
//        String date2 = sdf.format(jDateChooser2.getDate());
//        try {
//            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) conn;
//            reportSource = "src/report/report5.jrxml";
//            HashMap parameter = new HashMap();
//            parameter.put("awal", date1);
//            parameter.put("akhir", date2);
//            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (JRException e) {
//            System.out.println(e);
//        }
//    }

    private void laba() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(jDateChooser1.getDate());
        String date2 = sdf.format(jDateChooser2.getDate());
        try {
            HashMap parameter = new HashMap();
            parameter.put("awal", date1);
            parameter.put("akhir", date2);
            InputStream file = getClass().getResourceAsStream("/report/report6.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, conn );
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }
}
