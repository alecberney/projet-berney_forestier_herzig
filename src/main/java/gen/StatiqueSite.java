/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Main
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import picocli.CommandLine;
import java.util.concurrent.Callable;
import gen.command.Build;
import gen.command.Clean;
import gen.command.New;
import gen.command.Serve;

/**
 * Class implémentant la commande principale statiqueSite.
 * @author Herzig Melvyn
 */
@CommandLine.Command(
        name = "statiqueSite",
        description = "A brand new static site generator.",
        subcommands = {New.class, Clean.class, Build.class, Serve.class})
public class StatiqueSite implements Callable<Integer>
{
   /**
    * Méthode pour l'appel de la commande statiqueSite
    */
   @Override
   public Integer call() throws Exception
   {
      CommandLine.usage(this, System.out);
      return 0;
   }


   /**
    * Point d'entrée du programme.
    * @param args Arguments
    */
   public static void main(String[] args)
   {
      int exitCode = new CommandLine(new StatiqueSite()).execute(args);
      System.exit(exitCode);
   }
}
