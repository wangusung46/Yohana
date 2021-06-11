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
    private String stock, kategori, satuan, supplier, barang;

    public FormBarangMasuk() {
        initComponents();
        textkbeli.setEnabled(false);
        texttotal.setEnabled(false);
        defaultTableModel = new DefaultTableModel();
        tableinput.setModel(defaultTableModel);
        defaultTableModel.addColumn("Kode Beli");
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
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM belibarang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Statement statementRelation = connection.createStatement();
            ResultSet resultSetRelation;

            while (resultSet.next()) {
                Object[] objects = new Object[10];
                objects[0] = resultSet.getString("kodebeli");

                resultSetRelation = statementRelation.executeQuery("SELECT namasupplier FROM supplier WHERE kodesupplier = '" + resultSet.getString("kodesupplier") + "'");
                resultSetRelation.next();
                objects[1] = resultSetRelation.getString("namasupplier");

                resultSetRelation = statementRelation.executeQuery("SELECT namakategori FROM kategori WHERE kodekategori = '" + resultSet.getString("kodekategori") + "'");
                resultSetRelation.next();
                objects[2] = resultSetRelation.getString("namakategori");

                resultSetRelation = statementRelation.executeQuery("SELECT namabarang FROM barang WHERE kodebarang = '" + resultSet.getString("kodebarang") + "'");
                resultSetRelation.next();
                objects[3] = resultSetRelation.getString("namabarang");

                resultSetRelation = statementRelation.executeQuery("SELECT namasatuan FROM satuan WHERE kodesatuan = '" + resultSet.getString("kodesatuan") + "'");
                resultSetRelation.next();
                objects[4] = resultSetRelation.getString("namasatuan");

                objects[5] = resultSet.getString("jumlahbarang");
                objects[6] = resultSet.getString("hargabeli");
                objects[7] = resultSet.getString("total");
                objects[8] = resultSet.getString("tanggal");
                defaultTableModel.addRow(objects);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }

    private void tampilSupplier() {
        try {
            String sql = "SELECT * FROM supplier ORDER by kodesupplier";
            PreparedStatement pst = connection.prepareStatement(sql);
            try (ResultSet resultSet = pst.executeQuery(sql)) {
                System.out.println(sql);
                while (resultSet.next()) {
                    cksupplier.addItem(resultSet.getString("kodesupplier") + " " + resultSet.getString("namasupplier"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKategori() {
        try {
            String sql = "SELECT * FROM kategori ORDER by kodekategori";
            PreparedStatement pst = connection.prepareStatement(sql);
            try (ResultSet resultSet = pst.executeQuery(sql)) {
                while (resultSet.next()) {
                    ckkategori.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilBarang() {
        try {
            String sql = "SELECT * FROM barang ORDER by kodebarang";
            Statement st = connection.createStatement();
            try (ResultSet resultSet = st.executeQuery(sql)) {
                while (resultSet.next()) {
                    ckbarang.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilSatuan() {
        try {
            String sql = "SELECT * FROM satuan ORDER by kodesatuan";
            Statement st = connection.createStatement();
            try (ResultSet resultSet = st.executeQuery(sql)) {
                while (resultSet.next()) {
                    cksatuan.addItem(resultSet.getString(1) + " " + resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kodeBeli() {
        try {
            connection = Koneksi.getKoneksi();
            Statement st = connection.createStatement();

            String sql = "SELECT * FROM belibarang ORDER by kodebeli DESC";
            ResultSet resultSet = st.executeQuery(sql);

            if (resultSet.next()) {
                String kbar = resultSet.getString("kodebeli").substring(1);
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
                kbar = "F" + Nol + AN;
                String rsupplier = supplier;
                System.out.println(rsupplier);
                String rkategori = kategori;
                String rbarang = barang;
                String rsatuan = satuan;
                try {
                    textkbeli.setText(kbar);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                String kbar = "F00001";
                String rsupplier = supplier;
                String rkategori = kategori;
                String rbarang = barang;
                String rsatuan = satuan;
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
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM stock ORDER BY kodestock DESC";
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                String kbar = resultSet.getString("kodestock").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";
                System.out.println(kbar + " " + AN + " " + Nol);
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
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeSupplier() {
        try {
            String rsupplier = cksupplier.getSelectedItem().toString().substring(0, 6);
            System.out.println(rsupplier);
            String sql = "SELECT * FROM supplier Where kodesupplier LIKE '" + rsupplier + "' ORDER BY kodesupplier ASC";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery(sql);

            resultSet.absolute(1);
            supplier = resultSet.getString("kodesupplier");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeKategori() {
        try {
            String rkategori = ckkategori.getSelectedItem().toString().substring(0, 6);
            System.out.println(rkategori);
            String sql = "SELECT * FROM kategori WHERE kodekategori LIKE '" + rkategori + "' ORDER BY kodekategori ASC";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery(sql);
            resultSet.absolute(1);
            rkategori = resultSet.getString("kodekategori");
            kategori = rkategori;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeBarang() {
        try {
            String rbarang = ckbarang.getSelectedItem().toString().substring(0, 6);
            System.out.println(rbarang);
            String sql = "SELECT * FROM barang WHERE kodebarang LIKE '" + rbarang + "' ORDER BY kodebarang ASC";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery(sql);
            resultSet.absolute(1);
            rbarang = resultSet.getString("kodebarang");
            barang = rbarang;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeSatuan() {
        try {
            String rsatuan = cksatuan.getSelectedItem().toString().substring(0, 6);
            System.out.println(rsatuan);
            String sql = "SELECT * FROM satuan WHERE kodesatuan LIKE '" + rsatuan + "' ORDER BY kodesatuan ASC";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery(sql);
            resultSet.absolute(1);
            rsatuan = resultSet.getString("kodesatuan");
            satuan = rsatuan;
        } catch (SQLException e) {
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
        bedit = new javax.swing.JButton();
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

        bedit.setText("EDIT");
        bedit.setPreferredSize(new java.awt.Dimension(50, 24));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        panelTombol.add(bedit);

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
        if (textjumlah.getText().equals("")
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
                System.out.println(date);
                String ttanggal = date.toString();
                String sql = "INSERT INTO belibarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst;
                pst = connection.prepareStatement(sql);
                pst.setString(1, tkodebeli);
                pst.setString(2, tkodestock);
                pst.setString(3, tsupplier);
                pst.setString(4, tkategori);
                pst.setString(5, tkodebarang);
                pst.setString(6, tsatuan);
                pst.setString(7, tjumlah);
                pst.setString(8, thargabeli);
                pst.setString(9, ttotal);
                pst.setString(10, ttanggal);
                pst.executeUpdate();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

//            try {
//                Connection c = Koneksi.getKoneksi();
//                String sql = "INSERT INTO stock VALUES (?, ?, ?, ?, ?, ?, ?)";
//                PreparedStatement pst;
//                pst = c.prepareStatement(sql);
//                pst.setString(1, tkodestock);
//                pst.setString(2, tkategori);
//                pst.setString(3, tkodebarang);
//                pst.setString(4, tsatuan);
//                pst.setString(5, tjumlah);
//                pst.setString(6, thargabeli);
//                pst.setString(7, ttotal);
//                pst.executeUpdate();
//                pst.close();
//                JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, e);
//            } 
            loadData();
            kodeBeli();
            kosong();
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

        String tablekodebeli = (String) defaultTableModel.getValueAt(i, 0);
        textkbeli.setText(tablekodebeli);

//        String tablekodestock = (String) defaultTableModel.getValueAt(i, 1);
//        stock = tablekodestock;
        String tablesupplier = (String) defaultTableModel.getValueAt(i, 1);
        cksupplier.setSelectedItem(tablesupplier);

        String tablekategori = (String) defaultTableModel.getValueAt(i, 2);
        ckkategori.setSelectedItem(tablekategori);

        String tablenamabarang = (String) defaultTableModel.getValueAt(i, 3);
        cksatuan.setSelectedItem(tablenamabarang);

        String tablesatuan = (String) defaultTableModel.getValueAt(i, 4);
        ckkategori.setSelectedItem(tablesatuan);

    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql = "DELETE FROM belibarang WHERE kodebeli='" + textkbeli.getText() + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
//        try {
//            String sql = "DELETE FROM stock WHERE kodestock='" + stock + "'";
//            connection = (Connection) Koneksi.getKoneksi();
//            PreparedStatement pst = connection.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
        loadData();
        kodeBeli();
        kosong();
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (textjumlah.getText().equals("")
                || texthbeli.getText().equals("")
                || texttotal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                connection = Koneksi.getKoneksi();
                String sql = "UPDATE belibarang SET kodestock=?, kodesupplier=?, kodekategori=?, kodebarang=?, kodesatuan=?, jumlahbarang=?, hargabeli=?, total=? where kodebeli='" + textkbeli.getText() + "'";
                PreparedStatement pst;
                pst = connection.prepareStatement(sql);
                pst.setString(1, stock);
                pst.setString(2, cksupplier.getSelectedItem().toString().substring(0, 6));
                pst.setString(3, ckkategori.getSelectedItem().toString().substring(0, 6));
                pst.setString(4, ckbarang.getSelectedItem().toString().substring(0, 6));
                pst.setString(5, cksatuan.getSelectedItem().toString().substring(0, 6));
                pst.setString(6, textjumlah.getText());
                pst.setString(7, texthbeli.getText());
                pst.setString(8, texttotal.getText());
                pst.executeUpdate();
                textkbeli.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
            }
//            try {
//                connection = Koneksi.getKoneksi();
//                String sql = "UPDATE stock SET kodekategori=?, kodebarang=?, kodesatuan=?, jumlahbarang=?, hargabeli=?, total=? WHERE kodestock='" + stock + "'";
//                PreparedStatement pst;
//                pst = connection.prepareStatement(sql);
//                pst.setString(1, ckkategori.getSelectedItem().toString().substring(0, 6));
//                pst.setString(2, ckbarang.getSelectedItem().toString().substring(0, 6));
//                pst.setString(3, cksatuan.getSelectedItem().toString().substring(0, 6));
//                pst.setString(4, textjumlah.getText());
//                pst.setString(5, texthbeli.getText());
//                pst.setString(6, texttotal.getText());
//                pst.executeUpdate();
//                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIRUBAH", "PT MULIA JAYA TEXTILE", JOptionPane.INFORMATION_MESSAGE);
//                textkbeli.requestFocus();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            } 
            loadData();
            kodeBeli();
            kosong();
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();

        try {
            connection = Koneksi.getKoneksi();
            try (Statement st = connection.createStatement()) {
                String sql = "SELECT * FROM belibarang WHERE "
                        + "kodebeli LIKE '%" + textcari.getText()
                        + "%' OR kodestock LIKE'%" + textcari.getText()
                        + "%' OR namasupplier LIKE'%" + textcari.getText()
                        + "%' OR namakategori LIKE'%" + textcari.getText()
                        + "%' OR namabarang LIKE'" + textcari.getText()
                        + "%' OR namasatuan LIKE'%" + textcari.getText()
                        + "%' OR jumlah LIKE'%" + textcari.getText()
                        + "%' OR hargabeli LIKE'%" + textcari.getText()
                        + "%' OR total LIKE'%" + textcari.getText()
                        + "%' OR tanggal LIKE'%" + textcari.getText() + "%'";
                try (ResultSet rstest = st.executeQuery(sql)) {
                    while (rstest.next()) {
                        Object[] objects = new Object[10];
                        objects[0] = rstest.getString("kodebeli");
                        objects[1] = rstest.getString("kodestock");
                        objects[2] = rstest.getString("namasupplier");
                        objects[3] = rstest.getString("namakategori");
                        objects[4] = rstest.getString("namabarang");
                        objects[5] = rstest.getString("namasatuan");
                        objects[6] = rstest.getString("jumlahbarang");
                        objects[7] = rstest.getString("hargabeli");
                        objects[8] = rstest.getString("total");
                        objects[9] = rstest.getString("tanggal");

                        defaultTableModel.addRow(objects);
                    }
                }
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
        tampilKodeSatuan();
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ckkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckkategoriItemStateChanged
        tampilKodeKategori();
        kodeBeli();
    }//GEN-LAST:event_ckkategoriItemStateChanged

    private void ckkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckkategoriActionPerformed

    }//GEN-LAST:event_ckkategoriActionPerformed

    private void ckbarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckbarangItemStateChanged
        tampilKodeBarang();
        kodeBeli();
    }//GEN-LAST:event_ckbarangItemStateChanged

    private void cksupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksupplierActionPerformed
        tampilKodeSupplier();
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
    private javax.swing.JButton bedit;
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

}
