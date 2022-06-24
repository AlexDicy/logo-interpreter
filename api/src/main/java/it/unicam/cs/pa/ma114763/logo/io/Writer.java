package it.unicam.cs.pa.ma114763.logo.io;

import java.io.IOException;

/**
 * Takes a string representing a program and saves it in a resource.
 *
 * @author Lorenzo Lapucci
 */
public interface Writer {

    /**
     * Writes the given string to the resource.
     *
     * @param program the string to write
     * @throws IOException if an error occurred while writing to the resource
     */
    void write(String program) throws IOException;
}
