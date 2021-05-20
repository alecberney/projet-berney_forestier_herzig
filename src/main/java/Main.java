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
public class Statique implements Callable<Integer>
{
 
   /**
    * Point d'entrée du programme.
    * @param args Arguments
    */
   public static void main(String[] args)
   {
      System.out.println("Hello World");
   }
}
