import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public abstract class EvolutionaryAlgorithm {

  protected static Random random;
  private int populationSize;

  public EvolutionaryAlgorithm(Random random, int populationSize) {
    this.random = random;
    this.populationSize = populationSize;
  }

  private static double[] generateRandomArrayOfDimension(int dimension) {
    double[] A = new double[dimension];
    IntStream.range(0, dimension)
        .forEach(i -> A[i] = generateDoubleWithinRange(Constants.minValue, Constants.maxValue));
    return A;
  }

  static double generateDoubleWithinRange(int minValue, int maxValue) {
    return minValue + (maxValue - minValue) * random.nextDouble();
  }

  double[][] initializePopulation() {
    double[][] initialPopulation = new double[this.populationSize][Constants.problemDimension];

    IntStream.range(0, populationSize).forEach(i ->
        initialPopulation[i] = generateRandomArrayOfDimension(Constants.problemDimension));

    return initialPopulation;
  }

  public abstract double[][] selectParents(Map<double[], Double> fitnessTable);

  public abstract double[] crossover(double[] mother, double[] father);

  public abstract double[] mutate(double[] individual);
}
