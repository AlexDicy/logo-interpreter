package it.unicam.cs.pa.ma114763.logo.cli;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lorenzo Lapucci
 */
public class OptionsUtils {

    /**
     * Given an array of strings, it returns a map with the options and their
     * values.
     * <p>
     * Remove '-' and '--' from the beginning of the option name.
     *
     * @param args the array of strings to parse
     * @return a map with the options and their values
     */
    public static Map<String, String> getOptions(String[] args) {
        Map<String, String> options = new HashMap<>(args.length / 2);
        int i = 0;
        while (i < args.length - 1) {
            if (args[i].startsWith("-") && !args[i + 1].startsWith("-")) {
                options.put(stripKey(args[i]), args[i + 1]);
                i += 2;
            } else {
                options.put(stripKey(args[i]), "");
                i++;
            }
        }
        if (i < args.length) {
            options.put(stripKey(args[i]), "");
        }
        return options;
    }

    private static String stripKey(String key) {
        return key.startsWith("--") ? key.substring(2)
                : key.startsWith("-") ? key.substring(1) : key;
    }
}
