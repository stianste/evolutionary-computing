import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class Group16 implements ContestSubmission {

  Random rnd_;
  ContestEvaluation evaluation_;
  private int evaluations_limit_;

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
    System.out.println("Running algorithm");

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
    int populationSize = 10;
    EvolutionaryAlgorithm EA = new SimpleEvolutionaryAlgorithm(this.rnd_, populationSize);

    int evals = 0;
    // init population
    double[][] initialPopulation = EA.initializePopulation();
    System.out.println("Running algorithm");
    System.out.flush();
    Arrays.stream(initialPopulation).forEach(individual -> System.out.println(Arrays.toString(individual)));
    // calculate fitness
    while (evals < evaluations_limit_) {
      // Select parents
      // Apply crossover / mutation operators
      double child[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
      // Check fitness of unknown fuction
      Double fitness = (double) evaluation_.evaluate(child);
      evals++;
      // Select survivors
    }

  }
}
