import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class SimpleEvolutionaryAlgorithm extends EvolutionaryAlgorithm {

  public SimpleEvolutionaryAlgorithm(Random random, int populationSize) {
    super(random, populationSize);
  }

  @Override
  public double[][] selectParents(Map<double[], Double> fitnessTable){
    int fitnessWeight = 10;

    List<double[]> selectionArray = new ArrayList<double[]>();
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
    int k = this.random.nextInt(mother.length);
    double[] firstPart = Arrays.copyOfRange(mother, 0, k);
    double[] secondPart = Arrays.copyOfRange(father, k, father.length);
    return DoubleStream.concat(Arrays.stream(firstPart), Arrays.stream(secondPart)).toArray();
  }

  @Override
  public double[] mutate(double[] individual) {
    double[] mutatedIndividual = new double[individual.length];
    IntStream.range(0, individual.length).forEach(i -> {
      int random = this.random.nextInt(100);
      if(random < (int) Constants.mutationRate * 100) {
        mutatedIndividual[i] = this.generateDoubleWithinRange(Constants.minValue, Constants.maxValue);
      } else {
        mutatedIndividual[i] = individual[i];
      }
    });
    return mutatedIndividual;
  }
}
