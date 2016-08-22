import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import weka.core.converters.CSVLoader;

public class OpenFile {

    // define JFile chooser  varaible.
    public JFileChooser jfc;

    // Open data file
    public OpenFile() {

        // Call file chooser constructor
        jfc = new JFileChooser();

        //Set File Filter for csv data
        FileFilter csvFilter = new ExtensionFileFilter("Data File (*.csv)", "CSV");
        jfc.setFileFilter(csvFilter);

        // Multi selection of file disabled.
        jfc.setMultiSelectionEnabled(false);
    }

    public void openFile(Clustering dm) {

        // Show the file chooser dialog
        int returnVal = jfc.showOpenDialog(dm);

        // file slected and Okay.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            // load the file.
            if (loadData(file)) {
                // display the file path in footer.
                dm.inpText.setText(file.getPath());
            }
        }
    }

    public void saveFile(Clustering dm) {
        // Show the file chooser dialog
        int returnVal = jfc.showSaveDialog(dm);

        // file slected and Okay.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            dm.outText.setText(jfc.getSelectedFile().getPath());
        }
    }

    public boolean loadData(File file) {
        try {
            String fname = file.getName().toLowerCase();
            if (fname.endsWith(".csv")) {
                // load CSV
                CSVLoader loader = new CSVLoader();
                loader.setSource(file);
                Clustering.classData = loader.getDataSet();
            } else {
                // Other file format
                JOptionPane.showMessageDialog(null, "No Support for the File\n" + file.getPath(), "File Type Not Support", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            return true;
        } catch (Exception ex) {
            // Error massage if file not properly read.
            JOptionPane.showMessageDialog(null, "Error in Open File \n" + file.getPath(), "File Open Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean loadSearch(String file) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File(file));
            Clustering.searchData = loader.getDataSet();
            return true;
        } catch (Exception ex) {
            // Error massage if file not properly read.
            System.out.println("Exceptions :"+ex);
            JOptionPane.showMessageDialog(null, "Error in Open File \n" + file, "File Open Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

// File Externsion filter class. 
class ExtensionFileFilter extends FileFilter {

    String description;

    String extensions[];

// Single extension file type
    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[]{extension});
    }

    // Multiple extension in single file type.
    public ExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    // Convert string to lower case.
    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }
// Get description 

    public String getDescription() {
        return description;
    }

    public boolean accept(File file) {
        // check file is directory.
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                // Check for extensions condition.
                String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    return true;
                }
            }
        }
        return false;
    }
}
