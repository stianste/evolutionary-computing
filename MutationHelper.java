import java.util.Random;
import java.util.stream.IntStream;

public class MutationHelper {

  public static double[] uniformMutation(double[] individual, Random random) {
    double[] mutatedIndividual = new double[individual.length];
    IntStream.range(0, individual.length).forEach(i -> {
      double chanceOfMutation = random.nextDouble();
      if (chanceOfMutation < Constants.mutationRate) {
        mutatedIndividual[i] = EvolutionaryAlgorithm
            .generateDoubleWithinRange(Constants.minValue, Constants.maxValue);
      } else {
        mutatedIndividual[i] = individual[i];
      }
    });
    return mutatedIndividual;
  }

}

