import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Island {

  Function<double[], Double> evaluationFunction;
  private int populationSize;
  private EvolutionaryAlgorithm EA;
  private Map<double[], Double> parentsFitnessTable;
  private Map<double[], Double> childFitnessTable;

  public Island(int populationSize, EvolutionaryAlgorithm EA,
      Function<double[], Double> evaluationFunction) {
    this.populationSize = populationSize;
    this.evaluationFunction = evaluationFunction;
    this.EA = EA;
    this.childFitnessTable = new HashMap<>();
    this.initializePopulation();
  }

  private void initializePopulation() {
    this.evaluatePopulation(this.EA.initializePopulation(), this.parentsFitnessTable);
  }

  private void evaluatePopulation(double[][] population, Map<double[], Double> fitnessTable) {
    Arrays.stream(population).forEach(
        individual -> fitnessTable
            .put(individual, (double) this.evaluationFunction.apply(individual)));
  }

  public void nextGeneration() {
    this.childFitnessTable.clear();
    while (this.childFitnessTable.size() < this.populationSize + 1) {
      for (int i = 0; i < this.populationSize; i++) {
        double[][] parents = this.EA.selectParents(this.parentsFitnessTable);

        double[] child = EA.recombination(parents[0], parents[1]);
        double[] mutatedChild = EA.mutate(child);

        Double fitness = (double) this.evaluationFunction.apply(mutatedChild);

        this.childFitnessTable.put(mutatedChild, fitness);
      }

    }
    // Make the current population of children the parent population of the next generation
    this.parentsFitnessTable = new HashMap<>(this.childFitnessTable);
    this.childFitnessTable.clear();
  }
}
