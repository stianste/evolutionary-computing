import java.util.Random;
import java.util.stream.IntStream;

public abstract class EvolutionaryAlgorithm {

  private int populationSize;
  private Random random;

  public EvolutionaryAlgorithm(Random random, int populationSize) {
    this.random = random;
    this.populationSize = populationSize;
  }

  private static double[] generateRandomArrayOfDimension(int dimension, Random random) {
    double[] A = new double[dimension];
    IntStream.range(0, dimension).forEach(i ->
        A[i] =
            Constants.minValue + (Constants.maxValue - Constants.minValue) * random.nextDouble());

    return A;
  }

  public double[][] initializePopulation() {
    double[][] initialPopulation = new double[this.populationSize][Constants.problemDimension];

    IntStream.range(0, populationSize).forEach(i ->
        initialPopulation[i] = generateRandomArrayOfDimension(Constants.problemDimension,
            this.random));

    return initialPopulation;
  }

}
