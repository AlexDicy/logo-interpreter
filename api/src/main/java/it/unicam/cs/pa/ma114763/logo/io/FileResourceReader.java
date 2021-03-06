package it.unicam.cs.pa.ma114763.logo.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@link ResourceReader} implementation for files.
 *
 * @author Lorenzo Lapucci
 */
public class FileResourceReader implements ResourceReader {
    private final File file;

    public FileResourceReader(File file) {
        this.file = file;
    }

    @Override
    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        reader.close();
        return builder.toString();
    }
}
