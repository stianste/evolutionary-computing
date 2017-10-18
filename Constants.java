public class Constants {

  public static final int problemDimension = 10;
  public static final int minValue = -5;
  public static final int maxValue = 5;

  public static final int populationSize = 1000;

  public static final int subpopulationSize = 100;
  public static final int numberOfSubpopulations = 25;

  public static final int generationsBetweenMigrations = 20;
  public static final int nicheDistance = 25;

  public static final int tournamentSize = subpopulationSize / 5;
  public static final int numberOfCandidatesToMigrate = subpopulationSize / 20;

//  public static final double fitnessSharingAlpha = 1.5;
  public static final double mutationRate = 0.05;

  public static final boolean useFitnessSharing = true;
}
