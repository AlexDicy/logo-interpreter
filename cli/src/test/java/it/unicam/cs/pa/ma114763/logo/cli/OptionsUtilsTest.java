package it.unicam.cs.pa.ma114763.logo.cli;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lorenzo Lapucci
 */
class OptionsUtilsTest {

    @Test
    void getOptions() {
        String[] args = {"-h", "--help", "--width", "10", "--height",
                "20", "--color", "red", "--bgcolor", "blue", "nogui"};
        Map<String, String> options = OptionsUtils.getOptions(args);
        assertEquals("", options.get("h"));
        assertEquals("", options.get("help"));
        assertEquals("10", options.get("width"));
        assertEquals("20", options.get("height"));
        assertEquals("red", options.get("color"));
        assertEquals("blue", options.get("bgcolor"));
        assertEquals("", options.get("nogui"));
    }

}
