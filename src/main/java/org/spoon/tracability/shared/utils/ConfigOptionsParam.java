package org.spoon.tracability.shared.utils;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.spoon.tracability.shared.constants.OptionsCli;

public class ConfigOptionsParam {
    private final Option projectPath;
    private final Option outputJrePath;

    private final Option help;

    private final Option maven;

    private  final Options options;

    public Options getOptions() {
        return options;
    }

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
        this.maven = Option.builder(OptionsCli.MAVEN)
                .desc(OptionsCli.MAVEN_DESCRIPTION)
                .longOpt(OptionsCli.MAVEN_SHORT)
                .build();
        this.options = new Options();
        this.options.addOption(help);
        this.options.addOption(projectPath);
        this.options.addOption(outputJrePath);
        this.options.addOption(maven);
    }
}
