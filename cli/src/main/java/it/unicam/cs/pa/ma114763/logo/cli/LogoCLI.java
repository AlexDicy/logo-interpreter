package it.unicam.cs.pa.ma114763.logo.cli;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Lorenzo Lapucci
 */
public class LogoCLI {

    public static void main(String[] args) {
        Map<String, String> options = OptionsUtils.getOptions(args);

        Path input = null;
        Path output = null;
        Size size = null;

        if (options.containsKey("h") || options.containsKey("help")) {
            showHelp();
            return;
        }
        try {
            if (options.containsKey("i") || options.containsKey("input")) {
                input = Path.of(getStringArg(options, "i", "input", "No input file specified"));
            }
            if (options.containsKey("o") || options.containsKey("output")) {
                output = Path.of(getStringArg(options, "o", "output", "No output file specified"));
            }
            if (options.containsKey("s") || options.containsKey("size")) {
                size = parseSize(getStringArg(options, "s", "size", "No size specified"));
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
        new LogoCLI().checkAndRun(input, output, size);
    }

    private static void showHelp() {
        System.out.println("Usage: java -jar logo-cli.jar [OPTIONS]");
        System.out.println("Options:");
        System.out.println("  -h, --help\t\t\t\t\tShow this help");
        System.out.println("  -i, --input <file>\t\t\tInput file");
        System.out.println("  -o, --output <file>\t\t\tOutput file");
        System.out.println("  -s, --size <width>x<height>\tSize of the canvas");
        System.out.println("If no input, output or size are specified, the program will ask for them in interactive mode");
    }

    private static String getStringArg(Map<String, String> options, String shortKey, String longKey, String error) {
        String inputArg = options.getOrDefault(shortKey, "").isEmpty()
                ? options.get(longKey) : options.get(shortKey);
        if (inputArg == null || inputArg.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
        return inputArg;
    }

    public static Size parseSize(String sizeArg) {
        try {
            String[] size = sizeArg.split("x");
            if (size.length != 2) {
                throw new IllegalArgumentException("Invalid size parameter, expected <width>x<height>");
            }
            return new Size(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid size");
        }
    }

    /**
     * If not all the required arguments for non-interactive mode are present,
     * the program will run in interactive mode.
     *
     * @param input  Input file
     * @param output Output file
     * @param size   Size of the canvas
     */
    private void checkAndRun(Path input, Path output, Size size) {
        if (input == null || output == null || size == null) {
            askAndRun(new Scanner(System.in), input, output, size);
            System.out.println("Interactive mode not implemented yet");
        } else {
            System.out.println("Non-interactive mode not implemented yet");
            //run(input, output, size);
        }
    }

    private void askAndRun(Scanner scanner, Path input, Path output, Size size) {
        if (input == null) {
            input = askForInput(scanner);
        }
        if (output == null) {
            output = askForOutput(scanner);
        }
        if (size == null) {
            int width = askForDimension(scanner, "Canvas width");
            int height = askForDimension(scanner, "Canvas height");
            size = new Size(width, height);
        }
        run(input, output, size);
    }

    private void run(Path input, Path output, Size size) {
        System.out.println("Running...");
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        System.out.println("Size: " + size);
    }

    private Path askForInput(Scanner scanner) {
        Path path = null;
        while (path == null) {
            System.out.println("Input file:");
            File input = new File(scanner.nextLine());
            if (!input.exists() || !input.isFile()) {
                System.out.println("File not found or not a valid file, try again");
                continue;
            }
            path = input.toPath();
        }
        return path;
    }

    private Path askForOutput(Scanner scanner) {
        Path path = null;
        while (path == null) {
            System.out.println("Output file:");
            File output = new File(scanner.nextLine());
            if (output.exists()) {
                System.out.println("File already exists, do you want to overwrite it? (y/n)");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes")) {
                    continue;
                }
            }
            path = output.toPath();
        }
        return path;
    }

    private int askForDimension(Scanner scanner, String name) {
        int dimension = 0;
        while (dimension < 1) {
            try {
                System.out.println(name + ":");
                dimension = Integer.parseInt(scanner.nextLine());
                if (dimension < 1) {
                    System.out.println("Invalid " + name + ", must be greater than 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again");
            }
        }
        return dimension;
    }
}
