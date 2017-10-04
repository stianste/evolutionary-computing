javac -cp contest.jar Group16.java EvolutionaryAlgorithm.java SimpleEvolutionaryAlgorithm.java Constants.java SelectionHelper.java MutationHelper.java RecombinationHelper.java && \
  jar cmf MainClass.txt submission.jar Group16.class SelectionHelper.class MutationHelper.class RecombinationHelper.class && \
  java -jar testrun.jar -submission=Group16 -evaluation=BentCigarFunction -seed=1
