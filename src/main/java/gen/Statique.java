/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Statique
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import picocli.CommandLine;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Callable;
import gen.command.Build;
import gen.command.Clean;
import gen.command.Init;
import gen.command.Serve;

/**
 * Class implémentant la commande principale statique.
 * @author Herzig Melvyn
 */
@CommandLine.Command(
        name = "statique",
        description = "A brand new static site generator.",
        subcommands = {Init.class, Clean.class, Build.class, Serve.class})
public class Statique implements Callable<Integer>
{
   @CommandLine.Option(names = "-version", description = "Print software version")
   boolean displayVersionNeeded;

   /**
    * Méthode pour l'appel de la commande statique
    */
   @Override
   public Integer call() throws Exception
   {
      if(displayVersionNeeded)
      {
         displayVersion();
      }
      else
      {
         CommandLine.usage(this, System.out);
      }

      return 0;
   }

   /**
    * Affichage de la version de statique.
    * Se base sur la version dans le pom.xml
    */
   private void displayVersion()
   {
      try
      {
         Properties properties = new Properties();
         properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
         System.out.print("Statique version: ");
         System.out.println(properties.getProperty("version"));
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Point d'entrée du programme.
    * @param args Arguments
    */
   public static void main(String[] args)
   {
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      try
      {
         System.exit(cmd.execute(args));
      }
      catch (CommandLine.ParameterException e)
      {
         cmd.getErr().println(e.getMessage());
         if (!CommandLine.UnmatchedArgumentException.printSuggestions(e, cmd.getErr()))
         {
            e.getCommandLine().usage(cmd.getErr());
         }

         System.exit(cmd.getCommandSpec().exitCodeOnInvalidInput());
      }
      catch (Exception e)
      {
         e.printStackTrace(cmd.getErr());
         System.exit(cmd.getCommandSpec().exitCodeOnExecutionException());
      }
   }
}
