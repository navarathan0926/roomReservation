-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 17, 2022 at 04:38 AM
-- Server version: 8.0.27
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `userlogin`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `user_name` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_name`, `password`) VALUES
('admi', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
CREATE TABLE IF NOT EXISTS `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `roomNo` int NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `total_days` int NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `reserved_Status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Not Booked',
  PRIMARY KEY (`booking_id`),
  KEY `roomNo` (`roomNo`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`booking_id`, `user_id`, `roomNo`, `date_time`, `checkIn`, `checkOut`, `total_days`, `price`, `reserved_Status`) VALUES
(1, 1, 6, '2022-08-07 05:37:47', '2022-08-07', '2022-08-07', 1, '4000.00', 'Booked'),
(2, 1, 22, '2022-08-07 05:37:47', '2022-08-07', '2022-08-07', 1, '3400.00', 'Booked'),
(4, 1, 11, '2022-08-07 05:37:47', '2022-08-07', '2022-08-07', 1, '5500.00', 'Booked'),
(5, 1, 14, '2022-08-07 05:37:47', '2022-08-07', '2022-08-08', 2, '9600.00', 'Booked'),
(6, 2, 5, '2022-08-07 10:13:57', '2022-08-12', '2022-08-13', 2, '6000.00', 'Booked'),
(8, 2, 29, '2022-08-07 10:13:57', '2022-08-12', '2022-08-13', 2, '9200.00', 'Booked'),
(9, 2, 18, '2022-08-07 10:13:57', '2022-08-07', '2022-08-07', 1, '1800.00', 'Booked'),
(10, 2, 21, '2022-08-07 10:13:57', '2022-08-07', '2022-08-07', 1, '2300.00', 'Booked'),
(13, 2, 6, '2022-08-07 16:58:57', '2022-08-10', '2022-08-12', 3, '12000.00', 'Not Booked'),
(14, 2, 2, '2022-08-07 18:17:54', '2022-08-09', '2022-08-11', 3, '7500.00', 'Not Booked'),
(16, 2, 20, '2022-08-08 01:07:38', '2022-08-10', '2022-08-11', 2, '10400.00', 'Not Booked'),
(17, 2, 36, '2022-08-08 01:07:43', '2022-08-10', '2022-08-11', 2, '8600.00', 'Not Booked'),
(18, 2, 17, '2022-08-08 01:07:53', '2022-08-10', '2022-08-11', 2, '4000.00', 'Not Booked'),
(19, 2, 14, '2022-08-08 01:08:02', '2022-08-10', '2022-08-11', 2, '9600.00', 'Not Booked'),
(20, 2, 11, '2022-08-08 01:08:06', '2022-08-10', '2022-08-11', 2, '11000.00', 'Not Booked'),
(21, 2, 38, '2022-08-08 01:19:07', '2022-08-10', '2022-08-12', 3, '12600.00', 'Not Booked'),
(22, 2, 10, '2022-08-08 01:54:14', '2022-08-10', '2022-08-12', 3, '13500.00', 'Not Booked'),
(24, 1, 26, '2022-08-08 05:28:29', '2022-08-10', '2022-08-12', 3, '13500.00', 'Booked'),
(25, 1, 30, '2022-08-08 05:28:29', '2022-08-10', '2022-08-12', 3, '16800.00', 'Booked'),
(32, 1, 15, '2022-08-08 05:38:38', '2022-08-11', '2022-08-13', 3, '9600.00', 'Booked'),
(33, 1, 3, '2022-08-08 05:38:38', '2022-08-11', '2022-08-13', 3, '10500.00', 'Booked'),
(34, 1, 8, '2022-08-08 05:38:38', '2022-08-11', '2022-08-13', 3, '18000.00', 'Booked'),
(36, 1, 35, '2022-08-08 05:44:21', '2022-08-11', '2022-08-12', 2, '8400.00', 'Not Booked'),
(40, 11, 11, '2022-08-08 05:49:11', '2022-08-12', '2022-08-13', 2, '11000.00', 'Booked'),
(41, 11, 14, '2022-08-08 05:49:11', '2022-08-12', '2022-08-13', 2, '9600.00', 'Booked'),
(43, 11, 2, '2022-08-08 05:56:34', '2022-08-08', '2022-08-08', 1, '2500.00', 'Booked'),
(44, 11, 10, '2022-08-08 05:56:34', '2022-08-08', '2022-08-08', 1, '4500.00', 'Booked'),
(45, 11, 22, '2022-08-08 05:56:34', '2022-08-08', '2022-08-08', 1, '3400.00', 'Booked'),
(46, 11, 7, '2022-08-08 06:01:31', '2022-08-08', '2022-08-08', 1, '5000.00', 'Booked'),
(47, 11, 19, '2022-08-08 06:01:31', '2022-08-08', '2022-08-08', 1, '4800.00', 'Booked'),
(48, 11, 39, '2022-08-08 06:01:31', '2022-08-08', '2022-08-08', 1, '6000.00', 'Booked'),
(49, 11, 28, '2022-08-08 06:01:31', '2022-08-08', '2022-08-08', 1, '4800.00', 'Booked'),
(53, 11, 4, '2022-08-08 06:03:30', '2022-08-08', '2022-08-08', 1, '4500.00', 'Booked'),
(54, 11, 12, '2022-08-08 06:03:30', '2022-08-08', '2022-08-08', 1, '6500.00', 'Booked'),
(55, 11, 20, '2022-08-08 06:03:30', '2022-08-08', '2022-08-08', 1, '5200.00', 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `reserved_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `Customer_name` varchar(100) NOT NULL,
  `roomNumbers` varchar(300) NOT NULL,
  `totalBill` decimal(8,2) NOT NULL,
  `Paid_Status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Not Paid',
  PRIMARY KEY (`reserved_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reserved_id`, `customer_id`, `Customer_name`, `roomNumbers`, `totalBill`, `Paid_Status`) VALUES
(1, 1, 'Laksana', 'RoomNo 6[1days] \nRoomNo 22[1days] \nRoomNo 11[1days] \nRoomNo 14[2days] \n', '22500.00', 'Paid'),
(2, 2, 'Navarathan', 'RoomNo 5 (2 days) \n[CheckIn : 2022-08-12] [CheckOut : 2022-08-13]\n\nRoomNo 29 (2 days) \n[CheckIn : 2022-08-12] [CheckOut : 2022-08-13]\n\nRoomNo 18 (1 days) \n[CheckIn : 2022-08-07] [CheckOut : 2022-08-07]\n\nRoomNo 21 (1 days) \n[CheckIn : 2022-08-07] [CheckOut : 2022-08-07]\n\n', '19300.00', 'Paid'),
(3, 2, 'Navarathan', 'RoomNo 22 (3 days) \n[CheckIn : 2022-08-10] [CheckOut : 2022-08-12]\n\nRoomNo 10 (3 days) \n[CheckIn : 2022-08-10] [CheckOut : 2022-08-12]\n\n', '23700.00', 'Not Paid'),
(4, 1, 'Laksana', 'RoomNo 26 (3 days) \n[CheckIn : 2022-08-10] [CheckOut : 2022-08-12]\n\nRoomNo 30 (3 days) \n[CheckIn : 2022-08-10] [CheckOut : 2022-08-12]\n\n', '30300.00', 'Paid'),
(5, 1, 'Laksana', 'RoomNo 15 (3 days) \n[CheckIn : 2022-08-11] [CheckOut : 2022-08-13]\n\nRoomNo 3 (3 days) \n[CheckIn : 2022-08-11] [CheckOut : 2022-08-13]\n\nRoomNo 8 (3 days) \n[CheckIn : 2022-08-11] [CheckOut : 2022-08-13]\n\n', '38100.00', 'Paid'),
(6, 11, 'Lotus', 'RoomNo 11 (2 days) \n[CheckIn : 2022-08-12] [CheckOut : 2022-08-13]\n\nRoomNo 14 (2 days) \n[CheckIn : 2022-08-12] [CheckOut : 2022-08-13]\n\n', '20600.00', 'Paid'),
(7, 11, 'Lotus', 'RoomNo 2 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 10 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 22 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\n', '10400.00', 'Paid'),
(8, 11, 'Lotus', 'RoomNo 7 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 19 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 39 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 28 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\n', '20600.00', 'Paid'),
(9, 11, 'Lotus', 'RoomNo 1 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 32 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 17 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\n', '6500.00', 'Paid'),
(10, 11, 'Lotus', 'RoomNo 4 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 12 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\nRoomNo 20 (1 days) \n[CheckIn : 2022-08-08] [CheckOut : 2022-08-08]\n\n', '16200.00', 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `roomNo` int NOT NULL AUTO_INCREMENT,
  `acStatus` varchar(20) NOT NULL,
  `roomType` varchar(20) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `availability` varchar(10) NOT NULL,
  PRIMARY KEY (`roomNo`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`roomNo`, `acStatus`, `roomType`, `price`, `availability`) VALUES
(1, 'Non AC', 'Single', '1500.00', 'YES'),
(2, 'Non AC', 'Double', '2500.00', 'No'),
(3, 'Non AC', 'Triple', '3500.00', 'YES'),
(4, 'Non AC', 'Quad', '4500.00', 'No'),
(5, 'AC', 'Single', '3000.00', 'YES'),
(6, 'AC', 'Double', '4000.00', 'YES'),
(7, 'AC', 'Triple', '5000.00', 'No'),
(8, 'AC', 'Quad', '6000.00', 'YES'),
(9, 'AC', 'Single', '2800.00', 'YES'),
(10, 'AC', 'Double', '4500.00', 'No'),
(11, 'AC', 'Triple', '5500.00', 'YES'),
(12, 'AC', 'Quad', '6500.00', 'No'),
(13, 'Non AC', 'Quad', '5300.00', 'YES'),
(14, 'Non AC', 'Triple', '4800.00', 'No'),
(15, 'Non AC', 'Double', '3200.00', 'YES'),
(16, 'Non AC', 'Single', '2100.00', 'YES'),
(17, 'AC', 'Single', '2000.00', 'YES'),
(18, 'Non AC', 'Single', '1800.00', 'YES'),
(19, 'Non AC', 'Triple', '4800.00', 'No'),
(20, 'Non AC', 'Quad', '5200.00', 'No'),
(21, 'AC', 'Single', '2300.00', 'YES'),
(22, 'AC', 'Double', '3400.00', 'No'),
(23, 'Non AC', 'Triple', '6300.00', 'YES'),
(24, 'Non AC', 'Quad', '8000.00', 'YES'),
(25, 'Non AC', 'Double', '3500.00', 'YES'),
(26, 'AC', 'Double', '4500.00', 'YES'),
(27, 'AC', 'Single', '3000.00', 'YES'),
(28, 'Non AC', 'Triple', '4800.00', 'No'),
(29, 'Non AC', 'Quad', '4600.00', 'YES'),
(30, 'Non AC', 'Triple', '5600.00', 'YES'),
(31, 'Non AC', 'Quad', '6400.00', 'YES'),
(32, 'AC', 'Single', '3000.00', 'YES'),
(33, 'AC', 'Single', '3000.00', 'YES'),
(34, 'AC', 'Single', '3500.00', 'YES'),
(35, 'AC', 'Double', '4200.00', 'YES'),
(36, 'AC', 'Quad', '4300.00', 'YES'),
(37, 'Non AC', 'Single', '1800.00', 'YES'),
(38, 'Non AC', 'Double', '4200.00', 'YES'),
(39, 'AC', 'Triple', '6000.00', 'No'),
(40, 'Non AC', 'Triple', '4200.00', 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_no` varchar(12) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `firstname`, `lastname`, `email`, `phone_no`, `username`, `password`) VALUES
(1, 'Laksana', 'Sivakumaran', 'luxsasiva@gmail.com', '0776668890', 'Laksana', '2119eb59afc81b22cf8a4298047f9723'),
(2, 'Uthayakumar', 'Navarathan', 'navaa@gmail.com', '0778865430', 'Navarathan', '2119eb59afc81b22cf8a4298047f9723'),
(3, 'Satkunarasa', 'Ajinthan', 'aji@gmail.com', '07788665544', 'Ajinthan', '1234@'),
(4, 'Srishaayu', 'Balasurian', 'saayu@gmail.com', '0765432459', 'Shaayu', '1234@'),
(5, 'Pooja', 'Sabesan', 'pooja@gmail.com', '0786545678', 'Pooja', '1234@'),
(6, 'Balachandiran', 'Thinusan', 'thinu@gmail.com', '0712345678', 'Thinusan', '1234@'),
(7, 'Duxsana', 'Saantharasa', 'dux@gmail.com', '0786545678', 'Duxsana', '1234@'),
(9, 'Kunarasa', 'Tharsujan', 'tharsu@gmail.com', '07771233456', 'Thachu', '2119eb59afc81b22cf8a4298047f9723'),
(10, 'Kunasegaram', 'Kujinthan', 'kujinthan@gmail.com', '077343884', 'Kujinthan', '2119eb59afc81b22cf8a4298047f9723'),
(11, 'Lotus', 'Jasmine', 'lotus@gmail.com', '0772348781', 'Lotus', '2119eb59afc81b22cf8a4298047f9723');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`roomNo`) REFERENCES `rooms` (`roomNo`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
