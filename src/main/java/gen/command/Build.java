/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Build
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande build.
 * @author Herzig Melvyn
 */
@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer>
{
  /**
   * Méthode pour l'appel de la commande build.
   */
  @Override public Integer call()
  {
    System.out.printf("build");
    return 0;
  }

}