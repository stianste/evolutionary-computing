import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SelectionHelper {

  static double[][] fitnessProportionalSelection(Map<double[], Double> fitnessTable,
      Random random) {
    int fitnessWeight = 1000;

    List<double[]> selectionArray = new ArrayList<>();
    for (Map.Entry<double[], Double> candidate : fitnessTable.entrySet()) {
      for (int i = 0; i < candidate.getValue() * fitnessWeight + 1; i++) {
        selectionArray.add(candidate.getKey());
      }
    }

    double[] mother = selectionArray.get(random.nextInt(selectionArray.size()));
    double[] father = selectionArray.get(random.nextInt(selectionArray.size()));
    double[][] parents = {mother, father};
    return parents;
  }

  static double[][] deterministicTournamentWithReplacement(Map<double[], Double> fitnessTable,
      Random random, boolean shareFitness) {

    if (shareFitness) {
      fitnessTable = FitnessShareHelper.sharedFitnessTable(fitnessTable);
    }

    double[] mother = singleTournament(fitnessTable, random);
    double[] father = singleTournament(fitnessTable, random);
    double[][] parents = {mother, father};
    return parents;
  }

  public static double[] singleTournament(Map<double[], Double> fitnessTable,
      Random random) {

    double[] mostWinningCandidate = new double[10];
    int mostWins = -1;

    Object[] individuals = fitnessTable.keySet().toArray();

    for (Map.Entry<double[], Double> candidate : fitnessTable.entrySet()) {
      int wins = 0;
      for (int i = 0; i < Constants.tournamentSize; i++) {
        double[] contender = (double[]) individuals[random.nextInt(individuals.length)];
        if (candidate.getValue() > fitnessTable.get(contender)) {
          wins += 1;
        }
      }
      if (wins > mostWins) {
        mostWinningCandidate = candidate.getKey();
        mostWins = wins;
      }
    }
    return mostWinningCandidate;
  }

}
