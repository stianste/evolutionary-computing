public class Constants {

  public static final int problemDimension = 10;
  public static final int minValue = -5;
  public static final int maxValue = 5;
  public static final int populationSize = 1000;
  public static final int tournamentSize = 10;
  public static final int generationsBetweenMigrations = 10;
  public static final int numberOfCandidatesToMigrate = 5;
  public static final int subpopulationSize = 50; // Can't be smaller than tournamentSize
  public static final int numberOfSubpopulations = 10;
  public static final int nicheDistance = 10;
  public static final double fitnessSharingAlpha = 0.5;
  public static final double mutationRate = 0.05;
}
