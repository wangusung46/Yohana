package barang;

import java.awt.HeadlessException;
import java.sql.*;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormStockBarang1 extends javax.swing.JInternalFrame {

    private final DefaultTableModel model;
    private Connection conn = Koneksi.getKoneksi();
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement st;
    private String sql;
    private double tot, hb, jum;

    public FormStockBarang1() {
        initComponents();
        textkstock.setEnabled(false);
        texttotal.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Stock");
        model.addColumn("Kategori Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Total");
        loadData();
    }

    private void loadData() {
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM stock";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[7];
                o[0] = rs.getString("kodestock");
                o[1] = rs.getString("kodekategori");
                o[2] = rs.getString("kodebarang");
                o[3] = rs.getString("kodesatuan");
                o[4] = rs.getString("jumlahbarang");
                o[5] = rs.getString("hargabeli");
                o[6] = rs.getString("total");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelBeliBarang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        textkstock = new javax.swing.JTextField();
        panelJumlah = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        textjumlah = new javax.swing.JTextField();
        panelHargaBeli = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jhbeli = new javax.swing.JLabel();
        texthbeli = new javax.swing.JTextField();
        panelTombolTotal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        panelTotal = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jtotal = new javax.swing.JLabel();
        texttotal = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bhapus = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        panelCari = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textcari = new javax.swing.JTextField();
        panelTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 51, 51));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.setForeground(new java.awt.Color(0, 0, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM PEMASUKAN BARANG");
        jPanel6.add(jLabel1);

        jPanel3.setLayout(new java.awt.GridLayout(0, 2));

        panelBeliBarang.setLayout(new java.awt.GridLayout(1, 0));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Kode Pemasukan Barang");
        panelBeliBarang.add(jLabel13);

        textkstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkstockKeyTyped(evt);
            }
        });
        panelBeliBarang.add(textkstock);

        jPanel3.add(panelBeliBarang);

        panelJumlah.setLayout(new java.awt.GridLayout(1, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Jumlah");
        panelJumlah.add(jLabel10);
        panelJumlah.add(textjumlah);

        jPanel3.add(panelJumlah);

        panelHargaBeli.setLayout(new java.awt.GridLayout(1, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Harga Pemasukan");
        panelHargaBeli.add(jLabel11);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jhbeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jhbeli.setText("Rp");
        jPanel5.add(jhbeli);

        texthbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texthbeliActionPerformed(evt);
            }
        });
        jPanel5.add(texthbeli);

        panelHargaBeli.add(jPanel5);

        jPanel3.add(panelHargaBeli);

        panelTombolTotal.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("TOTAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelTombolTotal.add(jButton1);

        panelTotal.setLayout(new java.awt.GridLayout(1, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Total");
        panelTotal.add(jLabel12);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtotal.setText("Rp");
        jPanel4.add(jtotal);
        jPanel4.add(texttotal);

        panelTotal.add(jPanel4);

        panelTombol.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol.setLayout(new java.awt.GridLayout(1, 0));

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CARI");
        jPanel11.add(jLabel2);

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel11.add(textcari);

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
        jScrollPane1.setViewportView(tableinput);

        panelTabel.add(jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1289, Short.MAX_VALUE)
            .addComponent(panelTombolTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombolTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bedit.setEnabled(true);
        bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablekodestock = (String) model.getValueAt(i, 0);
        textkstock.setText(tablekodestock);
        textkstock.setEnabled(false);

        String tablejumlah = (String) model.getValueAt(i, 4);
        textjumlah.setText(tablejumlah);

        String tablehargabeli = (String) model.getValueAt(i, 5);
        texthbeli.setText(tablehargabeli);

        String tabletotal = (String) model.getValueAt(i, 6);
        texttotal.setText(tabletotal);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            sql = "delete from stock where kodestock='" + textkstock.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        textjumlah.setText("");
        texthbeli.setText("");
        texttotal.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textjumlah.getText().equals("")
                || texthbeli.getText().equals("")
                || texttotal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int i = tableinput.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                conn = Koneksi.getKoneksi();
                sql = "UPDATE stock SET jumlahbarang=?, hargabeli=?, total=? where kodestock='" + textkstock.getText() + "'";
                pst = conn.prepareStatement(sql);
                pst.setString(1, textjumlah.getText());
                pst.setString(2, texthbeli.getText());
                pst.setString(3, texttotal.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                textkstock.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                loadData();
                textjumlah.setText("");
                texthbeli.setText("");
                texttotal.setText("");
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM stock WHERE "
                    + "kodestock LIKE '%" + textcari.getText()
                    + "%' OR namakategori LIKE'%" + textcari.getText()
                    + "%' OR namabarang like'" + textcari.getText()
                    + "%' OR namasatuan like'%" + textcari.getText()
                    + "%' OR jumlah like'%" + textcari.getText()
                    + "%' OR hargabeli like'%" + textcari.getText()
                    + "%' OR total like'%" + textcari.getText() + "%'";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] o = new Object[7];
                o[0] = rs.getString("kodestock");
                o[1] = rs.getString("namakategori");
                o[2] = rs.getString("namabarang");
                o[3] = rs.getString("namasatuan");
                o[4] = rs.getString("jumlahbarang");
                o[5] = rs.getString("hargabeli");
                o[6] = rs.getString("total");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textkstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkstockKeyTyped
    }//GEN-LAST:event_textkstockKeyTyped

    private void texthbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texthbeliActionPerformed

    }//GEN-LAST:event_texthbeliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hb = Integer.parseInt(texthbeli.getText());
        jum = Integer.parseInt(textjumlah.getText());
        tot = hb * jum;
        String total = String.valueOf(tot);
        texttotal.setText(total);
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormStockBarang1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jhbeli;
    private javax.swing.JLabel jtotal;
    private javax.swing.JPanel panelBeliBarang;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelHargaBeli;
    private javax.swing.JPanel panelJumlah;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JPanel panelTombolTotal;
    private javax.swing.JPanel panelTotal;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField textjumlah;
    private javax.swing.JTextField textkstock;
    private javax.swing.JTextField texttotal;
    // End of variables declaration//GEN-END:variables
}
