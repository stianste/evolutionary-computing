import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class Group16 implements ContestSubmission {

  static Random rnd_;
  ContestEvaluation evaluation_;
  private int evaluations_limit_;
  private Map<double[], Double> parentsFitnessTable = new HashMap<>();
  private Map<double[], Double> childFitnessTable = new HashMap<>();

  public Group16() {
    rnd_ = new Random();
  }

  public void setSeed(long seed) {
    // Set seed of algortihms random process
    rnd_.setSeed(seed);
  }

  public void setEvaluation(ContestEvaluation evaluation) {
    // Set evaluation problem used in the run
    evaluation_ = evaluation;

    // Get evaluation properties
    Properties props = evaluation.getProperties();
    // Get evaluation limit
    evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
    // Property keys depend on specific evaluation
    // E.g. double param = Double.parseDouble(props.getProperty("property_name"));
    boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
    boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
    boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

    // Do sth with property values, e.g. specify relevant settings of your algorithm
    if (isMultimodal) {
      // Do sth
    } else {
      // Do sth else
    }
  }

  public void run() {
    EvolutionaryAlgorithm EA = new SimpleEvolutionaryAlgorithm(rnd_, Constants.populationSize);

    int evals = 0;

    double[][] initialPopulation = EA.initializePopulation();

    // Give the members of the initial population a fitness score
    evaluatePopulation(initialPopulation, this.parentsFitnessTable);
    evals += Constants.populationSize;

    while (evals < evaluations_limit_) {
      // Next generation
      for (int i = 0; i < Constants.populationSize; i++) {
        double[][] parents = EA.selectParents(this.parentsFitnessTable);

        double[] child = EA.crossover(parents[0], parents[1]);
        double[] mutatedChild = EA.mutate(child);

        Double fitness = (double) evaluation_.evaluate(mutatedChild);
        evals++;

        this.childFitnessTable.put(mutatedChild, fitness);
      }

      // Make the current population of children the parent population of the next generation
      this.parentsFitnessTable = new HashMap<>(this.childFitnessTable);
      this.childFitnessTable.clear();
    }
  }

  private void evaluatePopulation(double[][] population, Map<double[], Double> fitnessTable ) {
    Arrays.stream(population).forEach(individual -> fitnessTable.put(individual, (double) this.evaluation_.evaluate(individual)));
  }
}
