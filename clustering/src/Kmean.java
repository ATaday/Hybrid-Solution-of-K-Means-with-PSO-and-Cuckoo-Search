import weka.classifiers.meta.FilteredClassifier;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class Kmean {

    /**
     * Object that stores the classifier
     */
    SaveFile saveFile;
    FilteredClassifier classifier;
    public Kmean() {
        saveFile = new SaveFile();
    }
    
     //Classify the data with attribute. 
    public String classify(Instances classData,  String outfile, int clusterNo) {       

        //Find Classifier index        
        String data = "\n==== Simlple K-Mean Clustering ====\n\n";
        try {        
            //K-Mean Clustering            
            Instances kdata=new Instances(classData);
            kdata.setClassIndex(-1);
            SimpleKMeans kmeans = new SimpleKMeans();
            kmeans.setSeed(10);
            
            //important parameter to set: preserver order, number of cluster.
            kmeans.setPreserveInstancesOrder(true);
            kmeans.setNumClusters(clusterNo);
            kmeans.buildClusterer(kdata);
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(kmeans);
            eval.evaluateClusterer(kdata);
            saveFile.saveFile(kdata, outfile);
            data +=("Cluster Evaluation: " + eval.clusterResultsToString()); 
        } catch (Exception ex) {
            data = "\n==== There is Some Problem in Clustering ====\n";
        }       
        return data;
    } 
}
