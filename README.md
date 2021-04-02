(nous somme le groupe qui n'a pas pu clonner le repo pour la documentation)
Vous la trouvez mise à jour dans la branche main "Rapport.docx".

# projet-berney_forestier_herzig
Ce projet est réalisé durant le cours génie logiciel de la HEIG-VD.
Il met à disposition un générateur de sites statiques.

## Auteurs
  * Berney Alec
  * Forestier Quentin
  * Herzig Melvyn
  
# Installation et utilisation

### Dezippage de l'archive
**MacOS/Linux:**

```
mvn clean install \
    && unzip -o target/statique.zip
```

**Windows:** Utilise git bash ou n'importe quel autre bash basé sur unix. Ou faire un mvn clean install -> supprimer manuellement le vieux dossier "statique" à la racine du projet and dézipper la nouvelle archive *target/statique.zip*.

### Ajout au path
 depuis le répertoire du projer
**MacOS/Linux:**  
```
export PATH=$PATH:`pwd`/statique/bin
```

**Windows:**
Dans cmd.exe
```  
SET PATH=%PATH%;%cd%\statique\bin
```

### Exécution  
Sous windows penser à utiliser cmd.exe

Executer statique donne le résultat suivant
```
statique
Usage: statique [COMMAND]
A static site generator.
      -version Print software version
Commands:
	new    Initialize a static site directory
	clean  Clean a static site
	build  Build a static site
	serve  Serve a static site
```
La commande serve ne fait pas partie de ce sprint et n'est pas encore implémentée.
Le fichier config est généré mais n'est pas encore pris en compte car nous n'avons pas cerné son utilité. Il sera implémenté dans une future version.

### metadonnées
```
---
key1: value
key2:
  - value 1
  - value 2
---

document body start here
```
sont traduits par:
```
<meta name="key1" content="value">
<meta name="key2" content="value1, value2">
```

### contenu
Le contenu peut être ajouté en suivant la spécification commonmark:
https://commonmark.org/help/
Le text sans décoration markdown est inclus dans des balises <p> some text </p>

# GitHub
## Langues
	* Branches -> En anglais  
    * Reste -> En français  
## Branches
**Branches d'un sprint:** sprint-x (avec x = 1,2,3,4)    
**Branches qui ajoutent une fonctionnalité:** ft\-feature\-desc  
**Branches qui résolvent un bug:** bg\-name\-of\-bug\-solved  

# Conventions de programmation

## Langues
  * Code -> En anglais
  * Commentaires -> En français

## Accolades
Toujours après un retour à la ligne
```
 if(...)
 {
      /* Du code */
 }
```
## Entête de fichier
```
/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : <NomDuFichier>.h
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : JJ.MM.AAAA
 -----------------------------------------------------------------------------------
 */
 ```
## Commentaires
Les classes et les méthodes sont commentées au format Javadoc.

### Classes 
 ```
/**
 * Description de la classe
 * @author auteur 1
 * @date jj-mm-aaaa
 */
  ```
 
### Méthodes
```
/**
 * Description de la méthode
 * @param param1 Description du premier paramètre.
 * @param param2 Description du second paramètre.
 * @return Que retourne la méthode..
 */
```

### Nommage
Tous les noms seront en camel case sauf pour les constantes où les espaces seront des ‘_’ et en majuscules.  
**Classe :** Première lettre en majuscule, MaClasse.java  
**Fonction :** Première lettre en minuscule, maFonction()  
**Ma constante :** final int UNE_CONSTANTE.  

  
