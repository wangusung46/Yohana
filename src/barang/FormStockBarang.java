package barang;

import java.awt.HeadlessException;
import java.sql.*;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormStockBarang extends javax.swing.JInternalFrame {

    private final DefaultTableModel defaultTableModel;
    private Connection connection = Koneksi.getKoneksi();
    private ResultSet resultSet;
    private Statement statement;
    private String sql;

    public FormStockBarang() {
        initComponents();
        defaultTableModel = new DefaultTableModel();
        tableinput.setModel(defaultTableModel);
        defaultTableModel.addColumn("Kode Stock");
        defaultTableModel.addColumn("Kategori Barang");
        defaultTableModel.addColumn("Nama Barang");
        defaultTableModel.addColumn("Satuan Barang");
        defaultTableModel.addColumn("Jumlah Barang");
        loadData();
    }

    private void loadData() {
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();

        try {
            String sql = "SELECT * FROM stock";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            Statement statementRelation = connection.createStatement();
            ResultSet resultSetRelation;
            
            while (resultSet.next()) {
                Object[] objects = new Object[7];
                objects[0] = resultSet.getString("kodestock");
                resultSetRelation = statementRelation.executeQuery("SELECT namakategori FROM kategori WHERE kodekategori = '" + resultSet.getString("kodekategori") + "'");
                resultSetRelation.next();
                objects[1] = resultSetRelation.getString("namakategori");

                resultSetRelation = statementRelation.executeQuery("SELECT namabarang FROM barang WHERE kodebarang = '" + resultSet.getString("kodebarang") + "'");
                resultSetRelation.next();
                objects[2] = resultSetRelation.getString("namabarang");

                resultSetRelation = statementRelation.executeQuery("SELECT namasatuan FROM satuan WHERE kodesatuan = '" + resultSet.getString("kodesatuan") + "'");
                resultSetRelation.next();
                objects[3] = resultSetRelation.getString("namasatuan");
                objects[4] = resultSet.getString("jumlahbarang");

                defaultTableModel.addRow(objects);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel1.setText("FORM STOCK BARANG");
        jPanel6.add(jLabel1);

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
        jScrollPane1.setViewportView(tableinput);

        panelTabel.add(jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1289, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();

        try {
            connection = Koneksi.getKoneksi();
            statement = connection.createStatement();

            sql = "SELECT * FROM stock WHERE "
                    + "kodestock LIKE '%" + textcari.getText()
                    + "%' OR kodekategori LIKE'%" + textcari.getText()
                    + "%' OR kodebarang like'" + textcari.getText()
                    + "%' OR kodesatuan like'%" + textcari.getText()
                    + "%' OR jumlah like'%" + textcari.getText() + "%'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Object[] o = new Object[5];
                o[0] = resultSet.getString("kodestock");
                o[1] = resultSet.getString("kodekategori");
                o[2] = resultSet.getString("kodebarang");
                o[3] = resultSet.getString("kodesatuan");
                o[4] = resultSet.getString("jumlahbarang");

                defaultTableModel.addRow(o);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormStockBarang().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    // End of variables declaration//GEN-END:variables
}
