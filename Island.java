import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import org.vu.contest.ContestEvaluation;

public class Island {

  ContestEvaluation evaluator;
  private int populationSize;
  private EvolutionaryAlgorithm EA;
  private Random random;
  private Map<double[], Double> parentsFitnessTable;
  private Map<double[], Double> childFitnessTable;

  public Island(int populationSize, EvolutionaryAlgorithm EA, Random random,
      ContestEvaluation evaluator) {
    this.populationSize = populationSize;
    this.evaluator = evaluator;
    this.EA = EA;
    this.random = random;
    this.childFitnessTable = new HashMap<>();
    this.parentsFitnessTable = new HashMap<>();
    this.initializePopulation();
  }

  public void nextGeneration() {
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

  public void receiveImmigrants(double[][] immigrants) {
    // The last index (11th) of the immigrants array is the fitness value of that child
    IntStream.range(0, immigrants.length).forEach(i ->
        this.parentsFitnessTable.put(
            Arrays.copyOfRange(immigrants[i], 0, immigrants[i].length - 1),
            immigrants[i][immigrants[i].length - 1])
    );
  }

  public double[][] giveAwayMigrants() {
    double[][] migrants = this.selectRandomMigrators(Constants.numberOfCandidatesToMigrate);

    Arrays.stream(migrants).forEach(migrant ->
        this.parentsFitnessTable.remove(
            Arrays.copyOfRange(migrant, 0, migrant.length - 2)));

    return migrants;
  }

  private void initializePopulation() {
    this.evaluatePopulation(this.EA.initializePopulation(), this.parentsFitnessTable);
  }

  private void evaluatePopulation(double[][] population, Map<double[], Double> fitnessTable) {
    Arrays.stream(population).forEach(
        individual -> {
          fitnessTable
              .put(individual, (double) evaluator.evaluate(individual));
        });
  }

  private double[][] selectRandomMigrators(int numberOfCandidatesToMigrate) {
    double[][] migrants = new double[numberOfCandidatesToMigrate][Constants.problemDimension + 1];

    Object[] keys = this.parentsFitnessTable.keySet().toArray();
    IntStream.range(0, numberOfCandidatesToMigrate).forEach(i -> {
      int randomIndex = this.random.nextInt(keys.length);
      double[] randomCandidate = (double[]) keys[randomIndex];

      IntStream.range(0, randomCandidate.length).forEach(
          candidateIndex -> migrants[i][candidateIndex] = randomCandidate[candidateIndex]
      );

      migrants[i][migrants[i].length - 1] = this.parentsFitnessTable.get(randomCandidate);
    });

    return migrants;
  }

  private double[][] selectMigratorsByTournament(int numberOfCandidatesToMigrate) {
    // Add one extra slot in each migrant for holding it's fitness value
    double[][] migrants = new double[numberOfCandidatesToMigrate][Constants.problemDimension + 1];

    IntStream.range(0, numberOfCandidatesToMigrate).forEach(i -> {
      double[] candidate = SelectionHelper.singleTournament(this.parentsFitnessTable, random);

      IntStream.range(0, candidate.length).forEach(
          candidateIndex -> migrants[i][candidateIndex] = candidate[candidateIndex]
      );

      migrants[i][migrants[i].length - 1] = this.parentsFitnessTable.get(candidate);
    });

    return migrants;
  }
}
