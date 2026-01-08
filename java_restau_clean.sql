-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 07 jan. 2026 à 22:37
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java_restau_clean`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Entrées'),
(2, 'Plats'),
(3, 'Boissons'),
(4, 'Desserts');

-- --------------------------------------------------------

--
-- Structure de la table `dish`
--

CREATE TABLE `dish` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1,
  `icon` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `dish`
--

INSERT INTO `dish` (`id`, `category_id`, `name`, `description`, `price`, `available`, `icon`) VALUES
(1, 1, 'Gyozas', 'Raviolis japonais grillés', 5.50, 1, 'gyozas.png'),
(2, 1, 'Nems poulet', 'Nems croustillants au poulet', 6.90, 1, 'nems.png'),
(3, 2, 'Ramen miso', 'Soupe japonaise au miso', 12.90, 1, 'ramen.png'),
(5, 2, 'Curry rouge', 'Curry thaï épicé', 14.50, 1, 'curry.png'),
(6, 3, 'Bubble Tea', 'Thé aux perles de tapioca', 4.50, 1, 'bubble_tea.png'),
(9, 1, 'Gyozas Grillés', '5 raviolis japonais au poulet et légumes.', 7.20, 1, 'gyoza.png'),
(10, 1, 'Soupe Miso', 'Soupe traditionnelle au soja, tofu et algues wakame.', 4.50, 1, 'miso.png'),
(11, 1, 'Tempura Crevettes', '3 crevettes géantes en beignet léger et croustillant.', 8.90, 1, 'tempura.png'),
(12, 2, 'Pad Thaï Crevettes', 'Nouilles de riz sautées, crevettes, cacahuètes et citron vert.', 14.90, 1, 'padthai.png'),
(14, 2, 'Bo Bun Boeuf', 'Salade de vermicelles froide, bœuf sauté à la citronnelle et nems.', 13.90, 1, 'bobun.png'),
(15, 2, 'Poulet Croustillant', 'Filet de poulet pané façon japonaise servi avec riz blanc.', 12.50, 1, 'katsu.png'),
(16, 2, 'Curry Rouge Thaï', 'Poulet, lait de coco, bambou et basilic thaï (Épicé).', 14.20, 1, 'curry.png'),
(17, 4, 'Mochi Glacé Mix', 'Assortiment de 3 mochis (Vanille, Thé Vert, Mangue).', 6.00, 0, 'mochi.png'),
(18, 4, 'Perles de Coco', '2 boules de riz gluant à la vapeur fourrées au soja.', 5.50, 1, 'coco.png'),
(19, 4, 'Nems au Chocolat', '3 nems croustillants au chocolat fondu et banane.', 6.90, 1, 'chocnems.png'),
(20, 3, 'Boba Thé Mangue', 'Thé glacé à la mangue avec perles de tapioca.', 5.90, 1, 'boba.png'),
(21, 3, 'Limonade Japonaise', 'Ramune traditionnelle au goût de fraise.', 4.20, 1, 'ramune.png'),
(22, 3, 'Bière Asahi', 'Bière blonde japonaise (33cl).', 4.50, 1, 'asahi.png'),
(23, 3, 'Thé Vert Matcha', 'Thé vert japonais authentique servi chaud.', 3.50, 1, 'matcha.png');

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_ref` varchar(50) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `orders`
--

INSERT INTO `orders` (`id`, `customer_ref`, `total`, `created_at`) VALUES
(1, 'lina', 29.80, '2026-01-07 20:22:20');

-- --------------------------------------------------------

--
-- Structure de la table `order_item`
--

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `dish_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `order_item`
--

INSERT INTO `order_item` (`id`, `order_id`, `dish_id`, `quantity`, `unit_price`) VALUES
(1, 1, 11, 1, 8.90),
(2, 1, 14, 1, 13.90),
(3, 1, 23, 2, 3.50);

-- --------------------------------------------------------

--
-- Structure de la table `order_item_option`
--

CREATE TABLE `order_item_option` (
  `id` int(11) NOT NULL,
  `order_item_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `order_item_option`
--

INSERT INTO `order_item_option` (`id`, `order_item_id`, `name`, `value`) VALUES
(1, 1, 'spice', 'Moyen'),
(2, 1, 'side', 'Nouilles'),
(3, 2, 'spice', 'Moyen'),
(4, 2, 'side', 'Nouilles');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `dish`
--
ALTER TABLE `dish`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_dish_category` (`category_id`);

--
-- Index pour la table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_item_order` (`order_id`),
  ADD KEY `fk_order_item_dish` (`dish_id`);

--
-- Index pour la table `order_item_option`
--
ALTER TABLE `order_item_option`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_opt_item` (`order_item_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `dish`
--
ALTER TABLE `dish`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `order_item_option`
--
ALTER TABLE `order_item_option`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `dish`
--
ALTER TABLE `dish`
  ADD CONSTRAINT `fk_dish_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `fk_order_item_dish` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`),
  ADD CONSTRAINT `fk_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Contraintes pour la table `order_item_option`
--
ALTER TABLE `order_item_option`
  ADD CONSTRAINT `fk_opt_item` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
