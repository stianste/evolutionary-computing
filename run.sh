javac -cp contest.jar Group16.java EvolutionaryAlgorithm.java SimpleEvolutionaryAlgorithm.java Constants.java && \
  jar cmf MainClass.txt submission.jar Group16.class && \
  java -jar testrun.jar -submission=Group16 -evaluation=BentCigarFunction -seed=1
