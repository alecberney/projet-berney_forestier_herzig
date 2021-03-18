/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Serve
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Class implémentant la sous commande serve.
 * @author Herzig Melvyn
 */
@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer>
{

  /**
   * Méthode pour l'appel de la commande serve.
   */
  @Override public Integer call()
  {
    System.out.printf("serve");
    return 0;
  }

}