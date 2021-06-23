package barang;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormBarangMasuk extends javax.swing.JInternalFrame {

    private final DefaultTableModel defaultTableModel;
    private Connection connection = Koneksi.getKoneksi();
    private double tot, hb, jum;
    private String stock;

    public FormBarangMasuk() {
        initComponents();
        textkbeli.setEnabled(false);
        texttotal.setEnabled(false);
//        bsimpan.setEnabled(false);
        defaultTableModel = new DefaultTableModel();
        tableinput.setModel(defaultTableModel);
        defaultTableModel.addColumn("Kode Beli");
        defaultTableModel.addColumn("Kode Stock");
        defaultTableModel.addColumn("Nama Supplier");
        defaultTableModel.addColumn("Kategori Barang");
        defaultTableModel.addColumn("Nama Barang");
        defaultTableModel.addColumn("Satuan Barang");
        defaultTableModel.addColumn("Jumlah Barang");
        defaultTableModel.addColumn("Harga Beli");
        defaultTableModel.addColumn("Total");
        defaultTableModel.addColumn("Tanggal");
        loadData();
        tampilSupplier();
        tampilKategori();
        tampilBarang();
        tampilSatuan();
        kodeBeli();
        kodeStock();
        kosong();

    }

    private void existBarang() {
        String sql = "SELECT kodebarang, kodekategori, kodesatuan FROM belibarang WHERE kodebarang = '" + ckbarang.getSelectedItem().toString().substring(0, 6) + "' GROUP BY kodebarang";
        System.out.println(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            resultSet.next();
            if (resultSet.getString("kodekategori").equals(ckkategori.getSelectedItem().toString().subSequence(0, 6)) || resultSet.getString("kodesatuan").equals(cksatuan.getSelectedItem().toString().subSequence(0, 6))) {
                JOptionPane.showMessageDialog(null, "Data barang dengan kode barang " + ckbarang.getSelectedItem().toString().substring(0, 6) + " mempunyai satuan yang berbeda", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
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
        bhapus.setEnabled(false);
//        bedit.setEnabled(false);
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM belibarang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Statement statementRelation = connection.createStatement();
            ResultSet resultSetRelation;

            Object[] objects = new Object[10];
            while (resultSet.next()) {

                objects[0] = resultSet.getString("kodebeli");

                objects[1] = resultSet.getString("kodestock");

                resultSetRelation = statementRelation.executeQuery("SELECT namasupplier FROM supplier WHERE kodesupplier = '" + resultSet.getString("kodesupplier") + "'");
                resultSetRelation.next();
                objects[2] = resultSetRelation.getString("namasupplier");

                resultSetRelation = statementRelation.executeQuery("SELECT namakategori FROM kategori WHERE kodekategori = '" + resultSet.getString("kodekategori") + "'");
                resultSetRelation.next();
                objects[3] = resultSetRelation.getString("namakategori");

                resultSetRelation = statementRelation.executeQuery("SELECT namabarang FROM barang WHERE kodebarang = '" + resultSet.getString("kodebarang") + "'");
                resultSetRelation.next();
                objects[4] = resultSetRelation.getString("namabarang");

                resultSetRelation = statementRelation.executeQuery("SELECT namasatuan FROM satuan WHERE kodesatuan = '" + resultSet.getString("kodesatuan") + "'");
                resultSetRelation.next();
                objects[5] = resultSetRelation.getString("namasatuan");

                objects[6] = resultSet.getString("jumlahbarang");
                objects[7] = resultSet.getString("hargabeli");
                objects[8] = resultSet.getString("total");
                objects[9] = resultSet.getString("tanggal");
                defaultTableModel.addRow(objects);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }

    private void tampilSupplier() {
        try {
            String sql = "SELECT * FROM supplier ORDER by kodesupplier";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                cksupplier.addItem(resultSet.getString("kodesupplier") + " " + resultSet.getString("namasupplier"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKategori() {
        try {
            String sql = "SELECT * FROM kategori ORDER by kodekategori";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ckkategori.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilBarang() {
        try {
            String sql = "SELECT * FROM barang ORDER by kodebarang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ckbarang.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilSatuan() {
        try {
            String sql = "SELECT * FROM satuan ORDER by kodesatuan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cksatuan.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kodeBeli() {
        try {
            connection = Koneksi.getKoneksi();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM belibarang ORDER by kodebeli DESC";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String kbar = resultSet.getString("kodebeli").substring(1);
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
                kbar = "F" + Nol + AN;
                try {
                    textkbeli.setText(kbar);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                String kbar = "F00001";
                try {
                    textkbeli.setText(kbar);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kodeStock() {
        try {
            connection = Koneksi.getKoneksi();
            String sqlKodeStock = "SELECT * FROM belibarang ORDER by kodestock DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSetKodeStock = statement.executeQuery(sqlKodeStock);

            if (resultSetKodeStock.next()) {
                String kbar = resultSetKodeStock.getString("kodestock").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";
                switch (AN.length()) {
                    case 1:
                        Nol = "0000";
                        break;
                    case 2:
                        Nol = "000";
                        break;
                    case 3:
                        Nol = "00";
                        break;
                    case 4:
                        Nol = "0";
                        break;
                    case 5:
                        Nol = "";
                        break;
                    default:
                        break;
                }
                stock = "G" + Nol + AN;
            } else {
                stock = "G00001";
            }

            String sqlExist = "SELECT * FROM belibarang WHERE "
                    + "kodekategori = '" + ckkategori.getSelectedItem().toString().substring(0, 6) + "' AND "
                    + "kodebarang = '" + ckbarang.getSelectedItem().toString().substring(0, 6) + "' AND "
                    + "kodesatuan = '" + cksatuan.getSelectedItem().toString().substring(0, 6) + "' ";
            System.out.println(sqlExist);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExist);
            ResultSet resultSet = preparedStatement.executeQuery(sqlExist);
            String sama1 = "", sama2 = "", sama3 = "", sama4 = "";
            while (resultSet.next()) {
                sama1 = resultSet.getString("kodekategori");
                sama2 = resultSet.getString("kodebarang");
                sama3 = resultSet.getString("kodesatuan");
                sama4 = resultSet.getString("kodestock");
            }
            preparedStatement.close();
            resultSet.close();

            if (ckkategori.getSelectedItem().toString().substring(0, 6).equals(sama1)
                    && ckbarang.getSelectedItem().toString().substring(0, 6).equals(sama2)
                    && cksatuan.getSelectedItem().toString().substring(0, 6).equals(sama3)) {
                stock = sama4;
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kosong() {
        textjumlah.setText("");
        texthbeli.setText("");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelKode = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        textkbeli = new javax.swing.JTextField();
        panelSupplier = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cksupplier = new javax.swing.JComboBox<>();
        panelKategori = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        ckkategori = new javax.swing.JComboBox<>();
        panelNamaBarang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ckbarang = new javax.swing.JComboBox<>();
        panelSatuan = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        textjumlah = new javax.swing.JTextField();
        cksatuan = new javax.swing.JComboBox<>();
        panelJumlah = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        panelHargaBeli = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jhbeli = new javax.swing.JLabel();
        texthbeli = new javax.swing.JTextField();
        panelTotal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jtotal = new javax.swing.JLabel();
        texttotal = new javax.swing.JTextField();
        panelTombol = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        panelCari = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textcari = new javax.swing.JTextField();
        panelTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 0, 0));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setForeground(new java.awt.Color(0, 0, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM PEMASUKAN BARANG");
        jPanel7.add(jLabel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new java.awt.GridLayout(0, 2));

        panelKode.setLayout(new java.awt.GridLayout(1, 0));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Kode Pemasukan Barang");
        jLabel13.setPreferredSize(new java.awt.Dimension(50, 24));
        panelKode.add(jLabel13);

        textkbeli.setPreferredSize(new java.awt.Dimension(50, 24));
        textkbeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkbeliKeyTyped(evt);
            }
        });
        panelKode.add(textkbeli);

        jPanel2.add(panelKode);

        panelSupplier.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Supplier");
        jLabel8.setPreferredSize(new java.awt.Dimension(50, 24));
        panelSupplier.add(jLabel8);

        cksupplier.setPreferredSize(new java.awt.Dimension(50, 24));
        cksupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cksupplierActionPerformed(evt);
            }
        });
        panelSupplier.add(cksupplier);

        jPanel2.add(panelSupplier);

        panelKategori.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Kategori");
        jLabel9.setPreferredSize(new java.awt.Dimension(50, 24));
        panelKategori.add(jLabel9);

        ckkategori.setPreferredSize(new java.awt.Dimension(50, 24));
        ckkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckkategoriItemStateChanged(evt);
            }
        });
        ckkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckkategoriActionPerformed(evt);
            }
        });
        panelKategori.add(ckkategori);

        jPanel2.add(panelKategori);

        panelNamaBarang.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");
        jLabel3.setPreferredSize(new java.awt.Dimension(50, 24));
        panelNamaBarang.add(jLabel3);

        ckbarang.setPreferredSize(new java.awt.Dimension(50, 24));
        ckbarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckbarangItemStateChanged(evt);
            }
        });
        panelNamaBarang.add(ckbarang);

        jPanel2.add(panelNamaBarang);

        panelSatuan.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Satuan");
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 24));
        panelSatuan.add(jLabel5);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        textjumlah.setPreferredSize(new java.awt.Dimension(50, 24));
        textjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textjumlahKeyPressed(evt);
            }
        });
        jPanel6.add(textjumlah);

        cksatuan.setPreferredSize(new java.awt.Dimension(50, 24));
        cksatuan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cksatuanItemStateChanged(evt);
            }
        });
        cksatuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cksatuanMouseClicked(evt);
            }
        });
        cksatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cksatuanActionPerformed(evt);
            }
        });
        jPanel6.add(cksatuan);

        panelSatuan.add(jPanel6);

        jPanel2.add(panelSatuan);

        panelJumlah.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("TOTAL");
        jButton1.setPreferredSize(new java.awt.Dimension(50, 24));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelJumlah.add(jButton1);

        jPanel2.add(panelJumlah);

        panelHargaBeli.setLayout(new java.awt.GridLayout(1, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Harga Pemasukan");
        jLabel11.setPreferredSize(new java.awt.Dimension(50, 24));
        panelHargaBeli.add(jLabel11);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jhbeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jhbeli.setForeground(new java.awt.Color(255, 255, 255));
        jhbeli.setText("Rp");
        jhbeli.setPreferredSize(new java.awt.Dimension(50, 24));
        jPanel4.add(jhbeli);

        texthbeli.setPreferredSize(new java.awt.Dimension(50, 24));
        texthbeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                texthbeliKeyPressed(evt);
            }
        });
        jPanel4.add(texthbeli);

        panelHargaBeli.add(jPanel4);

        jPanel2.add(panelHargaBeli);

        panelTotal.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtotal.setForeground(new java.awt.Color(255, 255, 255));
        jtotal.setText("Rp");
        jtotal.setPreferredSize(new java.awt.Dimension(50, 24));
        jPanel3.add(jtotal);

        texttotal.setPreferredSize(new java.awt.Dimension(50, 24));
        texttotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                texttotalKeyPressed(evt);
            }
        });
        jPanel3.add(texttotal);

        panelTotal.add(jPanel3);

        jPanel2.add(panelTotal);

        panelTombol.setBackground(new java.awt.Color(0, 0, 0));
        panelTombol.setMinimumSize(new java.awt.Dimension(200, 24));
        panelTombol.setLayout(new java.awt.GridLayout(1, 0));

        bsimpan.setText("SIMPAN");
        bsimpan.setPreferredSize(new java.awt.Dimension(50, 24));
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

        panelCari.setBackground(new java.awt.Color(0, 0, 0));
        panelCari.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 24));
        jPanel5.add(jLabel2);

        textcari.setPreferredSize(new java.awt.Dimension(50, 24));
        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel5.add(textcari);

        panelCari.add(jPanel5);

        panelTabel.setBackground(new java.awt.Color(0, 0, 0));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        kodeStock();
        String sql = "SELECT kodebarang, kodekategori, kodesatuan FROM belibarang WHERE kodebarang = '" + ckbarang.getSelectedItem().toString().substring(0, 6) + "' GROUP BY kodebarang";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            resultSet.next();
            if (!resultSet.getString("kodekategori").equals(ckkategori.getSelectedItem().toString().subSequence(0, 6)) || !resultSet.getString("kodesatuan").equals(cksatuan.getSelectedItem().toString().subSequence(0, 6))) {
                JOptionPane.showMessageDialog(null, "Data barang dengan kode barang " + ckbarang.getSelectedItem().toString().substring(0, 6) + " mempunyai satuan yang berbeda", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } else if (textjumlah.getText().equals("")
                    || texthbeli.getText().equals("")
                    || texttotal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String tkodebeli = textkbeli.getText();
                String tkodestock = stock;
                String tsupplier = cksupplier.getSelectedItem().toString().substring(0, 6);
                String tkategori = ckkategori.getSelectedItem().toString().substring(0, 6);
                String tkodebarang = ckbarang.getSelectedItem().toString().substring(0, 6);
                String tsatuan = cksatuan.getSelectedItem().toString().substring(0, 6);
                String tjumlah = textjumlah.getText();
                String thargabeli = texthbeli.getText();
                String ttotal = texttotal.getText();
                try {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    String ttanggal = date.toString();
                    String sqlInsetBeliBarang = "INSERT INTO belibarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                    PreparedStatement preparedStatement;
                    preparedStatement = connection.prepareStatement(sqlInsetBeliBarang);
                    preparedStatement.setString(1, tkodebeli);
                    preparedStatement.setString(2, tkodestock);
                    preparedStatement.setString(3, tsupplier);
                    preparedStatement.setString(4, tkategori);
                    preparedStatement.setString(5, tkodebarang);
                    preparedStatement.setString(6, tsatuan);
                    preparedStatement.setString(7, tjumlah);
                    preparedStatement.setString(8, thargabeli);
                    preparedStatement.setString(9, ttotal);
                    preparedStatement.setString(10, ttanggal);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan pada tabel beli barang " + e);
                }

                try {
                    String sqlKodeStock = "SELECT kodestock, kodekategori, kodesatuan, kodebarang, SUM(jumlahbarang) FROM belibarang GROUP by kodestock, kodekategori, kodesatuan, kodebarang";
                    Statement statementKodeStock = connection.createStatement();
                    ResultSet resultSetKodeStock = statementKodeStock.executeQuery(sqlKodeStock);

                    while (resultSetKodeStock.next()) {
                        String kodeStock = resultSetKodeStock.getString("kodestock");
                        String kodeKategori = resultSetKodeStock.getString("kodekategori");
                        String kodeSatuan = resultSetKodeStock.getString("kodesatuan");
                        String kodeBarang = resultSetKodeStock.getString("kodebarang");
                        String jumlahBarang = resultSetKodeStock.getString("SUM(jumlahbarang)");
//                        PreparedStatement preparedStatement;
                        System.out.println(kodeStock + " " + kodeKategori + " " + kodeSatuan + " " + kodeBarang + " " + jumlahBarang);
                        System.out.println(stock);

                        String sqlKodeStockExist = "SELECT kodestock FROM stock WHERE kodestock = '" + kodeStock + "'";
                        Statement statementKodeStockExist = connection.createStatement();
                        ResultSet resultKodeStockExist = statementKodeStockExist.executeQuery(sqlKodeStockExist);
                        if (resultKodeStockExist.next() == false) {
                            preparedStatement = connection.prepareStatement(
                                    "INSERT INTO stock VALUES ("
                                    + "'" + stock + "', "
                                    + "'" + kodeKategori + "', "
                                    + "'" + kodeBarang + "', "
                                    + "'" + kodeSatuan + "', "
                                    + "'" + jumlahBarang + "')");
                            preparedStatement.executeUpdate();
                        } else {
                            preparedStatement = connection.prepareStatement(
                                    "UPDATE stock SET "
                                    + "kodekategori = '" + kodeKategori + "', "
                                    + "kodesatuan = '" + kodeSatuan + "', "
                                    + "kodebarang = '" + kodeBarang + "', "
                                    + "jumlahbarang = '" + jumlahBarang + "' "
                                    + "WHERE kodestock = '" + kodeStock + "'"
                            );
                            preparedStatement.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan pada tabel beli barang " + e);
                }
                loadData();
                kodeBeli();
                kosong();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }

    }//GEN-LAST:event_bsimpanActionPerformed
    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bsimpan.setEnabled(false);
//        bedit.setEnabled(true);
        bhapus.setEnabled(true);
//        ckbarang.setEnabled(false);
//        ckkategori.setEnabled(false);
//        cksatuan.setEnabled(false);
//        cksupplier.setEnabled(false);

        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }

        String tablekodebeli = (String) defaultTableModel.getValueAt(i, 0);
        textkbeli.setText(tablekodebeli);

        String tablekodestock = (String) defaultTableModel.getValueAt(i, 1);
        stock = tablekodestock;

        String tablesupplier = (String) defaultTableModel.getValueAt(i, 2);
        cksupplier.setSelectedItem(tablesupplier);

        String tablekategori = (String) defaultTableModel.getValueAt(i, 3);
        ckkategori.setSelectedItem(tablekategori);

        String tablenamabarang = (String) defaultTableModel.getValueAt(i, 4);
        cksatuan.setSelectedItem(tablenamabarang);

        String tablesatuan = (String) defaultTableModel.getValueAt(i, 5);
        ckkategori.setSelectedItem(tablesatuan);

    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        kodeStock();
        try {
            String sql = "DELETE FROM belibarang WHERE kodebeli='" + textkbeli.getText() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        try {
            String sqlKodeStock = "SELECT kodestock, kodekategori, kodesatuan, kodebarang, SUM(jumlahbarang) FROM belibarang GROUP by kodestock, kodekategori, kodesatuan, kodebarang";
            Statement statementKodeStock = connection.createStatement();
            ResultSet resultSetKodeStock = statementKodeStock.executeQuery(sqlKodeStock);

            while (resultSetKodeStock.next()) {
                String kodeStock = resultSetKodeStock.getString("kodestock");
                String kodeKategori = resultSetKodeStock.getString("kodekategori");
                String kodeSatuan = resultSetKodeStock.getString("kodesatuan");
                String kodeBarang = resultSetKodeStock.getString("kodebarang");
                String jumlahBarang = resultSetKodeStock.getString("SUM(jumlahbarang)");
                PreparedStatement preparedStatement;
                System.out.println(kodeStock + " " + kodeKategori + " " + kodeSatuan + " " + kodeBarang + " " + jumlahBarang);
                System.out.println(stock);

                String sqlKodeStockExist = "SELECT kodestock FROM stock WHERE kodestock = '" + kodeStock + "'";
                Statement statementKodeStockExist = connection.createStatement();
                ResultSet resultKodeStockExist = statementKodeStockExist.executeQuery(sqlKodeStockExist);
                if (resultKodeStockExist.next() == false) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO stock VALUES ("
                            + "'" + stock + "', "
                            + "'" + kodeKategori + "', "
                            + "'" + kodeSatuan + "', "
                            + "'" + kodeBarang + "', "
                            + "'" + jumlahBarang + "')");
                    preparedStatement.executeUpdate();
                } else {
                    preparedStatement = connection.prepareStatement(
                            "UPDATE stock SET "
                            + "kodekategori = '" + kodeKategori + "', "
                            + "kodesatuan = '" + kodeSatuan + "', "
                            + "kodebarang = '" + kodeBarang + "', "
                            + "jumlahbarang = '" + jumlahBarang + "' "
                            + "WHERE kodestock = '" + kodeStock + "'"
                    );
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan pada tabel beli barang " + e);
        }
        loadData();
        kodeBeli();
        kosong();
    }//GEN-LAST:event_bhapusActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();

        try {
            connection = Koneksi.getKoneksi();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM belibarang WHERE "
                    + "kodebeli LIKE '%" + textcari.getText()
                    + "%' OR kodestock LIKE'%" + textcari.getText()
                    + "%' OR kodesupplier LIKE'%" + textcari.getText()
                    + "%' OR kodekategori LIKE'%" + textcari.getText()
                    + "%' OR kodebarang LIKE'" + textcari.getText()
                    + "%' OR kodesatuan LIKE'%" + textcari.getText()
                    + "%' OR jumlah LIKE'%" + textcari.getText()
                    + "%' OR hargabeli LIKE'%" + textcari.getText()
                    + "%' OR total LIKE'%" + textcari.getText()
                    + "%' OR tanggal LIKE'%" + textcari.getText() + "%'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Object[] objects = new Object[10];
                objects[0] = resultSet.getString("kodebeli");
                objects[1] = resultSet.getString("kodestock");
                objects[2] = resultSet.getString("kodesupplier");
                objects[3] = resultSet.getString("kodekategori");
                objects[4] = resultSet.getString("kodebarang");
                objects[5] = resultSet.getString("kodesatuan");
                objects[6] = resultSet.getString("jumlahbarang");
                objects[7] = resultSet.getString("hargabeli");
                objects[8] = resultSet.getString("total");
                objects[9] = resultSet.getString("tanggal");

                defaultTableModel.addRow(objects);
            }
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textkbeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkbeliKeyTyped
    }//GEN-LAST:event_textkbeliKeyTyped

    private void cksatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksatuanActionPerformed

    }//GEN-LAST:event_cksatuanActionPerformed

    private void cksatuanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cksatuanItemStateChanged
//        tampilKodeSatuan();
        kodeBeli();
    }//GEN-LAST:event_cksatuanItemStateChanged

    private void cksatuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cksatuanMouseClicked

    }//GEN-LAST:event_cksatuanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hb = Integer.parseInt(texthbeli.getText());
        jum = Integer.parseInt(textjumlah.getText());
        tot = hb * jum;
        String total = String.valueOf(tot);
        texttotal.setText(total);
//        if(textjumlah.getText() != null && texthbeli.getText() != null){
//            bsimpan.setEnabled(true);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ckkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckkategoriItemStateChanged
//        tampilKodeKategori();
        kodeBeli();
    }//GEN-LAST:event_ckkategoriItemStateChanged

    private void ckkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckkategoriActionPerformed

    }//GEN-LAST:event_ckkategoriActionPerformed

    private void ckbarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckbarangItemStateChanged
//        tampilKodeBarang();
        kodeBeli();
    }//GEN-LAST:event_ckbarangItemStateChanged

    private void cksupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksupplierActionPerformed
//        tampilKodeSupplier();
        kodeBeli();
    }//GEN-LAST:event_cksupplierActionPerformed

    private void textjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textjumlahKeyPressed
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_textjumlahKeyPressed

    private void texthbeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texthbeliKeyPressed
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_texthbeliKeyPressed

    private void texttotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texttotalKeyPressed
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_texttotalKeyPressed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormBarangMasuk().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> ckbarang;
    private javax.swing.JComboBox<String> ckkategori;
    private javax.swing.JComboBox<String> cksatuan;
    private javax.swing.JComboBox<String> cksupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jhbeli;
    private javax.swing.JLabel jtotal;
    private javax.swing.JPanel panelCari;
    private javax.swing.JPanel panelHargaBeli;
    private javax.swing.JPanel panelJumlah;
    private javax.swing.JPanel panelKategori;
    private javax.swing.JPanel panelKode;
    private javax.swing.JPanel panelNamaBarang;
    private javax.swing.JPanel panelSatuan;
    private javax.swing.JPanel panelSupplier;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JPanel panelTotal;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField textjumlah;
    private javax.swing.JTextField textkbeli;
    private javax.swing.JTextField texttotal;
    // End of variables declaration//GEN-END:variables
//kodeStock();
//        if (textjumlah.getText().equals("")
//                || texthbeli.getText().equals("")
//                || texttotal.getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            try {
//                connection = Koneksi.getKoneksi();
//                String sql = "UPDATE belibarang SET kodestock=?, kodesupplier=?, kodekategori=?, kodebarang=?, kodesatuan=?, jumlahbarang=?, hargabeli=?, total=? where kodebeli='" + textkbeli.getText() + "'";
//                PreparedStatement preparedStatement;
//                preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, stock);
//                preparedStatement.setString(2, cksupplier.getSelectedItem().toString().substring(0, 6));
//                preparedStatement.setString(3, ckkategori.getSelectedItem().toString().substring(0, 6));
//                preparedStatement.setString(4, ckbarang.getSelectedItem().toString().substring(0, 6));
//                preparedStatement.setString(5, cksatuan.getSelectedItem().toString().substring(0, 6));
//                preparedStatement.setString(6, textjumlah.getText());
//                preparedStatement.setString(7, texthbeli.getText());
//                preparedStatement.setString(8, texttotal.getText());
//                preparedStatement.executeUpdate();
//                textkbeli.requestFocus();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
//            }
//
//            try {
//                String sqlKodeStock = "SELECT kodestock, kodekategori, kodesatuan, kodebarang, SUM(jumlahbarang) FROM belibarang GROUP by kodestock, kodekategori, kodesatuan, kodebarang";
//                Statement statementKodeStock = connection.createStatement();
//                ResultSet resultSetKodeStock = statementKodeStock.executeQuery(sqlKodeStock);
//
//                while (resultSetKodeStock.next()) {
//                    String kodeStock = resultSetKodeStock.getString("kodestock");
//                    String kodeKategori = resultSetKodeStock.getString("kodekategori");
//                    String kodeSatuan = resultSetKodeStock.getString("kodesatuan");
//                    String kodeBarang = resultSetKodeStock.getString("kodebarang");
//                    String jumlahBarang = resultSetKodeStock.getString("SUM(jumlahbarang)");
//                    PreparedStatement preparedStatement;
//                    System.out.println(kodeStock + " " + kodeKategori + " " + kodeSatuan + " " + kodeBarang + " " + jumlahBarang);
//                    System.out.println(stock);
//
//                    String sqlKodeStockExist = "SELECT kodestock FROM stock WHERE kodestock = '" + kodeStock + "'";
//                    Statement statementKodeStockExist = connection.createStatement();
//                    ResultSet resultKodeStockExist = statementKodeStockExist.executeQuery(sqlKodeStockExist);
//                    if (resultKodeStockExist.next() == false) {
//                        preparedStatement = connection.prepareStatement(
//                                "INSERT INTO stock VALUES ("
//                                + "'" + stock + "', "
//                                + "'" + kodeKategori + "', "
//                                + "'" + kodeSatuan + "', "
//                                + "'" + kodeBarang + "', "
//                                + "'" + jumlahBarang + "')");
//                        preparedStatement.executeUpdate();
//                    } else {
//                        preparedStatement = connection.prepareStatement(
//                                "UPDATE stock SET "
//                                + "kodekategori = '" + kodeKategori + "', "
//                                + "kodesatuan = '" + kodeSatuan + "', "
//                                + "kodebarang = '" + kodeBarang + "', "
//                                + "jumlahbarang = '" + jumlahBarang + "' "
//                                + "WHERE kodestock = '" + kodeStock + "'"
//                        );
//                        preparedStatement.executeUpdate();
//                    }
//                }
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Data gagal disimpan pada tabel beli barang " + e);
//            }
//            loadData();
//            kodeBeli();
//            kosong();
//        }
}
