/**
 * Author: Michael Mussler
 * Assignment Hypothesis testing
 * 3/21/2018
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.stat.inference.*;

public class Hypothesis {
//main method
    public static void main(String[] args) {
        String line = "";
        ArrayList<String> simJMachine = new ArrayList<String>();
        ArrayList<String> simJHuman = new ArrayList<String>();
        ArrayList<String> nicMachine = new ArrayList<String>();
        ArrayList<String> nicHuman = new ArrayList<String>();

        //SimJMachine Column
        try {
            FileReader fread = new FileReader("SemanticSimilarityScores.tsv");
            BufferedReader bread = new BufferedReader(fread);

            String Header = bread.readLine();
            while ((line = bread.readLine()) != null) {
                simJMachine.add(line.split("\\s+")[1]);

            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        //SimJHuman column
        try {
            FileReader fread = new FileReader("SemanticSimilarityScores.tsv");
            BufferedReader bread = new BufferedReader(fread);

            String headerLine = bread.readLine();
            while ((line = bread.readLine()) != null) {
                simJHuman.add(line.split("\\s+")[3]);

            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        //nicMachine column
        try {
            FileReader fread = new FileReader("SemanticSimilarityScores.tsv");
            BufferedReader bread = new BufferedReader(fread);

            String headerLine = bread.readLine();
            while ((line = bread.readLine()) != null) {
                nicMachine.add(line.split("\\s+")[2]);

            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        //nicHuman column
        try {
            FileReader fread = new FileReader("SemanticSimilarityScores.tsv");
            BufferedReader bread = new BufferedReader(fread);

            String headerLine = bread.readLine();
            while ((line = bread.readLine()) != null) {
                nicHuman.add(line.split("\\s+")[4]);

            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        //Create 4 loops so it will go through each column
        double[] simMachine = new double[simJMachine.size()];
        double[] simHuman = new double[simJHuman.size()];
        double[] nicDHuman = new double[nicHuman.size()];
        double[] nicDMachine = new double[nicMachine.size()];

        //iterate over the list Duplicate 4 times for each column
        for (int i = 0; i < simJMachine.size(); ++i) {
            //store each element as a double in the array.
            simMachine[i] = Double.parseDouble(simJMachine.get(i));
        }
        for (int i = 0; i < simJHuman.size(); ++i) {
            simHuman[i] = Double.parseDouble(simJHuman.get(i));
        }
        for (int i = 0; i < nicHuman.size(); ++i) {
            nicDHuman[i] = Double.parseDouble(nicHuman.get(i));
        }
        for (int i = 0; i < nicMachine.size(); ++i) {
            nicDMachine[i] = Double.parseDouble(nicMachine.get(i));
        }

        //Calculate the T test
        TTest t = new TTest();
        double t_stat;
        t_stat = t.t(nicDHuman, nicDMachine);
        double t_stat2;
        t_stat2 = t.t(simMachine, simHuman);

        //calculate the p-Value
        double nicpValue = t.tTest(nicDHuman, nicDMachine);
        double simpValue = t.tTest(simMachine, simHuman);

        //print the results
        System.out.println("nic t_statistic test\t" + Double.toString(t_stat));
        System.out.println("simj t_statistic test\t" + Double.toString(t_stat2));
        System.out.println("nic p-Value test\t" + nicpValue);
        System.out.println("simj p-Value test\t" + simpValue);
    }
}
