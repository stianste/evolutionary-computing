import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class RecombinationHelper {

  public static double[] singleArithmeticRecombination(double[] mother, double[] father,
      double alpha, Random random) {
    int k = random.nextInt(mother.length);

    double[] child = new double[mother.length];

    IntStream.range(0, mother.length).forEach(i -> {
      if (k < i) {
        child[i] = mother[i];
      } else {
        child[i] = (1 - alpha) * mother[i] + alpha * father[i];
      }
    });
    return child;
  }

  static double[] wholeArithmeticRecombination(double[] mother, double[] father, double alpha,
      Random random) {
    int k = random.nextInt(mother.length);

    double[] child = new double[mother.length];

    IntStream.range(0, mother.length).forEach(i -> {
      child[i] = (1 - alpha) * mother[i] + alpha * father[i];
    });

    return child;
  }

  static double[] naiveCrossover(double[] mother, double[] father, Random random) {
    // A recombination operator that makes more sense for integer representation, not these
    // real valued vectors
    int k = random.nextInt(mother.length);
    double[] firstPart = Arrays.copyOfRange(mother, 0, k);
    double[] secondPart = Arrays.copyOfRange(father, k, father.length);
    return DoubleStream.concat(Arrays.stream(firstPart), Arrays.stream(secondPart)).toArray();
  }
}

