package barang;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormBarangMasuk extends javax.swing.JInternalFrame {

    private final DefaultTableModel model;
    private Connection conn = Koneksi.getKoneksi();
    private double tot, hb, jum;
    private String stock, kategori, satuan, supplier, barang;

    public FormBarangMasuk() {
        initComponents();
        textkbeli.setEnabled(false);
        texttotal.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Beli");
        model.addColumn("Kode Stock");
        model.addColumn("Nama Supplier");
        model.addColumn("Kategori Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Total");
        model.addColumn("Tanggal");
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
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = Koneksi.getKoneksi();
            Statement st = c.createStatement();
            String sql = "SELECT * FROM belibarang";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Object[] o = new Object[10];
                    o[0] = rs.getString("kodebeli");
                    o[1] = rs.getString("kodestock");
                    o[2] = rs.getString("kodesupplier");
                    o[3] = rs.getString("kodekategori");
                    o[4] = rs.getString("kodebarang");
                    o[5] = rs.getString("kodesatuan");
                    o[6] = rs.getString("jumlahbarang");
                    o[7] = rs.getString("hargabeli");
                    o[8] = rs.getString("total");
                    o[9] = rs.getString("tanggal");
                    model.addRow(o);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }

    private void tampilSupplier() {
        try {
            String sql = "SELECT * FROM supplier ORDER by kodesupplier";
            PreparedStatement pst = conn.prepareStatement(sql);
            try (ResultSet rs = pst.executeQuery(sql)) {
                System.out.println(sql);
                while (rs.next()) {
                    cksupplier.addItem(rs.getString("kodesupplier") + " " + rs.getString("namasupplier"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKategori() {
        try {
            String sql = "SELECT * FROM kategori ORDER by kodekategori";
            PreparedStatement pst = conn.prepareStatement(sql);
            try (ResultSet rs = pst.executeQuery(sql)) {
                while (rs.next()) {
                    ckkategori.addItem(rs.getString(1) + " " + rs.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilBarang() {
        try {
            String sql = "SELECT * FROM barang ORDER by kodebarang";
            Statement st = conn.createStatement();
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    ckbarang.addItem(rs.getString(1) + " " + rs.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilSatuan() {
        try {
            String sql = "SELECT * FROM satuan ORDER by kodesatuan";
            Statement st = conn.createStatement();
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    cksatuan.addItem(rs.getString(1) + " " + rs.getString(2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kodeBeli() {
        try {
            conn = Koneksi.getKoneksi();
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM belibarang ORDER by kodebeli DESC";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                String kbar = rs.getString("kodebeli").substring(13);
                System.out.println(kbar);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "0";
                } else if (AN.length() == 2) {
                    Nol = "";
                }
                kbar = "6" + Nol + AN;
                String rsupplier = supplier;
                System.out.println(rsupplier);
                String rkategori = kategori;
                String rbarang = barang;
                String rsatuan = satuan;
                try {
                    textkbeli.setText(rsupplier + rkategori + rbarang + rsatuan + kbar);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                String kbar = "601";
                String rsupplier = supplier;
                String rkategori = kategori;
                String rbarang = barang;
                String rsatuan = satuan;
                try {
                    textkbeli.setText(rsupplier + rkategori + rbarang + rsatuan + kbar);
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
            conn = Koneksi.getKoneksi();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM stock ORDER BY kodestock DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kbar = rs.getString("kodestock").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";
                System.out.println(kbar + " " + AN + " " + Nol);
                switch (AN.length()) {
                    case 1:
                        Nol = "000";
                        break;
                    case 2:
                        Nol = "00";
                        break;
                    case 3:
                        Nol = "0";
                        break;
                    case 4:
                        Nol = "";
                        break;
                    default:
                        break;
                }
                stock = "7" + Nol + AN;
            } else {
                stock = "70001";
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeSupplier() {
        try {
            String rsupplier = cksupplier.getSelectedItem().toString().substring(0, 3);
            System.out.println(rsupplier);
            String sql = "SELECT * FROM supplier Where kodesupplier LIKE '" + rsupplier + "' ORDER BY kodesupplier ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            rs.absolute(1);
            supplier = rs.getString("kodesupplier");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeKategori() {
        try {
            String rkategori = ckkategori.getSelectedItem().toString().substring(0, 3);
            System.out.println(rkategori);
            String sql = "SELECT * FROM kategori WHERE kodekategori LIKE '" + rkategori + "' ORDER BY kodekategori ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.absolute(1);
            rkategori = rs.getString("kodekategori");
            kategori = rkategori;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeBarang() {
        try {
            String rbarang = ckbarang.getSelectedItem().toString().substring(0, 3);
            System.out.println(rbarang);
            String sql = "SELECT * FROM barang WHERE kodebarang LIKE '" + rbarang + "' ORDER BY kodebarang ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.absolute(1);
            rbarang = rs.getString("kodebarang");
            barang = rbarang;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilKodeSatuan() {
        try {
            String rsatuan = cksatuan.getSelectedItem().toString().substring(0, 3);
            System.out.println(rsatuan);
            String sql = "SELECT * FROM satuan WHERE kodesatuan LIKE '" + rsatuan + "' ORDER BY kodesatuan ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.absolute(1);
            rsatuan = rs.getString("kodesatuan");
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
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
            String tsupplier = cksupplier.getSelectedItem().toString().substring(0, 3);
            String tkategori = ckkategori.getSelectedItem().toString().substring(0, 3);
            String tkodebarang = ckbarang.getSelectedItem().toString().substring(0, 3);
            String tsatuan = cksatuan.getSelectedItem().toString().substring(0, 3);
            String tjumlah = textjumlah.getText();
            String thargabeli = texthbeli.getText();
            String ttotal = texttotal.getText();
            try {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                System.out.println(date);
                String ttanggal = date.toString();
                Connection c = Koneksi.getKoneksi();
                String sql = "INSERT INTO belibarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst;
                pst = c.prepareStatement(sql);
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

        String tablesupplier = (String) model.getValueAt(i, 2);
        cksupplier.setSelectedItem(tablesupplier);

        String tablekategori = (String) model.getValueAt(i, 3);
        ckkategori.setSelectedItem(tablekategori);

        String tablenamabarang = (String) model.getValueAt(i, 4);
        cksatuan.setSelectedItem(tablenamabarang);

        String tablesatuan = (String) model.getValueAt(i, 5);
        ckkategori.setSelectedItem(tablesatuan);

        String tablejumlah = (String) model.getValueAt(i, 6);
        textjumlah.setText(tablejumlah);

        String tablehargabeli = (String) model.getValueAt(i, 7);
        texthbeli.setText(tablehargabeli);

        String tabletotal = (String) model.getValueAt(i, 8);
        texttotal.setText(tabletotal);

        String tablekodebeli = (String) model.getValueAt(i, 0);
        textkbeli.setText(tablekodebeli);
        textkbeli.setEnabled(false);

        String tablekodestock = (String) model.getValueAt(i, 1);
        stock = tablekodestock;

    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql = "DELETE FROM belibarang WHERE kodebeli='" + textkbeli.getText() + "'";
            conn = (Connection) Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
//        try {
//            String sql = "DELETE FROM stock WHERE kodestock='" + stock + "'";
//            conn = (Connection) Koneksi.getKoneksi();
//            PreparedStatement pst = conn.prepareStatement(sql);
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
                conn = Koneksi.getKoneksi();
                String sql = "UPDATE belibarang SET kodestock=?, kodesupplier=?, kodekategori=?, kodebarang=?, kodesatuan=?, jumlahbarang=?, hargabeli=?, total=? where kodebeli='" + textkbeli.getText() + "'";
                PreparedStatement pst;
                pst = conn.prepareStatement(sql);
                pst.setString(1, stock);
                pst.setString(2, cksupplier.getSelectedItem().toString().substring(0, 3));
                pst.setString(3, ckkategori.getSelectedItem().toString().substring(0, 3));
                pst.setString(4, ckbarang.getSelectedItem().toString().substring(0, 3));
                pst.setString(5, cksatuan.getSelectedItem().toString().substring(0, 3));
                pst.setString(6, textjumlah.getText());
                pst.setString(7, texthbeli.getText());
                pst.setString(8, texttotal.getText());
                pst.executeUpdate();
                textkbeli.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
            }
//            try {
//                conn = Koneksi.getKoneksi();
//                String sql = "UPDATE stock SET kodekategori=?, kodebarang=?, kodesatuan=?, jumlahbarang=?, hargabeli=?, total=? WHERE kodestock='" + stock + "'";
//                PreparedStatement pst;
//                pst = conn.prepareStatement(sql);
//                pst.setString(1, ckkategori.getSelectedItem().toString().substring(0, 3));
//                pst.setString(2, ckbarang.getSelectedItem().toString().substring(0, 3));
//                pst.setString(3, cksatuan.getSelectedItem().toString().substring(0, 3));
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
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            conn = Koneksi.getKoneksi();
            try (Statement st = conn.createStatement()) {
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
                        Object[] o = new Object[10];
                        o[0] = rstest.getString("kodebeli");
                        o[1] = rstest.getString("kodestock");
                        o[2] = rstest.getString("namasupplier");
                        o[3] = rstest.getString("namakategori");
                        o[4] = rstest.getString("namabarang");
                        o[5] = rstest.getString("namasatuan");
                        o[6] = rstest.getString("jumlahbarang");
                        o[7] = rstest.getString("hargabeli");
                        o[8] = rstest.getString("total");
                        o[9] = rstest.getString("tanggal");

                        model.addRow(o);
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
