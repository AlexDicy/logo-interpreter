package it.unicam.cs.pa.ma114763.logo.io;

import java.io.IOException;

/**
 * Reads a resource and returns a string representing the program.
 *
 * @author Lorenzo Lapucci
 */
public interface Reader {

    /**
     * Reads the program string from the resource
     *
     * @return the string representing the program
     * @throws IOException if an error occurred while reading from the resource
     */
    String read() throws IOException;
}
