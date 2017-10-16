import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class FitnessShareHelper {

  public static Map<double[],Double> sharedFitnessTable(Map<double[], Double> fitnessTable) {
    // Could take a more dynamic programming approach here and not recalculate values
    Object[] individuals = fitnessTable.keySet().toArray();

    Arrays.stream(individuals).forEach(individual ->
        fitnessTable.put(
            (double[]) individual, calculateSharedFitness((double[]) individual, fitnessTable)));

    return fitnessTable;
  }

  private static int d(double[] i, double[] j){
    // Return the Euclidean distance between the two individuals i and j
    double sumOfSquares = 0;
    for(int index = 0; index < i.length; index++){
      sumOfSquares += Math.pow(i[index] + j[index], 2);
    }

    Double square = Math.sqrt(sumOfSquares);
    return square.intValue();
  }

  private static double sh(int d) {
    // The share function of two individuals
    if (d <= Constants.nicheDistance) {
      return 1 - (d / Constants.nicheDistance);
    }
    return 0.0;
  }

  private static double calculateSharedFitness(double[] i, Map<double[], Double> fitnessTable){
    Object[] individuals = fitnessTable.keySet().toArray();
    double sharedFitnessSum = 0;
    for (int index = 0; index < individuals.length; index++){
      sharedFitnessSum += sh(d(i, (double[]) individuals[index]));
    }

    return fitnessTable.get(i) / sharedFitnessSum;
  }
}
