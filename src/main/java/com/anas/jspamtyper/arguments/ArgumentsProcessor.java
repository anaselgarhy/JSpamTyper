package com.anas.jspamtyper.arguments;

import com.anas.jspamtyper.DebugController;
import com.anas.jspamtyper.MainController;
import org.apache.commons.cli.*;

import java.io.IOException;

public class ArgumentsProcessor {
    private final MainController mainController;
    private final Options options;
    private final CommandLineParser parser;

    public ArgumentsProcessor(MainController mainController) {
        this.mainController = mainController;
        this.options = new Options();
        this.parser = new DefaultParser();
        setupOptions();
    }

    private void setupOptions() {
        options.addOption("h", "help", false, "Print this help")
                .addOption("v", "version", false, "Print version")
                .addOption("d", "debug", false, "Print debug information")
                .addOptionGroup(setupInputGroup())
                .addOption(Option.builder("n").longOpt("number")
                        .desc("Number of spam(default: 6 if keyboard input is enabled)")
                        .hasArg().argName("number").build())
                .addOption("s", "sendKey", true, "Select key to send(default: enter)")
                .addOption("i", "interval", true, "Interval between spam(default: 0.2s)");
    }

    private OptionGroup setupInputGroup() {
        OptionGroup inputGroup = new OptionGroup();
        inputGroup.setRequired(true); // At least one of these options must be specified
        inputGroup.addOption(new Option("f", "file", true, "Input file"))
                .addOption(new Option("k", "keyboard", true,
                        "Use keyboard to type a random words take the length of random words"));
        return inputGroup;
    }

    public void process(String... args) {
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("h")) {
                printHelp();
            }
            if (commandLine.hasOption("v")) {
                showVersion();
            }
            if (commandLine.hasOption("d")) {
                DebugController.getInstance().setDebug(true);
            }
            if (commandLine.hasOption("f")) {
                mainController.getTyperController().setInputFile(commandLine.getOptionValue("f"));
            }
            if (commandLine.hasOption("k")) {
                mainController.getTyperController().setKeyboardInput(true);
                mainController.getTyperController().setRandomWordsLength(
                        Integer.parseInt(commandLine.getOptionValue("k")));
            }
            if (commandLine.hasOption("n")) {
                mainController.getTyperController().setNumber(Integer.parseInt(commandLine.getOptionValue("n")));
            }
            if (commandLine.hasOption("s")) {
                mainController.getTyperController().setSendKey(commandLine.getOptionValue("s").charAt(0));
            }
            if (commandLine.hasOption("i")) {
                mainController.getTyperController().setInterval(
                        Double.parseDouble(commandLine.getOptionValue("i")));
            }
        } catch (ParseException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            printHelp();
        }
    }

    private void showVersion() {
        System.out.println(MainController.VERSION);
        System.exit(0);
    }

    private void printHelp() {
        new HelpFormatter().printHelp("java -jar jspamtyper.jar", options);
        System.exit(0);
    }
}
