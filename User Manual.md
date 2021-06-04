# Manuel Utilisateur

## Installation

### 1) Télécharger l'archive
[récupérer l'archive statique.zip](https://github.com/gen-classroom/projet-berney_forestier_herzig/releases)

### 2) Dezippage de l'archive
**MacOS/Linux:**

```
unzip -o statique.zip
```

**Windows:**
dézipper l'archive *target/statique.zip* manuellement.

### 3) Ajouter l'application au path
 depuis l'intérieur de l'archive dézippée 
 
**MacOS/Linux:**  
```
export PATH=$PATH:`pwd`/bin
```

**Windows:**
Dans cmd.exe
```  
SET PATH=%PATH%;%cd%\bin
```

## Utilisation

### Informations d'utilisation générale

>L'application s'utilise à l'aide d'une interface en ligne de commande.

#### Commandes

Executer <i>$statique</i> seul vous donnera la liste des commandes et donc le résultat suivant
```	
Usage: statique [-version] [COMMAND]
A brand new static site generator.
      -version   Print software version
Commands:
  init   Initialize a static site directory
  clean  Clean a static site
  build  Build a static site
  serve  Open the site in a web browser.
```

les 4 commandes prennent des chemins de dossier. Si le chemin est manquant, la commande travail dans le répertoire
courant. 

Voici à quoi sevent chacune de ces commandes:
* init : Initialise tout ce qui est nécessaire pour créer le site
* build : Créer le site static à partir des fichiers mardkdown
* serve : Afficher le site dans un navigateur
* clean : Supprimer 

#### Arguments pour les commandes

Des arguments peuvent être utilisés sur certaines commandes, voici comment les ajouter:
>$ statique --[argument] build /cheminSite

Voici les arguments qui peuvent être passés à certaines commandes:
* watch : Permet de régénérer le site statique à la volée lorsque un changement sur le système de fichiers opère

Voici une liste des arguments pouvant être passés à chaque commande:
* init : aucune
* build : --watch
* serve : --watch
* clean : aucune

#### Ordre d'utilisation logique

1. statique init /monSite
2. configuration et création du site
3. statique build /monSite
4. statique serve /monSite
5. statique clean /monSite

### Configuration
Dans le fichier config.yaml il est possible de définir le titre de votre site. Ce titre
apparaîtra dans l'onglet des navigateurs avec le titre de page.
```
---
site_titre: titre du site
---
```

### Créer un page
Pour créer une page, copier coller le template suivant dans chaque fichier markdown.
```
---
page_titre: titre de la page
---

# Cet un titre

C'est du contenu!
```
Voici un exemple contenant beaucoup de possibilités de contenu pour le fichier index.md:
```
---
page_titre: titre de la page
---

# Un titre
## Un sous-titre
Notre projet github [ici](https://github.com/gen-classroom/projet-berney_forestier_herzig)


Une autre page de mon site [ici](./subdir/subdirfile.html)

| col1 | col2 |
|---|---|
|cell1|cell2|

~~du texte barré~~

++du texte souligné++

Qu'est-ce qui est mieux ?
- [ ]  Ne pas avoir des checkbox
- [x] Avoir des checkbox


---\

Une jolie image pour vos beaux yeux.

![Une image](./images/image.png)


La même image en plus petite

![Une image plus petite](./images/image.png){width=200}

```

> page_titre est la seule metadonnée des pages.

Plus de contenu peut être ajouté en suivant la spécification commonmark:
https://commonmark.org/help/

#### Ajouter des liens dans le menu
Il est possible de créer un menu en éditant le fichier <i>/template/menu.html</i>

Pour chaque nouveau lien que vous souhaitez créer ajouter entre les balises <i>ul</i> présentes:
```
<li><a href="chemin/vers_un_fichier_md/depuis_le_dossier_init/index.html">nom du lien</a></li>
````
> Le fichier ciblé par le lien est insérer entre dans href="<fichier ici>". Le fichier doit être ciblé depuis 
> le dossier créer par init et en remplacant l'extension md par html.

![arbre](figures/arbre.png)
			      	    
Si vous souhaitez créer un lien vers anotherPage.md, il faut écrire "aFolder/anotherPage.html" dans la balise href.

### Scénario d'utilisation

Si vous travaillez dans le répertoire <i>C:\user</i> et que vous souhaitez créer votre site dans le répertoire <i>C:\user\mySite</i>
>$ statique init /mySite

Configurez, préparez et créez votre site comme vous le souhaitez.
Pour ceci, consulter la section *Configuration* et *Créer une page* et vous pouvez également consulter  la section *Ajouter des liens dans le menu*.

Pour lancer la création / génération du site statique
>$ statique build /mySite

Le résultat de la traduction est déployé dans <i>C:\user\mySite\build</i>

Vous voulez retirer le déploiement ?
La commande recherche un dossier nommé build et le supprime. Ne vous trompez en pointant sur un mauvais dossier.
>$ statique clean /mySite

Pour visualiser votre site dans un navigateur.
>$ statique serve /mySite

Vous voulez que les modifications s'appliquent et s'affichent directement sur le navigateur?
>$ statique build --watch /mySite 
>$ statique serve --watch /mySite

Si vous voulez quitter le mode *watch*, utiliser le raccourci:
>$ Ctrl + c
>$Terminer le programme de commandes (O/N) ? O
