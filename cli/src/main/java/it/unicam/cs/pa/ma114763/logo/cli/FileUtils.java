package it.unicam.cs.pa.ma114763.logo.cli;

import it.unicam.cs.pa.ma114763.logo.io.FileResourceReader;
import it.unicam.cs.pa.ma114763.logo.io.FileResourceWriter;

import java.io.File;
import java.io.IOException;

/**
 * @author Lorenzo Lapucci
 */
public class FileUtils {

    /**
     * @param input the input file
     * @return a string representing the input file
     */
    public static String readProgramFromFile(File input) {
        try {
            FileResourceReader reader = new FileResourceReader(input);
            return reader.read();
        } catch (IOException e) {
            System.err.println("I/O error while reading the input file: " + e.getMessage());
            return null;
        }
    }

    /**
     * @param output     the output file
     * @param logoOutput the string representing the output
     * @return true if the output was successfully written, false otherwise
     */
    public static boolean saveProgramToFile(File output, String logoOutput) {
        try {
            FileResourceWriter writer = new FileResourceWriter(output);
            writer.write(logoOutput);
            return true;
        } catch (IOException e) {
            System.err.println("I/O error while writing the output file: " + e.getMessage());
            return false;
        }
    }
}
