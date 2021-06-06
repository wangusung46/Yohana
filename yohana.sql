-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2021 at 08:10 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yohana`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kodebarang` varchar(6) NOT NULL,
  `namabarang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kodebarang`, `namabarang`) VALUES
('301', 'Zee Coco Bom'),
('302', 'Sweety Bronze Pants M'),
('303', 'Sweety Silver Pants M'),
('304', 'Confidance AD. Classic Night L'),
('305', 'Happy Nappy Smart Pant M');

-- --------------------------------------------------------

--
-- Table structure for table `belibarang`
--

CREATE TABLE `belibarang` (
  `kodebeli` varchar(18) NOT NULL,
  `kodestock` varchar(12) NOT NULL,
  `kodesupplier` varchar(3) NOT NULL,
  `kodekategori` varchar(3) NOT NULL,
  `kodebarang` varchar(3) NOT NULL,
  `kodesatuan` varchar(3) NOT NULL,
  `jumlahbarang` int(12) NOT NULL,
  `hargabeli` int(12) NOT NULL,
  `total` int(12) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `belibarang`
--

INSERT INTO `belibarang` (`kodebeli`, `kodestock`, `kodesupplier`, `kodekategori`, `kodebarang`, `kodesatuan`, `jumlahbarang`, `hargabeli`, `total`, `tanggal`) VALUES
('101201301402602', '70006', '101', '201', '301', '402', 35, 35000, 1225000, '2021-06-06');

-- --------------------------------------------------------

--
-- Table structure for table `datauntung`
--

CREATE TABLE `datauntung` (
  `kodeuntung` int(11) NOT NULL,
  `namabarang` int(11) NOT NULL,
  `jumlahjual` int(11) NOT NULL,
  `untung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `jualbarang`
--

CREATE TABLE `jualbarang` (
  `faktur` varchar(21) NOT NULL,
  `kodepelanggan` varchar(3) NOT NULL,
  `kodebarang` varchar(12) NOT NULL,
  `kodesatuan` varchar(3) NOT NULL,
  `hargajual` int(12) NOT NULL,
  `jumlahjual` int(4) NOT NULL,
  `hargajualtotal` int(12) NOT NULL,
  `bayar` int(12) NOT NULL,
  `kembali` int(12) NOT NULL,
  `untung` int(12) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jualbarang`
--

INSERT INTO `jualbarang` (`faktur`, `kodepelanggan`, `kodebarang`, `kodesatuan`, `hargajual`, `jumlahjual`, `hargajualtotal`, `bayar`, `kembali`, `untung`, `tanggal`) VALUES
('405301402801', '405', '301', '402', 36000, 3, 108000, 200000, 92000, 3000, '2021-06-06');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kodekategori` varchar(6) NOT NULL,
  `namakategori` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kodekategori`, `namakategori`) VALUES
('201', 'Minuman'),
('204', 'Popok'),
('203', 'Snack'),
('202', 'Tisu');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `jenis_kelamin`, `email`, `no_telp`, `alamat`) VALUES
('danosella', 'anugerah', 'Laki-laki', 'anugerah@gmail.com', '666660', 'Ambon');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kodepelanggan` varchar(6) NOT NULL,
  `namapelanggan` varchar(20) NOT NULL,
  `jeniskelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `nomortelepon` varchar(12) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kodepelanggan`, `namapelanggan`, `jeniskelamin`, `nomortelepon`, `alamat`) VALUES
('405', 'Ham', 'Laki-Laki', '085245987856', 'Tangerang'),
('501', 'Acilo', 'Laki-Laki', '085245963258', 'Tanjung Barat'),
('502', 'Mohan Pelupessy', 'Laki-Laki', '085265485632', 'Gedong'),
('503', 'Thia', 'Perempuan', '081569859623', 'Gedong'),
('504', 'Nur', 'Perempuan', '085265425698', 'Depok');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `username` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`username`, `nama`, `password`, `jenis_kelamin`, `no_telp`, `alamat`) VALUES
('wawan', 'Wawan', '123', 'Laki-Laki', '081248666660', 'Ambon'),
('yohana', 'Islam', 'yohana', 'Laki-Laki', '081248666660', 'Gedong');

-- --------------------------------------------------------

--
-- Table structure for table `satuan`
--

CREATE TABLE `satuan` (
  `kodesatuan` varchar(6) NOT NULL,
  `namasatuan` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `satuan`
--

INSERT INTO `satuan` (`kodesatuan`, `namasatuan`) VALUES
('406', 'Bungkus'),
('402', 'Kodi'),
('403', 'Lusin'),
('408', 'Pack'),
('407', 'Roll');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `kodestock` varchar(6) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `namasatuan` varchar(50) NOT NULL,
  `jumlahbarang` int(12) NOT NULL,
  `hargabeli` int(12) NOT NULL,
  `total` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`kodestock`, `namakategori`, `namabarang`, `namasatuan`, `jumlahbarang`, `hargabeli`, `total`) VALUES
('70001', 'Popok', 'Confidance AD. Classic Night L', 'Pack', 88, 42000, 4116000),
('70002', 'Minuman', 'Zee Coco Bom', 'Bungkus', 97, 12000, 1164000),
('70003', 'Tisu', 'Sweety Silver Pants M', 'Roll', 51, 26000, 1560000),
('70004', 'Minuman', 'Zee Coco Bom', 'Bungkus', 10, 12000, 120000),
('70005', 'Minuman', 'Zee Coco Bom', 'Bungkus', 78, 9000, 702000);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `kodesupplier` varchar(6) NOT NULL,
  `namasupplier` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `nomortelepon` varchar(12) NOT NULL,
  `norekening` varchar(12) NOT NULL,
  `bank` varchar(10) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`kodesupplier`, `namasupplier`, `alamat`, `nomortelepon`, `norekening`, `bank`, `email`, `tanggal`) VALUES
('101', 'PT KHANZA', 'Masohi', '081343052810', '856587541258', 'BNI', 'khanza@gmail.com', '2020-01-26'),
('102', 'PT LIEDY', 'Gedong', '082123299528', '856258654452', 'BRI', 'liedy@gmail.com', '2020-01-26'),
('103', 'PT GUSUNG', 'Banda', '085245698523', '523659874526', 'Danamon', 'gusung@gmail.com', '2020-01-27'),
('104', 'PT HARUKU', 'Depok', '085245968524', '852654789582', 'Mandiri', 'haruku@gmail.com', '2020-02-01'),
('105', 'PT KAREPESINA', 'Pemalang', '081280695371', '877118371910', 'BRI', 'karepesina@gmail.com', '2020-02-10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kodebarang`);

--
-- Indexes for table `belibarang`
--
ALTER TABLE `belibarang`
  ADD PRIMARY KEY (`kodebeli`);

--
-- Indexes for table `jualbarang`
--
ALTER TABLE `jualbarang`
  ADD PRIMARY KEY (`faktur`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kodekategori`),
  ADD UNIQUE KEY `namakategori` (`namakategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kodepelanggan`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `satuan`
--
ALTER TABLE `satuan`
  ADD PRIMARY KEY (`kodesatuan`),
  ADD UNIQUE KEY `namasatuan` (`namasatuan`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`kodestock`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kodesupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
