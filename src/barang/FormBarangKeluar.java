package barang;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FormBarangKeluar extends javax.swing.JInternalFrame {

    private final DefaultTableModel defaultTableModel;
    private Connection connection = Koneksi.getKoneksi();
    private int bay, bel, jum, hj, jj, kem, unt, tot, totbel;
    private String awal;

    public FormBarangKeluar() {
        initComponents();
        textfaktur.setEnabled(false);
        textnsatuan.setEnabled(false);
        texthbeli.setEnabled(false);
        textkembali.setEnabled(false);
        textuntung.setEnabled(false);
        texttbarang.setEnabled(false);
        defaultTableModel = new DefaultTableModel();
        tableinput.setModel(defaultTableModel);
        defaultTableModel.addColumn("Faktur");
        defaultTableModel.addColumn("Nama Pelanggan");
        defaultTableModel.addColumn("Nama Barang");
        defaultTableModel.addColumn("Satuan Barang");
        defaultTableModel.addColumn("Harga Jual");
        defaultTableModel.addColumn("Jumlah Jual");
        defaultTableModel.addColumn("Harga Jual Total");
        defaultTableModel.addColumn("Untung");
        defaultTableModel.addColumn("Kembalian");
        defaultTableModel.addColumn("Tanggal");
        loadData();
        tampilKodePelanggan();
        tampilCetak();
    }

    private void FilterAngka(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "MASUKAN ANGKA SAJA", "PT MULIA JAYA TEXTILE", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadData() {
        try {
            bsimpan.setEnabled(true);
            defaultTableModel.getDataVector().removeAllElements();
            defaultTableModel.fireTableDataChanged();
            String sql = "SELECT * FROM jualbarang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            Statement statementRelation = connection.createStatement();
            ResultSet resultSetRelation;
            
            while (resultSet.next()) {
                Object[] objects = new Object[10];
                objects[0] = resultSet.getString("faktur");
                
                resultSetRelation = statementRelation.executeQuery("SELECT namapelanggan FROM pelanggan WHERE kodepelanggan = '" + resultSet.getString("kodepelanggan") + "'");
                resultSetRelation.next();
                objects[1] = resultSetRelation.getString("namapelanggan");
                
                resultSetRelation = statementRelation.executeQuery("SELECT namabarang FROM barang WHERE kodebarang = '" + resultSet.getString("kodebarang") + "'");
                resultSetRelation.next();
                objects[2] = resultSetRelation.getString("namabarang");

                resultSetRelation = statementRelation.executeQuery("SELECT namasatuan FROM satuan WHERE kodesatuan = '" + resultSet.getString("kodesatuan") + "'");
                resultSetRelation.next();
                objects[3] = resultSetRelation.getString("namasatuan");
                
                objects[4] = resultSet.getString("hargajual");
                objects[5] = resultSet.getString("jumlahjual");
                objects[6] = resultSet.getString("hargajualtotal");
                objects[7] = resultSet.getString("untung");
                objects[8] = resultSet.getString("kembali");
                objects[9] = resultSet.getString("tanggal");
                defaultTableModel.addRow(objects);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diload " + e);
        }
    }

    private void tampilCetak() {
        try {
            String sql = "SELECT * FROM jualbarang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ccetak.addItem(resultSet.getString("faktur"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodePelanggan() {
        try {
            String sql = "SELECT * FROM pelanggan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cnpelanggan.addItem(resultSet.getString("kodepelanggan") + " " + resultSet.getString("namapelanggan"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeBarang() {
        try {
            String sql1, sql2;
            sql1 = "SELECT * FROM belibarang GROUP BY kodebarang";

            Statement st1 = connection.createStatement();
            Statement st2 = connection.createStatement();
            ResultSet rs1, rs2;
            rs1 = st1.executeQuery(sql1);
            int i = 0;
            while (rs1.next()) {
                sql2 = "SELECT * FROM barang WHERE kodebarang = '" + rs1.getString("kodebarang") + "'";
                System.out.println(sql2);
                rs2 = st2.executeQuery(sql2);
                rs2.next();
                cnbarang.addItem(rs2.getString("kodebarang") + " " + rs2.getString("namabarang"));
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilAtributBarang() {
        try {
            int i = 1;
            String sql1 = "SELECT * FROM belibarang WHERE kodebarang LIKE '"
                    + cnbarang.getSelectedItem().toString().substring(0, 6)
                    + "' GROUP BY kodebarang";
            System.out.println(sql1);
            Statement st1 = connection.createStatement();
            ResultSet rs1;
            rs1 = st1.executeQuery(sql1);
            rs1.absolute(i);

            String sql2 = "SELECT * FROM satuan WHERE kodesatuan = '"
                    + rs1.getString("kodesatuan") + "'";
            System.out.println(sql2);
            Statement st2 = connection.createStatement();
            ResultSet rs2;
            rs2 = st2.executeQuery(sql2);
            rs2.absolute(i);
            textnsatuan.setText(rs2.getString("kodesatuan") + " " + rs2.getString("namasatuan"));
            texthbeli.setText(rs1.getString("hargabeli"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kodeJual() {
        try {
            connection = Koneksi.getKoneksi();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM jualbarang ORDER BY faktur DESC";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String kbar = resultSet.getString("faktur").substring(1);
                System.out.println(kbar);
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
                awal = "H" + Nol + AN;
            } else {
                awal = "H00001";
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
//        String rpelanggan = cnpelanggan.getSelectedItem().toString().substring(0, 6);
//        String rbeli = cnbarang.getSelectedItem().toString().substring(0, 6);
//        String rsatuan = textnsatuan.getText().substring(0, 6);
        textfaktur.setText(awal);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelFaktur = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textfaktur = new javax.swing.JTextField();
        panelPelanggan = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cnpelanggan = new javax.swing.JComboBox<>();
        panelSatuan = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textnsatuan = new javax.swing.JTextField();
        panelNamaBarang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cnbarang = new javax.swing.JComboBox<>();
        panelHargaBeli = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        texthbeli = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        panelJumlahJual = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        textjjual = new javax.swing.JTextField();
        panelTanggalJual = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        texthjual = new javax.swing.JTextField();
        panelTombol1 = new javax.swing.JPanel();
        jumlah = new javax.swing.JButton();
        panelTabel1 = new javax.swing.JPanel();
        panelTotal = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        texttbarang = new javax.swing.JTextField();
        panelBayar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textbayar = new javax.swing.JTextField();
        panelTombol2 = new javax.swing.JPanel();
        btotal = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelKeuntungan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        textuntung = new javax.swing.JTextField();
        panelKembalian = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        textkembali = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        panelCetak = new javax.swing.JPanel();
        ccetak = new javax.swing.JComboBox<>();
        bcetak = new javax.swing.JButton();
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

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setForeground(new java.awt.Color(0, 0, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM PENGELUARAN BARANG");
        jPanel7.add(jLabel1);

        jPanel3.setLayout(new java.awt.GridLayout(0, 2));

        panelFaktur.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Faktur");
        panelFaktur.add(jLabel8);

        textfaktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfakturKeyTyped(evt);
            }
        });
        panelFaktur.add(textfaktur);

        jPanel3.add(panelFaktur);

        panelPelanggan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nama Pelanggan");
        panelPelanggan.add(jLabel12);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        cnpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnpelangganActionPerformed(evt);
            }
        });
        jPanel5.add(cnpelanggan);

        panelPelanggan.add(jPanel5);

        jPanel3.add(panelPelanggan);

        panelSatuan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama Satuan");
        panelSatuan.add(jLabel5);
        panelSatuan.add(textnsatuan);

        jPanel3.add(panelSatuan);

        panelNamaBarang.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");
        panelNamaBarang.add(jLabel3);

        cnbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnbarangActionPerformed(evt);
            }
        });
        panelNamaBarang.add(cnbarang);

        jPanel3.add(panelNamaBarang);

        panelHargaBeli.setLayout(new java.awt.GridLayout(1, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Harga Pemasukan");
        panelHargaBeli.add(jLabel10);
        panelHargaBeli.add(texthbeli);

        jPanel3.add(panelHargaBeli);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        panelJumlahJual.setLayout(new java.awt.GridLayout(1, 0));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Jumlah Jual");
        panelJumlahJual.add(jLabel18);

        textjjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textjjualKeyTyped(evt);
            }
        });
        panelJumlahJual.add(textjjual);

        jPanel4.add(panelJumlahJual);

        panelTanggalJual.setLayout(new java.awt.GridLayout(1, 0));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Harga Pengeluaran");
        panelTanggalJual.add(jLabel13);

        texthjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                texthjualKeyTyped(evt);
            }
        });
        panelTanggalJual.add(texthjual);

        jPanel4.add(panelTanggalJual);

        panelTombol1.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol1.setLayout(new java.awt.GridLayout(1, 0));

        jumlah.setText("JUMLAH");
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        panelTombol1.add(jumlah);

        panelTabel1.setLayout(new java.awt.GridLayout(1, 0));

        panelTotal.setLayout(new java.awt.GridLayout(1, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Total");
        panelTotal.add(jLabel7);
        panelTotal.add(texttbarang);

        panelTabel1.add(panelTotal);

        panelBayar.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Pembayaran");
        panelBayar.add(jLabel4);

        textbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textbayarKeyTyped(evt);
            }
        });
        panelBayar.add(textbayar);

        panelTabel1.add(panelBayar);

        panelTombol2.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol2.setLayout(new java.awt.GridLayout(1, 0));

        btotal.setText("TOTAL");
        btotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btotalActionPerformed(evt);
            }
        });
        panelTombol2.add(btotal);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        panelKeuntungan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Keuntungan");
        panelKeuntungan.add(jLabel15);
        panelKeuntungan.add(textuntung);

        jPanel1.add(panelKeuntungan);

        panelKembalian.setLayout(new java.awt.GridLayout(1, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Kembalian");
        panelKembalian.add(jLabel6);
        panelKembalian.add(textkembali);

        jPanel1.add(panelKembalian);

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
        bhapus.setPreferredSize(new java.awt.Dimension(50, 24));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        panelTombol.add(bhapus);

        bedit.setText("EDIT");
        bedit.setPreferredSize(new java.awt.Dimension(50, 24));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        panelTombol.add(bedit);

        panelCetak.setLayout(new java.awt.GridLayout(1, 0));

        panelCetak.add(ccetak);

        bcetak.setText("CETAK");
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });
        panelCetak.add(bcetak);

        panelCari.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 5060, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (texthjual.getText().equals("")
                || textjjual.getText().equals("")
                || textbayar.getText().equals("")
                || texttbarang.getText().equals("")
                || textbayar.getText().equals("")
                || textuntung.getText().equals("")
                || textkembali.getText().equals("")
                ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tfaktur = textfaktur.getText();
            String tkpelanggan = cnpelanggan.getSelectedItem().toString().substring(0, 6);
            String tkbarang = cnbarang.getSelectedItem().toString().substring(0, 6);
            String tksatuan = textnsatuan.getText().substring(0, 6);
            String thjual = texthjual.getText();
            String tjjual = textjjual.getText();
            String thjtotal = texttbarang.getText();
            String tbayar = textbayar.getText();
            String tkembali = textkembali.getText();
            String tuntung = textuntung.getText();
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);
            String ttanggal = date.toString();
            String sql = "INSERT INTO jualbarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, tfaktur);
                pst.setString(2, tkpelanggan);
                pst.setString(3, tkbarang);
                pst.setString(4, tksatuan);
                pst.setString(5, thjual);
                pst.setString(6, tjjual);
                pst.setString(7, thjtotal);
                pst.setString(8, tbayar);
                pst.setString(9, tkembali);
                pst.setString(10, tuntung);
                pst.setString(11, ttanggal);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            kosong();
            loadData();
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        try {
            defaultTableModel.getDataVector().removeAllElements();
            defaultTableModel.fireTableDataChanged();
            connection = Koneksi.getKoneksi();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM jualbarang WHERE "
                    + "faktur LIKE '%" + textcari.getText()
                    + "%' OR kodepelanggan LIKE'%" + textcari.getText()
                    + "%' OR kodebarang LIKE'" + textcari.getText()
                    + "%' OR kodesatuan LIKE'%" + textcari.getText()
                    + "%' OR hargajual LIKE'%" + textcari.getText()
                    + "%' OR jumlahjual LIKE'%" + textcari.getText()
                    + "%' OR hargatotal LIKE'%" + textcari.getText()
                    + "%' OR untung LIKE'%" + textcari.getText()
                    + "%' OR tanggal LIKE'%" + textcari.getText() + "%'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Object[] objects = new Object[9];
                objects[0] = resultSet.getString("faktur");
                objects[1] = resultSet.getString("namapelanggan");
                objects[2] = resultSet.getString("namabarang");
                objects[3] = resultSet.getString("namasatuan");
                objects[4] = resultSet.getString("hargajual");
                objects[5] = resultSet.getString("jumlahjual");
                objects[6] = resultSet.getString("hargajualtotal");
                objects[7] = resultSet.getString("untung");
                objects[8] = resultSet.getString("tanggal");
                defaultTableModel.addRow(objects);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textfakturKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfakturKeyTyped
    }//GEN-LAST:event_textfakturKeyTyped

    private void btotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btotalActionPerformed
        tot = Integer.parseInt(texttbarang.getText());
        bay = Integer.parseInt(textbayar.getText());
        hj = Integer.parseInt(texthjual.getText());
        jj = Integer.parseInt(textjjual.getText());
        bel = Integer.parseInt(texthbeli.getText());
        kem = bay - tot;
        if (kem < 0) {
            JOptionPane.showMessageDialog(null, "Pembayaran tidak mencukupi total penjualan");
            JOptionPane.showMessageDialog(null, "Kurang Rp." + (Integer.parseInt((texttbarang.getText())) - Integer.parseInt(textbayar.getText())));
        } else {
            totbel = bel * jj;
            unt = tot - totbel;
            String kembali = String.valueOf(kem);
            textkembali.setText(kembali);
            String untung = String.valueOf(unt);
            textuntung.setText(untung);
        }
    }//GEN-LAST:event_btotalActionPerformed

    private void texthjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texthjualKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_texthjualKeyTyped

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        hj = Integer.parseInt(texthjual.getText());
        jj = Integer.parseInt(textjjual.getText());
        if (Integer.parseInt(texthbeli.getText()) > Integer.parseInt(texthjual.getText())) {
            JOptionPane.showMessageDialog(null, "Harga pengeluaran barang lebih kecil dari pemasukan");

        } else {
            jum = hj * jj;
            String jumlahLocal = String.valueOf(jum);
            texttbarang.setText(jumlahLocal);
        }
    }//GEN-LAST:event_jumlahActionPerformed

    private void cnpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnpelangganActionPerformed
        if (cnpelanggan.getItemCount() == 0) {
            tampilKodePelanggan();
        }

        tampilKodeBarang();
        cnbarang.removeAllItems();
        if (cnbarang.getItemCount() == 0) {
            tampilKodeBarang();

        }
        tampilAtributBarang();
        kodeJual();
    }//GEN-LAST:event_cnpelangganActionPerformed

    private void cnbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnbarangActionPerformed
        if (cnbarang.getItemCount() == 0) {
            tampilKodeBarang();
        }
        tampilAtributBarang();
        kodeJual();
    }//GEN-LAST:event_cnbarangActionPerformed

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        relasiCetak();
    }//GEN-LAST:event_bcetakActionPerformed

    private void textbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textbayarKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textbayarKeyTyped

    private void textjjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textjjualKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textjjualKeyTyped

    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bsimpan.setEnabled(false);
        bedit.setEnabled(true);
        bhapus.setEnabled(true);

        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }

        String tableFaktur = (String) defaultTableModel.getValueAt(i, 0);
        textfaktur.setText(tableFaktur);

        String tablePelanggan = (String) defaultTableModel.getValueAt(i, 1);
        cnpelanggan.setSelectedItem(tablePelanggan);

        String tableBarang = (String) defaultTableModel.getValueAt(i, 2);
        cnbarang.setSelectedItem(tableBarang);

        String tableSatuan = (String) defaultTableModel.getValueAt(i, 3);
        textnsatuan.setText(tableSatuan);

        String tableHargaJual = (String) defaultTableModel.getValueAt(i, 4);
        texthjual.setText(tableHargaJual);
        
        String tableJumlahJual = (String) defaultTableModel.getValueAt(i, 5);
        textjjual.setText(tableJumlahJual);
        
    }//GEN-LAST:event_tableinputMouseClicked

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beditActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormBarangKeluar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcetak;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btotal;
    private javax.swing.JComboBox<String> ccetak;
    private javax.swing.JComboBox<String> cnbarang;
    private javax.swing.JComboBox<String> cnpelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jumlah;
    private javax.swing.JPanel panelBayar;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelCetak;
    private javax.swing.JPanel panelFaktur;
    private javax.swing.JPanel panelHargaBeli;
    private javax.swing.JPanel panelJumlahJual;
    private javax.swing.JPanel panelKembalian;
    private javax.swing.JPanel panelKeuntungan;
    private javax.swing.JPanel panelNamaBarang;
    private javax.swing.JPanel panelPelanggan;
    private javax.swing.JPanel panelSatuan;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTabel1;
    private javax.swing.JPanel panelTanggalJual;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JPanel panelTombol1;
    private javax.swing.JPanel panelTombol2;
    private javax.swing.JPanel panelTotal;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textbayar;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textfaktur;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField texthjual;
    private javax.swing.JTextField textjjual;
    private javax.swing.JTextField textkembali;
    private javax.swing.JTextField textnsatuan;
    private javax.swing.JTextField texttbarang;
    private javax.swing.JTextField textuntung;
    // End of variables declaration//GEN-END:variables

    private void relasiCetak() {
        String rcetak = ("%" + ccetak.getSelectedItem().toString() + "%");
        String rbarang = ("%" + cnbarang.getSelectedItem().toString().substring(0, 6) + "%");
        String rsatuan = ("%" + textnsatuan.getText().substring(0, 6) + "%");
//        String reportSource;
        try {
//            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) connection;
//            reportSource = "src/report/report1.jrxml";
            HashMap parameter = new HashMap();
            parameter.put("fakturdano", rcetak);
            parameter.put("fakturkodebarang", rbarang);
            parameter.put("fakturkodesatuan", rsatuan);
            System.out.println(rcetak + " " + rbarang + " " + rsatuan);
            InputStream file = getClass().getResourceAsStream("/report/report1.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void kosong() {
        texthjual.setText("");
        textjjual.setText("");
        texttbarang.setText("");
        textbayar.setText("");
        textkembali.setText("");
        textuntung.setText("");
    }
}
