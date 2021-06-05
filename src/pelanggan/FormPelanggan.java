package pelanggan;

import java.awt.HeadlessException;
import koneksi.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormPelanggan extends javax.swing.JInternalFrame {

    private final DefaultTableModel model;
    private Connection conn = Koneksi.getKoneksi();
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement st;
    private String sql;
    private Date date;

    public FormPelanggan() {
        initComponents();
        kodepelanggan();
        textkpelanggan.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kede Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Nomor Telepon");
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

    private void loadData() {
        bsimpan.setEnabled(true);
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM pelanggan";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[5];
                o[0] = rs.getString("kodepelanggan");
                o[1] = rs.getString("namapelanggan");
                o[2] = rs.getString("jeniskelamin");
                o[3] = rs.getString("nomortelepon");
                o[4] = rs.getString("alamat");

                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATA GAGAL DI SIMPAN " + e);
        }
    }

    private void kodepelanggan() {
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM pelanggan ORDER by kodepelanggan DESC";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String ksupp = rs.getString("kodepelanggan").substring(1);
                String AN = "" + (Integer.parseInt(ksupp) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } else if (AN.length() == 2) {
                    Nol = "";
                }
                textkpelanggan.setText("1" + Nol + AN);
            } else {
                textkpelanggan.setText("101");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelKodePelanggan = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textkpelanggan = new javax.swing.JTextField();
        panelNamaPelanggan = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        textnpelanggan = new javax.swing.JTextField();
        panelJenisKelamin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cjeniskelamin = new javax.swing.JComboBox<>();
        panelNoTelp = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        texttelepon = new javax.swing.JTextField();
        panelAlamat = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textalamat = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
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
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jPanel4.setLayout(new java.awt.GridLayout(0, 2));

        panelKodePelanggan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Kode Pelanggan");
        panelKodePelanggan.add(jLabel8);

        textkpelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkpelangganKeyTyped(evt);
            }
        });
        panelKodePelanggan.add(textkpelanggan);

        jPanel4.add(panelKodePelanggan);

        panelNamaPelanggan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Pelanggan");
        panelNamaPelanggan.add(jLabel9);

        textnpelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnpelangganKeyTyped(evt);
            }
        });
        panelNamaPelanggan.add(textnpelanggan);

        jPanel4.add(panelNamaPelanggan);

        panelJenisKelamin.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Jenis Kelamin");
        panelJenisKelamin.add(jLabel3);

        cjeniskelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        panelJenisKelamin.add(cjeniskelamin);

        jPanel4.add(panelJenisKelamin);

        panelNoTelp.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nomor Telepon");
        panelNoTelp.add(jLabel4);

        texttelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textteleponKeyTyped(evt);
            }
        });
        panelNoTelp.add(texttelepon);

        jPanel4.add(panelNoTelp);

        panelAlamat.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Alamat");
        panelAlamat.add(jLabel5);

        textalamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textalamatKeyTyped(evt);
            }
        });
        panelAlamat.add(textalamat);

        jPanel4.add(panelAlamat);

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
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
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (textnpelanggan.getText().equals("")
                || textalamat.getText().equals("")
                || texttelepon.getText().equals("")
                || textalamat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tkodepelanggan = textkpelanggan.getText();
            String tnamapelanggan = textnpelanggan.getText();
            String tjeniskelamin = cjeniskelamin.getSelectedItem().toString();
            String ttelepon = texttelepon.getText();
            String talamat = textalamat.getText();

            try {
                long millis = System.currentTimeMillis();
                date = new java.sql.Date(millis);
                System.out.println(date);
                conn = Koneksi.getKoneksi();
                sql = "INSERT INTO pelanggan VALUES (?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, tkodepelanggan);
                pst.setString(2, tnamapelanggan);
                pst.setString(3, tjeniskelamin);
                pst.setString(4, ttelepon);
                pst.setString(5, talamat);
                pst.executeUpdate();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodepelanggan();
                textnpelanggan.setText("");
                texttelepon.setText("");
                textalamat.setText("");
                JOptionPane.showMessageDialog(null, "DATA BERHASIL TERSIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
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
        String tablekodepelanggan = (String) model.getValueAt(i, 0);
        textkpelanggan.setText(tablekodepelanggan);
        textkpelanggan.setEnabled(false);

        String tablenamapelanggan = (String) model.getValueAt(i, 1);
        textnpelanggan.setText(tablenamapelanggan);

        String tablealamat = (String) model.getValueAt(i, 2);
        cjeniskelamin.getSelectedItem().toString();

        String tabletelepon = (String) model.getValueAt(i, 3);
        texttelepon.setText(tabletelepon);

        String tablerekening = (String) model.getValueAt(i, 4);
        textalamat.setText(tablerekening);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            sql = "DELETE FROM pelanggan WHERE kodepelanggan='" + textkpelanggan.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodepelanggan();
        textnpelanggan.setText("");
        texttelepon.setText("");
        textalamat.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textnpelanggan.getText().equals("")
                || texttelepon.getText().equals("")
                || textalamat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int i = tableinput.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                conn = Koneksi.getKoneksi();
                sql = "UPDATE pelanggan SET namapelanggan=?, jeniskelamin=?, nomortelepon=?, alamat=? WHERE kodepelanggan='" + textkpelanggan.getText() + "'";
                pst = conn.prepareStatement(sql);
                pst.setString(1, textnpelanggan.getText());
                pst.setString(2, cjeniskelamin.getSelectedItem().toString());
                pst.setString(3, texttelepon.getText());
                pst.setString(4, textalamat.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DI RUBAH", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
                textkpelanggan.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodepelanggan();
                textnpelanggan.setText("");
                texttelepon.setText("");
                textalamat.setText("");
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM pelanggan WHERE "
                    + "kodepelanggan LIKE '%" + textcari.getText()
                    + "%' OR namapelanggan LIKE'%" + textcari.getText()
                    + "%' OR jeniskelamin LIKE'" + textcari.getText()
                    + "%' OR nomortelepon LIKE'%" + textcari.getText()
                    + "%' OR alamat LIKE'%" + textcari.getText() + "%'";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("kodepelanggan");
                o[1] = rs.getString("namapelanggan");
                o[2] = rs.getString("jeniskelamin");
                o[3] = rs.getString("nomortelepon");
                o[4] = rs.getString("alamat");
                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_textcariKeyReleased
    private void textteleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textteleponKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textteleponKeyTyped
    private void textalamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textalamatKeyTyped
    }//GEN-LAST:event_textalamatKeyTyped
    private void textnpelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnpelangganKeyTyped
        FilterHuruf(evt);
    }//GEN-LAST:event_textnpelangganKeyTyped
    private void textkpelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkpelangganKeyTyped
    }//GEN-LAST:event_textkpelangganKeyTyped
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormPelanggan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> cjeniskelamin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAlamat;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelJenisKelamin;
    private javax.swing.JPanel panelKodePelanggan;
    private javax.swing.JPanel panelNamaPelanggan;
    private javax.swing.JPanel panelNoTelp;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textalamat;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkpelanggan;
    private javax.swing.JTextField textnpelanggan;
    private javax.swing.JTextField texttelepon;
    // End of variables declaration//GEN-END:variables
}
