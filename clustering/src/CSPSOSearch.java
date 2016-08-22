import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CSPSOSearch {

    public void convertCSV(String inpFile, String outFile, int col, int row) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inpFile));
            row++;
            String data[][] = new String[row][col];
            for (int i = 0; i < row; i++) {
                String line = br.readLine();
                data[i] = line.split(",");
            }
            br.close();
            
            for (int j = 0; j < col; j++) {
                ArrayList<Double> cspso = new ArrayList<>();
                try {
                    for (int i = 1; i < row; i++) {
                        cspso.add(Double.parseDouble(data[i][j]));
                    }
                    CuckooPSO cs=new CuckooPSO(cspso);
                    CuckooPSO res=cs.randomWalk(cspso.size());
                    for (int i = 1; i < row; i++) {
                        data[i][j] = "" + res.vars.get(i - 1);
                    }
                } catch (Exception ex) {
                    System.out.println("Exceptions "+ex);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (j == col - 1) {
                        bw.write(data[i][j]);
                    } else {
                        bw.write(data[i][j] + ",");
                    }
                }
                bw.write("\n");
            }
            bw.close();
        } catch (Exception ex) {
            System.out.println("Ex :" + ex);
        }
    }
}
