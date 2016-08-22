
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class CSPSOKmean {

    SaveFile saveFile;

    public CSPSOKmean() {
        saveFile = new SaveFile();
    }

    //Classify the data with attribute. 
    public String classify(Instances classData, String outfile, int clusterNo) {
        //Find Classifier index        
        String data = "\n==== Hybrid of Cuckoo search and PSO search for attribute Selection and Simlple K-Mean Clustering ====\n\n";
        try {
            //Select Attribute throgh Hybrid Search 
            //K-Mean Clustering            
            Instances kdata = new Instances(classData);
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
            data += ("Cluster Evaluation: " + eval.clusterResultsToString());
        } catch (Exception ex) {
            data = "\n==== There is Some Problem in Clustering ====\n";
            System.out.println(ex);
        }
        return data;
    }
}
