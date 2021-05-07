/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : StatiqueTest
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour la classe gen.Main
 */
public class StatiqueTest
{

   // Redirection du flux de sortie.
   final PrintStream standardOut = System.out;
   final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

   /**
    * Préparation des tests.
    */
   @BeforeEach
   public void setUp()
   {
      // Redirecton
      System.setOut(new PrintStream(outputStreamCaptor));
   }

   /**
    * Test de la commande: statique -version
    */
   @Test
   void runTest()
   {
      // Préparation de la commande.
      Callable<Integer> callable = new Statique();
      CommandLine cmd = new CommandLine(callable);

      // Arguments.
      String[] args = new String[]{"-version"};

      // Exécution
      String expected = "Statique version: 0.0.2";
      cmd.execute(args);

      //Vérifie si la commande donne la bonne sortie.
      assertEquals(expected,outputStreamCaptor.toString().trim());
   }

   /**
    * Réinitialisation.
    */
   @AfterEach
   public void tearDown() {
      System.setOut(standardOut);
   }
}
