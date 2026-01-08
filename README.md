Restaurant ASIATIK EXPRESS - Back-End (Javalin & MySQL)
Interface de programmation (API) pour la gestion des données du restaurant. Ce projet assure la liaison entre la base de données MySQL et l'interface JavaFX, gérant le catalogue des produits et l'enregistrement des commandes.

📋 Prérequis

Java 17 ou supérieur.

Maven (pour la gestion des dépendances).

Serveur MySQL (via WAMP, XAMPP ou installation directe).

Base de données : Le fichier java_restau_clean.sql doit être importé.

⚙️ Procédures pour générer le code Pour installer les dépendances et compiler le serveur, exécutez à la racine du dossier backend :

__________________

mvn clean compile
__________________

🚀 Lancement du projet Assurez-vous que votre serveur MySQL est actif, puis lancez le serveur API avec :

_______________

mvn exec:java
_______________

Le serveur écoute par défaut sur le port 7070.

📂 Organisation du Projet (Structure) Basé sur l'architecture du dossier src/main/java/fr/java :

api/dto/ : Contient les objets de transfert de données (CreateOrderRequest, CreateOrderResponse) pour structurer les échanges JSON avec le front.

model/ : Définition des objets métiers reflétant la base de données (Category, Dish, Order, OrderItem).

repo/ : Couche d'accès aux données :

Db.java : Configuration de la connexion JDBC (URL, User, Password).

MySqlStore.java : Logique des requêtes SQL (DAO) pour récupérer les produits et sauvegarder les ventes.

Main.java : Point d'entrée de l'application, configure les routes API (Endpoints) avec Javalin.

✨ Fonctionnalités implémentées

API RESTful : Fournit des données au format JSON pour les catégories et les plats.

Gestion des Commandes : Réception des paniers complexes et enregistrement sécurisé en base de données avec gestion des transactions.

Liaison SQL : Système de persistance robuste utilisant JDBC pour communiquer avec MySQL.

Modélisation UML : Structure validée par le diagramme présent dans le dossier modelio/.

👤 Auteurs

Benhamza Alae
Benbaout Lina