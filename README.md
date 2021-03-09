# projet-berney_forestier_herzig
Ce projet est réalisé durant le cours génie logiciel de la HEIG-VD.
Il met à disposition un générateur de sites statiques.

## Auteurs
  * Berney Alec
  * Forestier Quentin
  * Herzig Melvyn
  
# Installation et utilisation
Construction et dézippage du projet.
```
mvn clean install \
&& rm -fr statique \
&& unzip target/statiqueSite.zip
```
 
Ajout du répertoire bin au PATH
```
export PATH=$PATH:`pwd`/statiqueSite/bin
```

Executé statique donne le résultat suivant
```
statique
Usage: statiqueSite [COMMAND]
A static site generator.
Commands:
	new    Initialize a static site directory
	clean  Clean a static site
	build  Build a static site
	serve  Serve a static site
```

# Conventions de programmation

## Langues
  * Code -> En anglais
  * Commentaires -> En Français

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
