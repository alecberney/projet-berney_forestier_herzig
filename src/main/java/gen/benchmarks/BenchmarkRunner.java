package gen.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Classe démarrant l'exécution des benchmarks.
 * Inspiré de la présentation du Prof. Chapuis Bertil
 * @author Herzig Melvyn
 * @date 27/05/2021
 *
 */
public class BenchmarkRunner
{
   /**
    * Fonction main permettant de lancé l'exécution des benchmark.
    * Les benchmarks sont faits sur un forks, avec une itérations d'échauffement
    * et 3 itérations de tests.
    * @param args Arguments du main (ignorés)
    * @throws RunnerException Levé si la classe exécutant le benchmark rencontre
    * une erreur.
    */
   public static void main(String... args) throws RunnerException
   {
      Options options = new OptionsBuilder()
                              .forks(1)
                              .warmupIterations(1)
                              .measurementIterations(3)
                              .build();
      new Runner(options).run();
   }
}
