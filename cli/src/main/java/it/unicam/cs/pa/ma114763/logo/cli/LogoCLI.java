package it.unicam.cs.pa.ma114763.logo.cli;

import it.unicam.cs.pa.ma114763.logo.LogoInterpreter;
import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.processor.LogoProcessor;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Lorenzo Lapucci
 */
public class LogoCLI {

    public static void main(String[] args) {
        Map<String, String> options = OptionsUtils.getOptions(args);

        File input = null;
        File output = null;
        Size size = null;

        if (options.containsKey("h") || options.containsKey("help")) {
            showHelp();
            return;
        }
        try {
            if (options.containsKey("i") || options.containsKey("input")) {
                input = new File(getStringArg(options, "i", "input", "No input file specified"));
            }
            if (options.containsKey("o") || options.containsKey("output")) {
                output = new File(getStringArg(options, "o", "output", "No output file specified"));
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
    private void checkAndRun(File input, File output, Size size) {
        if (input == null || output == null || size == null) {
            askAndRun(new Scanner(System.in), input, output, size);
        } else {
            run(input, output, size);
        }
    }

    private void askAndRun(Scanner scanner, File input, File output, Size size) {
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

    private void run(File input, File output, Size size) {
        System.out.println("Running Logo interpreter on input file: " + input);
        System.out.println("Saving file: " + output + "\nCanvas size: " + size.width() + "w " + size.height() + "h");
        DrawingContext drawing = new LoggingLogoDrawing(size.width(), size.height());
        LogoInterpreter interpreter = new LogoInterpreter(new LogoProcessor(), drawing);
        String program = FileUtils.readProgramFromFile(input);

        if (program != null && runProgram(interpreter, program)) {
            if (FileUtils.saveProgramToFile(output, drawing.getDrawingAsString())) {
                System.out.println("Program saved to file: " + output);
            } else {
                System.out.println("Error saving program to file: " + output);
            }
        }
    }

    private boolean runProgram(LogoInterpreter interpreter, String program) {
        try {
            interpreter.initialize(new LogoParser(), program);
            interpreter.runAll();
            return true;
        } catch (ParserException e) {
            System.out.println("Input parser error: " + e.getMessage());
            return false;
        }
    }

    private File askForInput(Scanner scanner) {
        File file = null;
        while (file == null) {
            System.out.println("Input file:");
            File input = new File(scanner.nextLine());
            if (!input.exists() || !input.isFile()) {
                System.out.println("File not found or not a valid file, try again");
                continue;
            }
            file = input;
        }
        return file;
    }

    private File askForOutput(Scanner scanner) {
        File file = null;
        while (file == null) {
            System.out.println("Output file:");
            String arg = scanner.nextLine();
            if (arg.trim().isEmpty()) continue;
            File output = new File(arg);
            if (output.exists()) {
                System.out.println("File already exists, do you want to overwrite it? (y/n)");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes")) {
                    continue;
                }
            }
            file = output;
        }
        return file;
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
