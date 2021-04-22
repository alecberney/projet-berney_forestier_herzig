/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Statique
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import java.io.File;
import java.io.IOException;
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

   private FileManager(){}

   /**
    * Pour un chemin donné, crée le dossier template avec les fichiers par défauts.
    * @param path Chemin ou créer le répertoire.
    */
   public static void instantiate(String path)
   {
      Path p = Paths.get(path);
      String pathStart = p.isAbsolute() ? "" : ".";

      File templateDir = new File(pathStart + path + "/template");
      templateDir.mkdir();

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
