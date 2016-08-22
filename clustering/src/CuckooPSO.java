import java.util.ArrayList;

public class CuckooPSO extends PSOSolution {

    public CuckooPSO(ArrayList<Double> vars) {
        super(vars);
    }

    public CuckooPSO(int numVars) {
        super(numVars);
    }

    //copied from CSSolution
    public CuckooPSO randomWalk(int n) {

        // creates a neighborhood of size 1 times the scaling factor
        double distanceSquared = Math.pow(rand.nextDouble(), 2);
        // creates an ArrayList from 0 to n-1 (for indexing purposes only)
        ArrayList<Integer> varIndices = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            varIndices.add(i, i);
        }

        ArrayList<Double> vars = this.getVars();
        CuckooPSO newSol = new CuckooPSO(n);
        newSol.initializeWithNull();
        ArrayList<Double> newVars = newSol.getVars();
        for (int i = 0; i < n; i++) {
            /* Chooses a random variable index from the indices
    		 * of the remaining/unwalked variables. */
            int index = rand.nextInt(varIndices.size());
            // Finds the variable value that this index corresponds to.
            int varIndex = varIndices.get(index);
            double curVar = vars.get(varIndex);

            // use correct distribution to generate random double [0,1)
            double r= rand.nextDouble();
            
            // alters this variable coefficient by adding a random step between (-distance,distance)
            double distance = Math.sqrt(distanceSquared);
            double varStep = r * distance * 2 - distance;
            double newVar = curVar + varStep;
            newVars.set(varIndex, newVar);
            // removes the variable that has already been visited
            varIndices.remove(index);
            // updates distance for next for loop
            distanceSquared -= Math.pow(varStep, 2);
        }
//        int len = vars.size();
//        for (int i = 0; i < len; i++) {
//            System.out.println("Darshan "+vars.get(i)+" "+newVars.get(i));
//        }
        return newSol;
    }
}
