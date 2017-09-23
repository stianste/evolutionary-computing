First create a Group16.class file:     

`javac -cp contest.jar Group16.java EvolutionaryAlgorithm.java SimpleEvolutionaryAlgorithm.java Constants.java `

Next, create a submission:     

`jar cmf MainClass.txt submission.jar Group16.class `

Finally test code by typing:

`java -jar testrun.jar -submission=Group16 -evaluation=BentCigarFunction -seed=1`
