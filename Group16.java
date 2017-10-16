import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    int evals = 0;

    // Initialize islands
    List<Island> islands = new ArrayList<>();
    IntStream.range(0, Constants.numberOfSubpopulations).forEach(i ->
        islands.add(
            new Island(
                Constants.subpopulationSize,
                new SimpleEvolutionaryAlgorithm(rnd_, Constants.subpopulationSize),
                rnd_,
                this.evaluation_)
        ));

    evals += Constants.subpopulationSize * Constants.numberOfSubpopulations;

    int generation = 1;

    while (evals < evaluations_limit_) {
      islands.stream().forEach(island -> {
        island.nextGeneration();
      });

      if (generation % Constants.generationsBetweenMigrations == 0) {
        performMigration(islands);
      }

      generation += 1;

      evals += Constants.subpopulationSize * Constants.numberOfSubpopulations;

      printStatistics(evals);
    }

  }

  private void performMigration(List<Island> islands) {
    IntStream.range(0, islands.size()).forEach(i -> {
      IntStream.range(i, islands.size()).forEach(j -> {
        if (i != j) {
          islands.get(i).receiveImmigrants(islands.get(j).giveAwayMigrants());
        }
      });
    });
  }

  private void printStatistics(int evals) {
    System.out.println(evals + " evals out of " + evaluations_limit_ + " – " + String
        .valueOf((double) evals / evaluations_limit_ * 100) + " % done");
  }

}
