/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Clean
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import gen.FileManager;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande clean.
 * @author Berney Alec & Herzig Melvyn
 */
@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer>
{
  @CommandLine.Parameters(index = "0", description = "path to existing workspace", defaultValue = "")String path;

  /**
   * Méthode pour l'appel de la commande clean
   */
  @Override public Integer call() throws IOException
  {
      if(!path.equals(""))
      {
          FileManager.clean("./" + path + "/build");
      }
      else
      {
          FileManager.clean("./build");
      }

      return 0;
  }

}