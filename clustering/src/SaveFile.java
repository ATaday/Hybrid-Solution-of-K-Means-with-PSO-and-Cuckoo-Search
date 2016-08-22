
import java.io.File;
import weka.core.Instances;
import weka.core.converters.CSVSaver;

public class SaveFile {

    public void saveFile(Instances data, String file) {
        try {
            if (file.length() > 0) {
                String fname = file.toLowerCase();
                if (!fname.endsWith(".csv")) {
                    file = file + ".csv";
                }
                CSVSaver csv_saver = new CSVSaver();
                csv_saver.setInstances(data);
                csv_saver.setFile(new File(file));
                csv_saver.writeBatch();
            }
        } catch (Exception ex) {

        }
    }
}
