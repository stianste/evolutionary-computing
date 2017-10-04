import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SimpleEvolutionaryAlgorithm extends EvolutionaryAlgorithm {

  public SimpleEvolutionaryAlgorithm(Random random, int populationSize) {
    super(random, populationSize);
  }

  @Override
  public double[][] selectParents(Map<double[], Double> fitnessTable){
    int fitnessWeight = 10;

    List<double[]> selectionArray = new ArrayList<>();
    for(Map.Entry<double[], Double> candidate : fitnessTable.entrySet()) {
      for(int i = 0; i < candidate.getValue().intValue() * fitnessWeight + 1; i++){
        selectionArray.add(candidate.getKey());
      }
    }

    double[] mother = selectionArray.get(random.nextInt(selectionArray.size()));
    double[] father = selectionArray.get(random.nextInt(selectionArray.size()));
    double[][] parents = {mother, father};
    return parents;
  }

  @Override
  public double[] crossover(double[] mother, double[] father) {
    return this.singleArithmeticRecombination(mother, father, 0.5);
  }

  @Override
  public double[] mutate(double[] individual) {
    return this.uniformMutation(individual);
  }

  private double[] uniformMutation(double[] individual) {
    double[] mutatedIndividual = new double[individual.length];
    IntStream.range(0, individual.length).forEach(i -> {
      double chanceOfMutation = random.nextDouble();
      if(chanceOfMutation < Constants.mutationRate) {
        mutatedIndividual[i] = generateDoubleWithinRange(Constants.minValue, Constants.maxValue);
      } else {
        mutatedIndividual[i] = individual[i];
      }
    });
    return mutatedIndividual;
  }

  private double[] singleArithmeticRecombination(double[] mother, double[] father, double alpha){
    int k = random.nextInt(mother.length);

    double[] child = new double[mother.length];

    IntStream.range(0, mother.length).forEach(i -> {
      if(k < i){
        child[i] = mother[i];
      } else {
        child[i] = (1 - alpha) * mother[i] + alpha * father[i];
      }
    });

    return child;
  }

  private double[] naiveCrossover(double[] mother, double[] father) {
    // A crossover operator that makes more sense for integer representation, not these
    // real valued vectors
    int k = random.nextInt(mother.length);
    double[] firstPart = Arrays.copyOfRange(mother, 0, k);
    double[] secondPart = Arrays.copyOfRange(father, k, father.length);
    return DoubleStream.concat(Arrays.stream(firstPart), Arrays.stream(secondPart)).toArray();
  }
}
