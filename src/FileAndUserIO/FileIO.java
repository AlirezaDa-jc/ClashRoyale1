
package FileAndUserIO;

import Utilities.StrUtilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {


    public static String readTextFile(String filename) {
        StringBuilder res = new StringBuilder();
        String line;
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {
                res.append(line);
                res.append("\n");
            }
            res = new StringBuilder(StrUtilities.chomp(res.toString()));

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file : ");
            System.err.println(filename);
            System.out.println("--");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Reading error with file : ");
            System.err.println(filename);
            System.out.println("--");
            ex.printStackTrace();
        }
        return res.toString();
    }
}
