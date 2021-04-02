/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Init
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande init.
 * @author Herzig Melvyn
 */
@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer>
{

  /**
   * Méthode pour l'appel de la commande new
   */
  @Override public Integer call()
  {
    System.out.printf("init");
    return 0;
  }

}