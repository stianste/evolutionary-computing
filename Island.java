import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.vu.contest.ContestEvaluation;

public class Island {

  ContestEvaluation evaluator;
  private int populationSize;
  private EvolutionaryAlgorithm EA;
  private Map<double[], Double> parentsFitnessTable;
  private Map<double[], Double> childFitnessTable;

  public Island(int populationSize, EvolutionaryAlgorithm EA,
      ContestEvaluation evaluator) {
    this.populationSize = populationSize;
    this.evaluator = evaluator;
    this.EA = EA;
    this.childFitnessTable = new HashMap<>();
    this.parentsFitnessTable = new HashMap<>();
    this.initializePopulation();
  }

  private void initializePopulation() {
    this.evaluatePopulation(this.EA.initializePopulation(), this.parentsFitnessTable);
    System.out.println(this.parentsFitnessTable.size());
  }

  private void evaluatePopulation(double[][] population, Map<double[], Double> fitnessTable) {
    Arrays.stream(population).forEach(
        individual -> {
          fitnessTable
              .put(individual, (double) evaluator.evaluate(individual));
        });
  }

  void nextGeneration() {
    this.childFitnessTable.clear();

    for (int i = 0; i < this.populationSize; i++) {
      double[][] parents = this.EA.selectParents(this.parentsFitnessTable);

      double[] child = EA.recombination(parents[0], parents[1]);
      double[] mutatedChild = EA.mutate(child);

      Double fitness = (double) this.evaluator.evaluate(mutatedChild);

      this.childFitnessTable.put(mutatedChild, fitness);
    }

    // Make the current population of children the parent population of the next generation
    this.parentsFitnessTable = new HashMap<>(this.childFitnessTable);
    this.childFitnessTable.clear();
  }
}
