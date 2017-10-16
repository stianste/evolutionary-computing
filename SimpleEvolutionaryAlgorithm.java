import java.util.Map;
import java.util.Random;

public class SimpleEvolutionaryAlgorithm extends EvolutionaryAlgorithm {

  public SimpleEvolutionaryAlgorithm(Random random, int populationSize) {
    super(random, populationSize);
  }

  @Override
  public double[][] selectParents(Map<double[], Double> fitnessTable) {
    return SelectionHelper.deterministicTournamentWithReplacement(fitnessTable, random, Constants.useFitnessSharing);
  }

  @Override
  public double[] recombination(double[] mother, double[] father) {
    return RecombinationHelper.wholeArithmeticRecombination(mother, father, 0.5, random);
  }

  @Override
  public double[] mutate(double[] individual) {
    return MutationHelper.uniformMutation(individual, random);
  }
}
