package barang;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormBarang extends javax.swing.JInternalFrame {

    private Connection conn = Koneksi.getKoneksi();
    private final DefaultTableModel model;
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement st;
    private String sql;

    public FormBarang() {
        initComponents();
        kodebarang();
        textkbarang.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        loadData();
    }

    private void FilterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "MASUKAN HURUF SAJA !", "PT MULIA JAYA TEXTILE", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void FilterAngka(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "MASUKAN ANGKA SAJA", "PT MULIA JAYA TEXTILE", JOptionPane.WARNING_MESSAGE);
        }
    }

    public final void loadData() {
        bsimpan.setEnabled(true);
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM barang";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[2];
                o[0] = rs.getString("kodebarang");
                o[1] = rs.getString("namabarang");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }

    private void kodebarang() {
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM barang ORDER by kodebarang DESC";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String kbar = rs.getString("kodebarang").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                } else if (AN.length() == 5) {
                    Nol = "";
                }
                textkbarang.setText("E" + Nol + AN);
            } else {
                textkbarang.setText("E00001");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelKode = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textkbarang = new javax.swing.JTextField();
        panelSupplier = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textnbarang = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        panelCari = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textcari = new javax.swing.JTextField();
        panelTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));
        jPanel5.setForeground(new java.awt.Color(0, 0, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM BARANG");
        jPanel5.add(jLabel1);

        jPanel3.setLayout(new java.awt.GridLayout(0, 2));

        panelKode.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Kode Barang");
        panelKode.add(jLabel8);
        panelKode.add(textkbarang);

        jPanel3.add(panelKode);

        panelSupplier.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");
        panelSupplier.add(jLabel3);
        panelSupplier.add(textnbarang);

        jPanel3.add(panelSupplier);

        panelTombol.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol.setLayout(new java.awt.GridLayout(1, 0));

        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        panelTombol.add(bsimpan);

        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        panelTombol.add(bedit);

        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        panelTombol.add(bhapus);

        panelCari.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel6.add(jLabel2);

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel6.add(textcari);

        panelCari.add(jPanel6);

        panelTabel.setLayout(new java.awt.GridLayout(1, 0));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        panelTabel.add(jScrollPane1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1640, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (textnbarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tkodebarang = textkbarang.getText();
            String tnamabarang = textnbarang.getText();

            try {
                conn = Koneksi.getKoneksi();
                sql = "INSERT INTO barang VALUES (?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, tkodebarang);
                pst.setString(2, tnamabarang);
                pst.executeUpdate();
                pst.close();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                loadData();
                kodebarang();
                textnbarang.setText("");
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed
    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bsimpan.setEnabled(false);
        bedit.setEnabled(true);
        bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablekodebarang = (String) model.getValueAt(i, 0);
        textkbarang.setText(tablekodebarang);
        textkbarang.setEnabled(false);

        String tablenamabarang = (String) model.getValueAt(i, 1);
        textnbarang.setText(tablenamabarang);

    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            sql = "DELETE FROM barang WHERE kodebarang='" + textkbarang.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodebarang();
        textnbarang.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textnbarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int i = tableinput.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                Connection c = Koneksi.getKoneksi();
                sql = "UPDATE barang SET namabarang=? WHERE kodebarang='" + textkbarang.getText() + "'";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, textnbarang.getText());
                p.executeUpdate();
                textkbarang.requestFocus();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodebarang();
                textnbarang.setText("");
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM barang WHERE "
                    + "kodebarang LIKE '%" + textcari.getText()
                    + "%' OR namabarang LIKE'%" + textcari.getText() + "%'";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] o = new Object[2];
                o[0] = rs.getString("kodebarang");
                o[1] = rs.getString("namabarang");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_textcariKeyReleased
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormBarang().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelKode;
    private javax.swing.JPanel panelSupplier;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkbarang;
    private javax.swing.JTextField textnbarang;
    // End of variables declaration//GEN-END:variables
}
