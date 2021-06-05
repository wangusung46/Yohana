package kategori;

import java.awt.HeadlessException;
import koneksi.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormKategori extends javax.swing.JInternalFrame {

    private final DefaultTableModel model;
    private Connection conn = Koneksi.getKoneksi();
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement st;
    private String sql;

    public FormKategori() {
        initComponents();
        kodekategori();
        textkkategori.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Kategori");
        model.addColumn("Nama Kategori");
        loadData();
    }

    private void loadData() {
        bsimpan.setEnabled(true);
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM kategori";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("kodekategori");
                o[1] = rs.getString("namakategori");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }

    private void kodekategori() {
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM kategori ORDER by kodekategori DESC";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String ksat = rs.getString("kodekategori").substring(1);
                String AN = "" + (Integer.parseInt(ksat) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } else if (AN.length() == 2) {
                    Nol = "";
                }
                textkkategori.setText("4" + Nol + AN);
            } else {
                textkkategori.setText("401");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelKategori = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        textkkategori = new javax.swing.JTextField();
        panelNamaKategori = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textnkategori = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        panelCari = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        textcari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelTabel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jPanel4.setLayout(new java.awt.GridLayout(0, 2));

        panelKategori.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Kode Kategori");
        panelKategori.add(jLabel9);

        textkkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkkategoriKeyTyped(evt);
            }
        });
        panelKategori.add(textkkategori);

        jPanel4.add(panelKategori);

        panelNamaKategori.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nama Kategori");
        panelNamaKategori.add(jLabel8);

        textnkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnkategoriKeyTyped(evt);
            }
        });
        panelNamaKategori.add(textnkategori);

        jPanel4.add(panelNamaKategori);

        panelTombol.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol.setLayout(new java.awt.GridLayout(1, 0));

        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        panelTombol.add(bsimpan);

        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        panelTombol.add(bhapus);

        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        panelTombol.add(bedit);

        panelCari.setLayout(new java.awt.GridLayout(1, 0));

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.X_AXIS));

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel11.add(textcari);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel11.add(jLabel2);

        panelCari.add(jPanel11);

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
        jScrollPane2.setViewportView(tableinput);

        panelTabel.add(jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (textnkategori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tkodekategori = textkkategori.getText();
            String tnamakategori = textnkategori.getText();
            try {
                conn = Koneksi.getKoneksi();
                sql = "INSERT INTO kategori VALUES (?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, tkodekategori);
                pst.setString(2, tnamakategori);
                pst.executeUpdate();
                pst.close();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIRSIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodekategori();
                textnkategori.setText("");
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            sql = "DELETE FROM kategori WHERE kodekategori='" + textkkategori.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodekategori();
        textnkategori.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textnkategori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int i = tableinput.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                conn = Koneksi.getKoneksi();
                sql = "UPDATE kategori SET namakategori=? WHERE kodekategori='" + textkkategori.getText() + "'";
                pst = conn.prepareStatement(sql);
                pst.setString(1, textnkategori.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIRUBAH", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
                textnkategori.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah " + e);
            } finally {
                loadData();
                kodekategori();
                textnkategori.setText("");
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();
            sql = "SELECT * FROM kategori WHERE kodekategori LIKE '%" + textcari.getText() + "%' OR namakategori LIKE'%" + textcari.getText() + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[2];
                o[0] = rs.getString("kodekategori");
                o[1] = rs.getString("namakategori");
                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textnkategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnkategoriKeyTyped
    }//GEN-LAST:event_textnkategoriKeyTyped

    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        // TODO add your handling code here:
        bsimpan.setEnabled(false);
        bedit.setEnabled(true);
        bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablekodepelanggan = (String) model.getValueAt(i, 0);
        textkkategori.setText(tablekodepelanggan);
        textkkategori.setEnabled(false);

        String tablenamapelanggan = (String) model.getValueAt(i, 1);
        textnkategori.setText(tablenamapelanggan);
    }//GEN-LAST:event_tableinputMouseClicked

    private void textkkategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkkategoriKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textkkategoriKeyTyped
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormKategori().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelKategori;
    private javax.swing.JPanel panelNamaKategori;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkkategori;
    private javax.swing.JTextField textnkategori;
    // End of variables declaration//GEN-END:variables
}
