package org.spoon.tracability;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.spoon.tracability.shared.constants.OptionsCli;
import org.spoon.tracability.shared.utils.ConfigOptionsParam;
import org.spoon.tracability.spoon.processors.ClassProcessor;
import org.spoon.tracability.spoon.processors.LogGeneratorProcessor;

import java.io.File;

public class Main {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Main.class);
    private static String projectPath;
    private static String outputJrePath;
    private static String maven;

    private static String spoonedOutput;

    public static void main(String[] args) {
        LOGGER.info("Start the program");
        configurationCli(args);
        LOGGER.info("projectPath: {}", projectPath);
        LOGGER.info("outputJrePath: {}", outputJrePath);
        LOGGER.info("maven: {}", maven);
        boolean isMavenProject = Boolean.parseBoolean(maven);
        ClassProcessor classProcessor = new ClassProcessor();
        LogGeneratorProcessor logGeneratorProcessor = new LogGeneratorProcessor(projectPath, isMavenProject);
        logGeneratorProcessor.apply(classProcessor);
        String spoonedOutputPath;
        if (OptionsCli.SPOONED_OUTPUT_RESULT != null) {
            spoonedOutputPath = OptionsCli.SPOONED_OUTPUT_RESULT + File.separator + "spooned";
        } else {
            spoonedOutputPath = projectPath + File.separator + "spooned";
        }
        LOGGER.info("Spoon output: {}", spoonedOutputPath);
        LOGGER.info("End the program");
    }

    private static void configurationCli(String[] args) {
        LOGGER.info("Start the configuration of the CLI");
        try {
        ConfigOptionsParam configOptionsParam = new ConfigOptionsParam();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(configOptionsParam.getOptions(), args);
        projectPath = commandLine.getOptionValue(OptionsCli.PROJECT_PATH);
        outputJrePath = commandLine.getOptionValue(OptionsCli.OUTPUT_JRE_PATH);
        maven = commandLine.getOptionValue(OptionsCli.MAVEN);
        spoonedOutput = commandLine.getOptionValue(OptionsCli.SPOONED_OUTPUT);
        if (spoonedOutput != null) {
            OptionsCli.SPOONED_OUTPUT_RESULT = spoonedOutput;
        }
        boolean helpMode = commandLine.hasOption(OptionsCli.HELP);// args.length == 0
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Spoon-Logging", configOptionsParam.getOptions(), true);
            System.exit(0);
        }
        } catch (ParseException e) {
            LOGGER.error("Error while parsing the command line arguments", e);
        }
    }
}