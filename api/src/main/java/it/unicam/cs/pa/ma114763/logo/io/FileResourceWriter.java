package it.unicam.cs.pa.ma114763.logo.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * {@link ResourceWriter} implementation for files.
 *
 * @author Lorenzo Lapucci
 */
public class FileResourceWriter implements ResourceWriter {
    private final File file;

    public FileResourceWriter(File file) {
        this.file = file;
    }


    @Override
    public void write(String program) throws IOException {
        if (!file.createNewFile() && !file.exists() && !file.canWrite()) {
            throw new IOException("Cannot write to file " + file.getAbsolutePath());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(program);
        writer.close();
    }
}
