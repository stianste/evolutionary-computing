import static org.junit.Assert.*;
import static org.junit.Test;

public class SimpleEvolutionaryAlgorithmTest {

  EvolutionaryAlgorithm SEA = new SimpleEvolutionaryAlgorithm(new Random(), 500);

  @Test
  void testCorrectCrossover() {
    double[] mother = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
    double[] father = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

    double[] child = SEA.crossover(mother, father);
    AssertEquals(child.length, mother.length);
    IntStream.range(0, child.length).forEach(i -> AssertEquals(child[i], mother[i]));
  }

}