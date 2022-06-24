package it.unicam.cs.pa.ma114763.logo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * {@link ResourceReader} implementation for files.
 *
 * @author Lorenzo Lapucci
 */
public class FileResourceReader implements ResourceReader {
    private final Path path;

    public FileResourceReader(Path path) {
        this.path = path;
    }

    @Override
    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
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
