package menu;

import barang.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kategori.*;
import pelanggan.*;
import satuan.*;
import supplier.*;
import javax.swing.JOptionPane;
import koneksi.Koneksi;

public class FormMenu extends javax.swing.JFrame {

    private Connection connection = Koneksi.getKoneksi();
    public String role;

    public FormMenu() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        System.out.println(role);
//        buttonBarang.setEnabled(false);
//        buttonBeliBarang.setEnabled(false);
//        buttonJualBarang.setEnabled(false);
//        buttonKategori.setEnabled(false);
//        buttonLaporan.setEnabled(false);
//        buttonPelanggan.setEnabled(false);
//        buttonSatuan.setEnabled(false);
//        buttonSupplier.setEnabled(false);
    }

    public FormMenu(String role) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        System.out.println(role);
        buttonBarang.setEnabled(false);
        buttonBeliBarang.setEnabled(false);
        buttonJualBarang.setEnabled(false);
        buttonStockBarang.setEnabled(false);
        buttonKategori.setEnabled(false);
        buttonLaporan.setEnabled(false);
        buttonPelanggan.setEnabled(false);
        buttonSatuan.setEnabled(false);
        buttonSupplier.setEnabled(false);
        this.role = role;
        role();
    }

    private void role() {
        if (role.equals("Admin")) {
            buttonBarang.setEnabled(true);
            buttonBeliBarang.setEnabled(true);
            buttonJualBarang.setEnabled(true);
            buttonStockBarang.setEnabled(true);
            buttonKategori.setEnabled(true);
            buttonLaporan.setEnabled(true);
            buttonPelanggan.setEnabled(true);
            buttonSatuan.setEnabled(true);
            buttonSupplier.setEnabled(true);
        } else if (role.equals("Divisi Gudang")) {
            buttonBarang.setEnabled(true);
            buttonBeliBarang.setEnabled(true);
            buttonStockBarang.setEnabled(true);
        } else if (role.equals("Manager")) {
            buttonLaporan.setEnabled(true);
        } else{
            buttonLaporan.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanelButton = new javax.swing.JPanel();
        buttonPelanggan = new javax.swing.JButton();
        buttonSatuan = new javax.swing.JButton();
        buttonSupplier = new javax.swing.JButton();
        buttonKategori = new javax.swing.JButton();
        buttonBarang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        buttonBeliBarang = new javax.swing.JButton();
        buttonJualBarang = new javax.swing.JButton();
        buttonStockBarang = new javax.swing.JButton();
        buttonLaporan = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanelFooter = new javax.swing.JPanel();
        labelFooter = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(1080, 501));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 400));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelHeader.setBackground(new java.awt.Color(255, 255, 0));
        jPanelHeader.setLayout(new java.awt.GridLayout(1, 0));

        labelHeader.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelHeader.setForeground(new java.awt.Color(0, 0, 255));
        labelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeader.setText("PT MULIA JAYA TEXTILE ");
        jPanelHeader.add(labelHeader);

        jPanel1.add(jPanelHeader);

        jPanelButton.setBackground(new java.awt.Color(0, 0, 0));
        jPanelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelButton.setLayout(new java.awt.GridLayout(1, 0));

        buttonPelanggan.setText("Customer");
        buttonPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPelangganActionPerformed(evt);
            }
        });
        jPanelButton.add(buttonPelanggan);

        buttonSatuan.setText("Satuan");
        buttonSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSatuanActionPerformed(evt);
            }
        });
        jPanelButton.add(buttonSatuan);

        buttonSupplier.setText("Supplier");
        buttonSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSupplierActionPerformed(evt);
            }
        });
        jPanelButton.add(buttonSupplier);

        buttonKategori.setText("Kategori");
        buttonKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKategoriActionPerformed(evt);
            }
        });
        jPanelButton.add(buttonKategori);

        buttonBarang.setText("Barang");
        buttonBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBarangActionPerformed(evt);
            }
        });
        jPanelButton.add(buttonBarang);

        jPanel1.add(jPanelButton);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        buttonBeliBarang.setText("Pemasukan Barang");
        buttonBeliBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBeliBarangActionPerformed(evt);
            }
        });
        jPanel2.add(buttonBeliBarang);

        buttonJualBarang.setText("Pengeluaran Barang");
        buttonJualBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJualBarangActionPerformed(evt);
            }
        });
        jPanel2.add(buttonJualBarang);

        buttonStockBarang.setText("Stock Barang");
        buttonStockBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockBarangActionPerformed(evt);
            }
        });
        jPanel2.add(buttonStockBarang);

        buttonLaporan.setText("Laporan");
        buttonLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLaporanActionPerformed(evt);
            }
        });
        jPanel2.add(buttonLaporan);

        buttonKeluar.setText("Keluar");
        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarActionPerformed(evt);
            }
        });
        jPanel2.add(buttonKeluar);

        jPanel1.add(jPanel2);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(956, 400));
        jDesktopPane1.setLayout(new javax.swing.BoxLayout(jDesktopPane1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(jDesktopPane1);

        jPanelFooter.setBackground(new java.awt.Color(255, 255, 0));
        jPanelFooter.setLayout(new java.awt.GridLayout(1, 0));

        labelFooter.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelFooter.setForeground(new java.awt.Color(0, 0, 255));
        labelFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFooter.setText("SISTEM INFORMASI PENGELUARAN DAN PEMASUKAN STOK BARANG");
        jPanelFooter.add(labelFooter);

        jPanel1.add(jPanelFooter);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonJualBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJualBarangActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormBarangKeluar fb = new FormBarangKeluar();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonJualBarangActionPerformed

    private void buttonLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLaporanActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormLaporan fb = new FormLaporan();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonLaporanActionPerformed

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed

        if (JOptionPane.showConfirmDialog(null, "Yakin ingin keluar?", "KHANZA App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            FormLogin2 formLogin2 = new FormLogin2();
            formLogin2.setVisible(true);
        }
    }//GEN-LAST:event_buttonKeluarActionPerformed

    private void buttonBeliBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBeliBarangActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormBarangMasuk fb = new FormBarangMasuk();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonBeliBarangActionPerformed

    private void buttonSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSatuanActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormSatuan fb = new FormSatuan();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonSatuanActionPerformed

    private void buttonSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSupplierActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormSupplier fb = new FormSupplier();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonSupplierActionPerformed

    private void buttonKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKategoriActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormKategori fb = new FormKategori();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonKategoriActionPerformed

    private void buttonPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPelangganActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormPelanggan fb = new FormPelanggan();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonPelangganActionPerformed

    private void buttonBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBarangActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormBarang fb = new FormBarang();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonBarangActionPerformed

    private void buttonStockBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockBarangActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        FormStockBarang fb = new FormStockBarang();
        jDesktopPane1.add(fb);
        fb.setVisible(true);
    }//GEN-LAST:event_buttonStockBarangActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBarang;
    private javax.swing.JButton buttonBeliBarang;
    private javax.swing.JButton buttonJualBarang;
    private javax.swing.JButton buttonKategori;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonLaporan;
    private javax.swing.JButton buttonPelanggan;
    private javax.swing.JButton buttonSatuan;
    private javax.swing.JButton buttonStockBarang;
    private javax.swing.JButton buttonSupplier;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JLabel labelFooter;
    private javax.swing.JLabel labelHeader;
    // End of variables declaration//GEN-END:variables
}
