/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Clean
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande clean.
 * @author Herzig Melvyn
 */
@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer>
{

  /**
   * Méthode pour l'appel de la commande clean
   */
  @Override public Integer call()
  {
    System.out.printf("clean");
    return 0;
  }

}