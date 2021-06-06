package supplier;

import java.awt.HeadlessException;
import koneksi.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormSupplier extends javax.swing.JInternalFrame {

    private final DefaultTableModel model;
    private Connection conn = Koneksi.getKoneksi();
    private ResultSet rs;
    private PreparedStatement pst;
    private Statement st;
    private String sql;
    private Date date;

    public FormSupplier() {
        initComponents();
        kodesupplier();
        textksupplier.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat");
        model.addColumn("Nomor Telepon");
        model.addColumn("Nomor Rekening");
        model.addColumn("Bank");
        model.addColumn("Email");
        loadData();
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
            sql = "SELECT * FROM supplier";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("kodesupplier");
                o[1] = rs.getString("namasupplier");
                o[2] = rs.getString("alamat");
                o[3] = rs.getString("nomortelepon");
                o[4] = rs.getString("norekening");
                o[5] = rs.getString("bank");
                o[6] = rs.getString("email");
                model.addRow(o);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void kodesupplier() {
        try {
            conn = Koneksi.getKoneksi();
            st = conn.createStatement();

            sql = "SELECT * FROM supplier ORDER by kodesupplier DESC";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String ksupp = rs.getString("kodesupplier").substring(1);
                String AN = "" + (Integer.parseInt(ksupp) + 1);
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
                textksupplier.setText("C" + Nol + AN);
            } else {
                textksupplier.setText("C00001");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelKodeSupplier = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textksupplier = new javax.swing.JTextField();
        panelNamaSupplier = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        textnsupplier = new javax.swing.JTextField();
        panelAlamat = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textalamat = new javax.swing.JTextField();
        panelTelepon = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        texttelepon = new javax.swing.JTextField();
        panelNorek = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textrekening = new javax.swing.JTextField();
        panelBank = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        textbank = new javax.swing.JTextField();
        panelEmail = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        textemail = new javax.swing.JTextField();
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

        panelKodeSupplier.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Kode Supplier");
        panelKodeSupplier.add(jLabel8);

        textksupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textksupplierKeyTyped(evt);
            }
        });
        panelKodeSupplier.add(textksupplier);

        jPanel4.add(panelKodeSupplier);

        panelNamaSupplier.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Supplier");
        panelNamaSupplier.add(jLabel9);

        textnsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnsupplierKeyTyped(evt);
            }
        });
        panelNamaSupplier.add(textnsupplier);

        jPanel4.add(panelNamaSupplier);

        panelAlamat.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Alamat");
        panelAlamat.add(jLabel3);
        panelAlamat.add(textalamat);

        jPanel4.add(panelAlamat);

        panelTelepon.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Telepon");
        panelTelepon.add(jLabel4);

        texttelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textteleponKeyTyped(evt);
            }
        });
        panelTelepon.add(texttelepon);

        jPanel4.add(panelTelepon);

        panelNorek.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nomor Rekening");
        panelNorek.add(jLabel5);

        textrekening.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textrekeningKeyTyped(evt);
            }
        });
        panelNorek.add(textrekening);

        jPanel4.add(panelNorek);

        panelBank.setLayout(new java.awt.GridLayout(1, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Bank");
        panelBank.add(jLabel6);

        textbank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textbankKeyTyped(evt);
            }
        });
        panelBank.add(textbank);

        jPanel4.add(panelBank);

        panelEmail.setLayout(new java.awt.GridLayout(1, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Email");
        panelEmail.add(jLabel7);

        textemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textemailKeyTyped(evt);
            }
        });
        panelEmail.add(textemail);

        jPanel4.add(panelEmail);

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
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
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
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (textnsupplier.getText().equals("")
                || textalamat.getText().equals("")
                || texttelepon.getText().equals("")
                || textrekening.getText().equals("")
                || textbank.getText().equals("")
                || textemail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tkodesupplier = textksupplier.getText();
            String tnamasupplier = textnsupplier.getText();
            String talamat = textalamat.getText();
            String ttelepon = texttelepon.getText();
            String trekening = textrekening.getText();
            String tbank = textbank.getText();
            String temail = textemail.getText();
            try {
                long millis = System.currentTimeMillis();
                date = new java.sql.Date(millis);
                System.out.println(date);
                String ttanggal = date.toString();
                conn = Koneksi.getKoneksi();
                sql = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, tkodesupplier);
                pst.setString(2, tnamasupplier);
                pst.setString(3, talamat);
                pst.setString(4, ttelepon);
                pst.setString(5, trekening);
                pst.setString(6, tbank);
                pst.setString(7, temail);
                pst.setString(8, ttanggal);
  
                pst.executeUpdate();
                pst.close();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodesupplier();
                textnsupplier.setText("");
                textalamat.setText("");
                texttelepon.setText("");
                textrekening.setText("");
                textbank.setText("");
                textemail.setText("");
                textemail.setText("");
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
        String tablekodesupplier = (String) model.getValueAt(i, 0);
        textksupplier.setText(tablekodesupplier);
        textksupplier.setEnabled(false);

        String tablenamasupplier = (String) model.getValueAt(i, 1);
        textnsupplier.setText(tablenamasupplier);

        String tablealamat = (String) model.getValueAt(i, 2);
        textalamat.setText(tablealamat);

        String tabletelepon = (String) model.getValueAt(i, 3);
        texttelepon.setText(tabletelepon);

        String tablerekening = (String) model.getValueAt(i, 4);
        textrekening.setText(tablerekening);

        String tablebank = (String) model.getValueAt(i, 5);
        textbank.setText(tablebank);

        String tableemail = (String) model.getValueAt(i, 6);
        textemail.setText(tableemail);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            sql = "DELETE FROM supplier WHERE kodesupplier='" + textksupplier.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodesupplier();
        textnsupplier.setText("");
        textalamat.setText("");
        texttelepon.setText("");
        textrekening.setText("");
        textbank.setText("");
        textemail.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textnsupplier.getText().equals("")
                || textalamat.getText().equals("")
                || texttelepon.getText().equals("")
                || textrekening.getText().equals("")
                || textbank.getText().equals("")
                || textemail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int i = tableinput.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                long millis = System.currentTimeMillis();
                date = new java.sql.Date(millis);
                System.out.println(date);
                String ttanggal = date.toString();
                conn = Koneksi.getKoneksi();
                sql = "UPDATE supplier SET namasupplier=?, alamat=?, nomortelepon=?, norekening=?, bank=?, email=?, tanggal=? where kodesupplier='" + textksupplier.getText() + "'";
                pst = conn.prepareStatement(sql);
                pst.setString(1, textnsupplier.getText());
                pst.setString(2, textalamat.getText());
                pst.setString(3, texttelepon.getText());
                pst.setString(4, textrekening.getText());
                pst.setString(5, textbank.getText());
                pst.setString(6, textemail.getText());
                pst.setString(7, ttanggal);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
                textksupplier.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                loadData();
                kodesupplier();
                textnsupplier.setText("");
                textalamat.setText("");
                texttelepon.setText("");
                textrekening.setText("");
                textbank.setText("");
                textemail.setText("");
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
                    + "%' OR namasupplier LIKE'%" + textcari.getText()
                    + "%' OR alamat like'" + textcari.getText()
                    + "%' OR nomortelepon LIKE'%" + textcari.getText()
                    + "%' OR norekening LIKE'%" + textcari.getText()
                    + "%' OR bank LIKE'%" + textcari.getText()
                    + "%' OR email LIKE'%" + textcari.getText() + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("kodesupplier");
                o[1] = rs.getString("namasupplier");
                o[2] = rs.getString("alamat");
                o[3] = rs.getString("nomortelepon");
                o[4] = rs.getString("norekening");
                o[5] = rs.getString("bank");
                o[6] = rs.getString("email");
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
    private void textrekeningKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textrekeningKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textrekeningKeyTyped
    private void textbankKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textbankKeyTyped
    }//GEN-LAST:event_textbankKeyTyped
    private void textemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textemailKeyTyped
    }//GEN-LAST:event_textemailKeyTyped
    private void textnsupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnsupplierKeyTyped
    }//GEN-LAST:event_textnsupplierKeyTyped
    private void textksupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textksupplierKeyTyped
    }//GEN-LAST:event_textksupplierKeyTyped
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormSupplier().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAlamat;
    private javax.swing.JPanel panelBank;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelEmail;
    private javax.swing.JPanel panelKodeSupplier;
    private javax.swing.JPanel panelNamaSupplier;
    private javax.swing.JPanel panelNorek;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTelepon;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textalamat;
    private javax.swing.JTextField textbank;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textemail;
    private javax.swing.JTextField textksupplier;
    private javax.swing.JTextField textnsupplier;
    private javax.swing.JTextField textrekening;
    private javax.swing.JTextField texttelepon;
    // End of variables declaration//GEN-END:variables
}
