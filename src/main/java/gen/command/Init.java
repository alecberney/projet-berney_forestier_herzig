/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Init
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import gen.FileManager;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande init.
 * @author Herzig Melvyn
 */
@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer>
{
  @CommandLine.Parameters(index = "0", description = "path to futur workspace", defaultValue = "/")String path;

  /**
   * Méthode pour l'appel de la commande new
   */
  @Override public Integer call()
  {
    FileManager.instantiate(path);
    return 0;
  }

}