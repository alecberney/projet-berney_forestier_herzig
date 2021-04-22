/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Statique
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/**
 * Class gérant la structure de fichiers pour Statique.
 * @author Herzig Melvyn
 */
public class FileManager
{
   /**
    * Nom des fichiers créés par défaut.
    */
   private static final String[] baseFiles = {"config.yaml", "index.md"};

   /**
    * Contenu du fichier menu.html
    */
   public static final String menuContent = "<ul>\n" +
                                             "\t<li><a href=\"index.html\">home</a></li>\n" +
                                             "</ul>";
   /**
    * Contenu du fichier template.html
    */
   public static final String templateContent = "<!doctype html>\n" +
                                                 "<html>\n" +
                                                 "\t<head>\n" +
                                                 "\t\t<meta charset=\"UTF-8\">\n" +
                                                 "\t\t<title>{{ site.titre }} | {{ page.titre }}</title>" +
                                                 "\t</head>\n" +
                                                 "\t<body>\n" +
                                                 "\t\t {{ include menu.html }}\n" +
                                                 "\t\t {{ content }}\n" +
                                                 "\t</body>\n" +
                                                 "</html>";

   private FileManager(){}

   /**
    * Pour un chemin donné, crée le dossier template avec les fichiers par défauts.
    * @param path Chemin ou créer le répertoire.
    */
   public static void instantiate(String path)
   {
      Path p = Paths.get(path);
      String pathStart = p.isAbsolute() ? "" : ".";

      File dir = new File(pathStart);
      dir.mkdir();

      String templatePath = pathStart + path + "/template";
      File templateDir = new File(templatePath);
      templateDir.mkdirs();

      // Create templated files
      createMenuHtml(templatePath);
      createTemplateHtml(templatePath);

      for(String fileName : baseFiles)
      {

         File file = new File(pathStart + path + "/" + fileName);

         file.getParentFile().mkdirs();
         try
         {
            file.createNewFile();
         }
         catch (IOException e)
         {
            System.out.println(e.getMessage());
         }
      }
   }

   /**
    * Crée le fichier menu.html dans le dossier situé dans path.
    * @param path Dossier qui reçoit le menu.html
    */
   private static void createMenuHtml(String path)
   {
      String content = FileManager.menuContent;
      createFile(new File(path + "/menu.html"), content);
   }

   /**
    * Crée le fichier menu.html dans le dossier situé dans path.
    * @param path Dossier qui reçoit le menu.html
    */
   private static void createTemplateHtml(String path)
   {
      String content = FileManager.templateContent;
      createFile(new File(path + "/template.html"), content);
   }

   /**
    * Crée le fichier file et y insère le contenu content.
    * @param file Fichier à créer.
    * @param content Contenu à insérer.
    */
   public static void createFile(File file, String content)
   {
      try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file))))
      {
         out.write(content);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Pour un chemin donné, supprime tous dossiers et fichiers
    * @param path Chemin du répertoire à supprimer.
    */
   public static void clean(String path)
   {
      File folder  = new File(path);

      if(folder.exists())
      {
         try
         {
            FileUtils.deleteDirectory(folder);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
   }
}
