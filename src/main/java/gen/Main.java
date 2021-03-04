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
    * Méthode pour l'appel de la commande newCmd avec picoCLI
    */
   @CommandLine.Command(name = "new", description = "command new from mainCmd")
   public Integer newCmd() throws Exception
   {
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande buildCmd avec picoCLI
    */
   @CommandLine.Command(name = "build", description = "command new from mainCmd")
   public Integer buildCmd() throws Exception
   {
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande buildCmd avec picoCLI
    */
   @CommandLine.Command(name = "serve", description = "command new from mainCmd")
   public Integer serveCmd() throws Exception
   {
      return 0;
   }

   /**
    * Méthode pour l'appel de la commande cleanCmd avec picoCLI
    */
   @CommandLine.Command(name = "clean", description = "command new from mainCmd")
   public Integer cleanCmd() throws Exception
   {
      return 0;
   }

   public static void main(String[] args)
   {
      int exitCode = new CommandLine(new Main()).execute(args);
      System.exit(exitCode);
   }
}
