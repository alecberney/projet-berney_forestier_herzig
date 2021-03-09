/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Main.h
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import picocli.CommandLine;
import java.util.concurrent.Callable;

/**
 * Class implémentant gen.Main pour picoCLI
 * @author Herzig Melvyn
 */
@CommandLine.Command(
        name = "mainCmd"
)
public class Main implements Callable<Integer>
{
   /**
    * Méthode pour l'appel de la commande mainCmd avec picoCLI
    */
      @Override
   public Integer call() throws Exception
   {

      System.out.println("Hello world!");

      return 0;
   }

   /**
    * Méthode pour l'appel de la commande new avec picoCLI
    */
   @CommandLine.Command(name = "new", description = "command new from mainCmd")
   public Integer newCmd() throws Exception
   {
      System.out.println("new cmd!");
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande build avec picoCLI
    */
   @CommandLine.Command(name = "build", description = "command build from mainCmd")
   public Integer buildCmd() throws Exception
   {
      System.out.println("build cmd");
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande serve avec picoCLI
    */
   @CommandLine.Command(name = "serve", description = "command serve from mainCmd")
   public Integer serveCmd() throws Exception
   {
      System.out.println("Serve cmd");
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande clean avec picoCLI
    */
   @CommandLine.Command(name = "clean", description = "command clean from mainCmd")
   public Integer cleanCmd() throws Exception
   {
      System.out.println("Clean cmd");
      return 0;
   }

   /**
    * Point d'entrée du programme.
    * @param args Arguments
    */
   public static void main(String[] args)
   {
      int exitCode = new CommandLine(new Main()).execute(args);
      System.exit(exitCode);
   }
}
