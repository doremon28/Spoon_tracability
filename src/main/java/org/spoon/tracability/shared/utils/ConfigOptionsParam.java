package org.spoon.tracability.shared.utils;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.spoon.tracability.shared.constants.OptionsCli;

/**
 * The type Config options param.
 */
public class ConfigOptionsParam {
    /**
     * The Project path.
     */
    private final Option projectPath;
    /**
     * The Output jre path.
     */
    private final Option outputJrePath;

    /**
     * The Help.
     */
    private final Option help;

    /**
     * The Maven.
     */
    private final Option maven;

    /**
     * The Spooned output.
     */
    private final Option spoonedOutput;

    /**
     * The Options.
     */
    private  final Options options;

    /**
     * Gets options.
     *
     * @return the options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Instantiates a new Config options param.
     */
    public ConfigOptionsParam() {
        this.help = Option.builder(OptionsCli.HELP_SHORT)
                .longOpt(OptionsCli.HELP)
                .desc(OptionsCli.HELP_DESCRIPTION)
                .build();
        this.projectPath = Option.builder(OptionsCli.PROJECT_PATH_SHORT)
                .longOpt(OptionsCli.PROJECT_PATH)
                .hasArg()
                .desc(OptionsCli.PROJECT_PATH_DESCRIPTION)
                .required(true)
                .build();
        this.outputJrePath = Option.builder(OptionsCli.OUTPUT_JRE_PATH_SHORT)
                .longOpt(OptionsCli.OUTPUT_JRE_PATH)
                .hasArg()
                .desc(OptionsCli.OUTPUT_JRE_PATH_DESCRIPTION)
                .build();
        this.maven = Option.builder(OptionsCli.MAVEN_SHORT)
                .desc(OptionsCli.MAVEN_DESCRIPTION)
                .longOpt(OptionsCli.MAVEN)
                .hasArg()
                .required(true)
                .build();
        spoonedOutput = Option.builder(OptionsCli.SPOONED_OUTPUT_SHORT)
                .longOpt(OptionsCli.SPOONED_OUTPUT)
                .hasArg()
                .desc(OptionsCli.SPOONED_OUTPUT_DESCRIPTION)
                .build();
        this.options = new Options();
        this.options.addOption(help);
        this.options.addOption(projectPath);
        this.options.addOption(outputJrePath);
        this.options.addOption(maven);
        this.options.addOption(spoonedOutput);
    }
}
