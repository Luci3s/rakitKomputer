-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_jual
DROP DATABASE IF EXISTS `db_jual`;
CREATE DATABASE IF NOT EXISTS `db_jual` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_jual`;

-- Dumping structure for table db_jual.barang
DROP TABLE IF EXISTS `barang`;
CREATE TABLE IF NOT EXISTS `barang` (
  `kodebrg` varchar(10) NOT NULL,
  `namabrg` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `tarif` double DEFAULT NULL,
  `gambar` varchar(100) DEFAULT NULL,
  `stok` int DEFAULT NULL,
  PRIMARY KEY (`kodebrg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table db_jual.barang: ~9 rows (approximately)
DELETE FROM `barang`;
INSERT INTO `barang` (`kodebrg`, `namabrg`, `tarif`, `gambar`, `stok`) VALUES
	('B00001', 'VGA GTX 1660 Ti', 3500000, 'src\\images\\GTX 1660 Ti.png', 2),
	('B00002', 'Motherboard ASRock B550M Pro SE - AM4', 1500000, 'src\\images\\mobo asrock b550m se am4.jpg', 1),
	('B00003', 'Ryzen 7 3700x', 5960000, 'src\\images\\Ryzen 7 3700x.jpg', 5),
	('B00004', 'Ram Corsair Vengeance RGB Rs 8x2 DDR4', 1030000, 'src\\images\\ram corsair vengeance 8x2.jpg', 5),
	('B00005', 'Power Supply Aerocool LUX RGB 80+ Bronze 650W', 695000, 'src\\images\\Aerocool LUX RGB 80+ Bronze.jpg', 2),
	('B00006', 'Hardisk1TB Osmous Red 7200 RPM', 417000, 'src\\images\\Hardisk1TB Osmous Red 7200 RPM.jpg', 5),
	('B00007', 'SSD 512GB RX7 SATA', 364000, 'src\\images\\SSD 512GB RX7 SATA.jpg', 5),
	('B00008', 'Casing PC Gaming Aula MZ01 mATX - White', 488000, 'src\\images\\Casing PC Gaming Aula MZ01 mATX ', 2),
	('B00009', 'Monitor Samsung LED IPS S24R350 Full HD 24inch', 1309000, 'src\\images\\Monitor Samsung LED IPS S24R350 Full HD 24inch.jpg', 4);

-- Dumping structure for table db_jual.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `idmember` varchar(10) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`idmember`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table db_jual.customer: ~6 rows (approximately)
DELETE FROM `customer`;
INSERT INTO `customer` (`idmember`, `nama`, `alamat`, `total`) VALUES
	('C00001', 'Lumiel', 'Jl. Pagarsih No. 61', 1000000),
	('C00002', 'Lucies', 'Cimahi Utara No.31', 500000),
	('C00003', 'Anya', 'Lembang No. 1', 300000),
	('C00004', 'Ica', 'Cigondewah No.131', 600000),
	('C00005', 'Leona', 'Baleendah No. 123', 200000),
	('C00006', 'Ucup', 'Maleber Barat Gg. Ormis No. 2', 1000000);

-- Dumping structure for table db_jual.jual
DROP TABLE IF EXISTS `jual`;
CREATE TABLE IF NOT EXISTS `jual` (
  `nojual` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `idmember` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nojual`) USING BTREE,
  KEY `FK_jual_customer` (`idmember`),
  CONSTRAINT `FK_jual_customer` FOREIGN KEY (`idmember`) REFERENCES `customer` (`idmember`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table db_jual.jual: ~5 rows (approximately)
DELETE FROM `jual`;
INSERT INTO `jual` (`nojual`, `tanggal`, `idmember`) VALUES
	('2023001', '2023-09-25', 'C00002'),
	('2023002', '2023-09-26', 'C00004'),
	('2023003', '2023-09-28', 'C00005'),
	('2023004', '2023-11-01', 'C00004'),
	('2023005', '2023-12-01', 'C00004');

-- Dumping structure for table db_jual.jual_detil
DROP TABLE IF EXISTS `jual_detil`;
CREATE TABLE IF NOT EXISTS `jual_detil` (
  `nojual` varchar(10) DEFAULT NULL,
  `kodebrg` varchar(10) DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  KEY `FK_jual_detil_barang` (`kodebrg`),
  KEY `FK_jual_detil_jual` (`nojual`),
  CONSTRAINT `FK_jual_detil_barang` FOREIGN KEY (`kodebrg`) REFERENCES `barang` (`kodebrg`),
  CONSTRAINT `FK_jual_detil_jual` FOREIGN KEY (`nojual`) REFERENCES `jual` (`nojual`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table db_jual.jual_detil: ~9 rows (approximately)
DELETE FROM `jual_detil`;
INSERT INTO `jual_detil` (`nojual`, `kodebrg`, `jumlah`) VALUES
	('2023001', 'B00005', 2),
	('2023001', 'B00004', 3),
	('2023002', 'B00001', 1),
	('2023003', 'B00002', 1),
	('2023003', 'B00004', 3),
	('2023003', 'B00003', 2),
	('2023004', 'B00001', 1),
	('2023004', 'B00003', 2),
	('2023005', 'B00005', 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
