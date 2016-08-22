import java.util.ArrayList;
import java.util.Random;

public class Search {
    
    protected ArrayList<Double> vars;
    protected int numVars;
    
    /**
     * Invariant: fitness must be updated to null whenever the solution is
     *            modified, to prevent inaccurate fitness numbers.
     */
    protected Double fitness;
    
    protected Random rand = new Random();
    
    /**
     * Initialize CSSolution with specific ArrayList of
     * coefficients.
     */
    public Search(ArrayList<Double> vars) {
        this.vars = new ArrayList<Double>(vars);
        this.numVars = vars.size();
    }
    
    public Search(int numVars) {
    	this.vars = new ArrayList<Double>();
    	this.numVars = numVars;
    }
    
    public ArrayList<Double> getVars() {
        return vars;
    }
    
    public void setVars(ArrayList<Double> vars) {
    	this.vars = new ArrayList<Double>(vars);
    }
    
    public void evalFitness(OptimizationProblem optProb) {
        this.fitness = optProb.fitness(this);
    }
    
    public double getFitness() {
        if (this.fitness == null) {
//          TODO  throw new Exception("Uninitialized fitness!");
            System.out.printf("Uninitialized fitness!");
            System.exit(1);
        }
        return this.fitness;
    }
    
    public double getFitness(OptimizationProblem optProb) {
		evalFitness(optProb);
    	return this.fitness;
    }
    
    protected void initializeWithNull() {
        for (int i = 0; i < this.numVars; i++) {
            this.vars.add(null);
        }
    }
    
    /**
     * Sets solution with random variable values, ensuring constraints for
     * the optimization problem are met.
     */
    public void setAsRandSol(OptimizationProblem optProb) {
        if (this.vars.size() < this.numVars) {
            this.initializeWithNull();
        }
        do {
            for (int i = 0; i < this.numVars; i++) {
                double min = optProb.getMinVar(i);
                double max = optProb.getMaxVar(i);
                double range = max - min;
                double newVar = rand.nextDouble() * range + min;
                this.vars.set(i, newVar);
            }
        } while(!optProb.withinConstraints(this));
    }
    
    public String toString() {
        String output = "";
        if (this.vars.size() == 0)
            output += "Empty Solution";
        else {
            for (int i = 0; i < this.numVars; i++) {
                output += String.format("x%d:\t%f\n", i, this.vars.get(i));
            }
        }
        if (this.fitness != null)
            System.out.printf("---------------------\nFitness: %f\n", this.fitness);
        return output;
    }
    
    public void print() {
        System.out.println(this.toString());
    }
}
