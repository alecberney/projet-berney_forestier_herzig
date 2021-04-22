/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : command.InitTest
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import gen.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de test pour la commande init
 */
public class InitTest
{
   private static final String path = "/test";

   /**
    * Test si la commande init peut créer les fichier sans chemin.
    */
   @Test
   void runTestEmptyPath()
   {
      // Préparation de la commande.
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      // Arguments.
      String[] args = new String[]{"init"};

      File index  = new File("./index.md");
      File config  = new File("./config.yaml");
      if(index.exists()) index.delete();
      if(config.exists()) config.delete();

      // Exécution
      cmd.execute(args);

      //Les deux fichiers sont-il créés ?
      assertTrue(index.exists() && config.exists());
   }

   /**
    * Test si la commande init crée les fichiers correctement avec un chemin
    */
   @Test
   void runTestWithPath()
   {
      // Préparation de la commande.
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      // Arguments.
      String[] args = new String[]{"init", path};

      File index  = new File("./"+path+"/index.md");
      File config  = new File("./"+path+"/config.yaml");
      if(index.exists()) index.delete();
      if(config.exists()) config.delete();

      File dir = new File("./"+path);
      if(dir.exists()) dir.delete();

      // Exécution
      cmd.execute(args);

      //Les deux fichiers sont-il créés ?
      assertTrue(index.exists() && config.exists());
   }

   @Test
   void runTestTemplateFolderCreation()
   {
      // Préparation de la commande.
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      // Arguments.
      String[] args = new String[]{"init", path};

      // Exécution
      cmd.execute(args);

      // Récupération du dossier
      File templateFolder = new File("./"+path+"/template");

      assertTrue(templateFolder.exists());
   }

   @Test
   void runTestMenuCreation()
   {
      // Préparation de la commande.
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      // Arguments.
      String[] args = new String[]{"init", path};

      File menu  = new File("./"+path+"/template/menu.html");
      if(menu.exists()) menu.delete();

      // Exécution
      cmd.execute(args);

      // Résultat
      String content = "<ul>\n" +
              "\t<li><a href=\"index.html\">home</a></li>\n" +
              "</ul>";

      // Lecture du menu
      String data = "";
      try
      {
         data = FileUtils.readFileToString(menu, "UTF-8");
         int i = 0;
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      assertEquals(data, content);
   }

   /**
    * Supprimer les fichiers à la fin des tests
    */
   @AfterAll
   static void clean()
   {
      // Nettoie le test runTestEmptyPath
      File index  = new File("./index.md");
      File config  = new File("./config.yaml");
      if(index.exists()) index.delete();
      if(config.exists()) config.delete();

      // Supprime le dossier test
      File dir = new File("./"+path);
      if(dir.exists())
      {
         try
         {
            FileUtils.deleteDirectory(dir);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
   }
}
