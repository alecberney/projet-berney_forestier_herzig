/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Statique
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    * Pour un chemin donné, crée les fichiers par défaut
    * @param path Chemin ou créer le répertoire.
    */
   public static void instantiate(String path)
   {

      for(String fileName : baseFiles)
      {
         Path p = Paths.get(path);
         String pathStart = p.isAbsolute() ? "" : ".";

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
}
