# Ticketing App - JavaFX Application

## 📋 Description

**Ticketing App** est une application de gestion de tickets de support développée en **JavaFX**. Elle permet de créer, consulter, modifier et supprimer des tickets de support client avec persistence des données dans une base de données **SQLite**.

### Fonctionnalités principales :
- ✅ **Créer** des tickets de support
- ✅ **Consulter** la liste des tickets dans un tableau
- ✅ **Modifier** les tickets existants
- ✅ **Supprimer** des tickets
- ✅ **Rechercher** des tickets par titre ou client
- ✅ **Filtrer** par statut et priorité
- ✅ **Marquer** comme urgent
- ✅ **Exporter** les tickets (CSV si implémenté)
- ✅ **Persistence** des données dans SQLite

## 🛠️ Technologies utilisées

- **Java** - Langage de programmation
- **JavaFX** - Framework UI de bureau
- **SQLite** - Base de données légère
- **Maven** - Gestionnaire de dépendances et build


## 🚀 Installation et lancement

### Prérequis
- **Java 11+** installé
- **Maven 3.6+** installé

### Étapes d'installation

1. **Cloner ou télécharger le projet**
   ```bash
   git clone <URL_DU_PROJET>
   cd ticketing_app
   ```

2. **Compiler le projet**
   ```bash
   mvn clean compile
   ```

3. **Lancer l'application**
   ```bash
   mvn javafx:run
   ```

   Ou en une seule commande :
   ```bash
   mvn clean compile javafx:run
   ````
