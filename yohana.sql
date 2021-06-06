-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2021 at 10:22 AM
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
('E00001', 'xdrhxdrhxdrh');

-- --------------------------------------------------------

--
-- Table structure for table `belibarang`
--

CREATE TABLE `belibarang` (
  `kodebeli` varchar(99) NOT NULL,
  `kodestock` varchar(12) NOT NULL,
  `kodesupplier` varchar(6) NOT NULL,
  `kodekategori` varchar(6) NOT NULL,
  `kodebarang` varchar(6) NOT NULL,
  `kodesatuan` varchar(6) NOT NULL,
  `jumlahbarang` int(12) NOT NULL,
  `hargabeli` int(12) NOT NULL,
  `total` int(12) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `belibarang`
--

INSERT INTO `belibarang` (`kodebeli`, `kodestock`, `kodesupplier`, `kodekategori`, `kodebarang`, `kodesatuan`, `jumlahbarang`, `hargabeli`, `total`, `tanggal`) VALUES
('C00001D00001E00001B00001F00001', 'G00001', 'C00001', 'D00001', 'E00001', 'B00001', 6, 457, 2742, '2021-06-06');

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
  `faktur` varchar(99) NOT NULL,
  `kodepelanggan` varchar(6) NOT NULL,
  `kodebarang` varchar(12) NOT NULL,
  `kodesatuan` varchar(6) NOT NULL,
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
('A00001E00001B00001801', 'A00001', 'E00001', 'B00001', 500, 5, 2500, 3000, 500, 215, '2021-06-06');

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
('D00001', 'xdrhxdrh');

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
('A00001', 'fctu', 'Laki-Laki', '864', 'ftcuftcu'),
('A00002', 'cft', 'Perempuan', '486', 'yfcj');

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
('B00001', 'rdyhrdh');

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
('C00001', 'cftj', 'fctjcft', '48647', '4876', 'drxghedsx', 'cftj', '2021-06-06');

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
