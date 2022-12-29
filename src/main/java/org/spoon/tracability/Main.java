package org.spoon.tracability;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.spoon.tracability.shared.constants.OptionsCli;
import org.spoon.tracability.shared.utils.ConfigOptionsParam;

public class Main {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Main.class);
    private static String projectPath;
    private static String outputJrePath;
    private static String maven;

    public static void main(String[] args) {
        configurationCli(args);
        LOGGER.info("projectPath: {}", projectPath);
        LOGGER.info("outputJrePath: {}", outputJrePath);
        LOGGER.info("maven: {}", maven);
    }

    private static void configurationCli(String[] args) {
        try {
        ConfigOptionsParam configOptionsParam = new ConfigOptionsParam();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(configOptionsParam.getOptions(), args);
        projectPath = commandLine.getOptionValue(OptionsCli.PROJECT_PATH);
        outputJrePath = commandLine.getOptionValue(OptionsCli.OUTPUT_JRE_PATH);
        maven = commandLine.getOptionValue(OptionsCli.MAVEN);
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