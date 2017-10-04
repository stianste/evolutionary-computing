First create a Group16.class file:     

`javac -cp contest.jar Group16.java EvolutionaryAlgorithm.java SimpleEvolutionaryAlgorithm.java Constants.java SelectionHelper.java MutationHelper.java RecombinationHelper.java `

Next, create a submission:     

`jar cmf MainClass.txt submission.jar Group16.class SelectionHelper.class MutationHelper.class RecombinationHelper.class `

Finally test code by typing:

`java -jar testrun.jar -submission=Group16 -evaluation=BentCigarFunction -seed=1`

Alternatively you can just run the `run.sh` file provided in the directory to run all these commands automatically. 

On Linux you must also set the LD_LIBRARY_PATH using the following command: 

`export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/libcjavabbob.so`
