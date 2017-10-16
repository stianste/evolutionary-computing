javac -cp contest.jar Group16.java EvolutionaryAlgorithm.java SimpleEvolutionaryAlgorithm.java Constants.java SelectionHelper.java MutationHelper.java RecombinationHelper.java Island.java FitnessShareHelper.java && \
  jar cmf MainClass.txt submission.jar Group16.class Constants.class SelectionHelper.class MutationHelper.class RecombinationHelper.class Island.class FitnessShareHelper.class && \
  java -jar testrun.jar -submission=Group16 -evaluation=SphereEvaluation -seed=1
