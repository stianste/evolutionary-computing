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

}
