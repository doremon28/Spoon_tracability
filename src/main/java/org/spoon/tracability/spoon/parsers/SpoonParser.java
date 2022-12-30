package org.spoon.tracability.spoon.parsers;

import org.slf4j.Logger;
import org.spoon.tracability.parser.Parser;
import org.spoon.tracability.shared.constants.OptionsCli;
import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.processing.Processor;
import spoon.reflect.declaration.CtClass;

import java.io.File;

public class SpoonParser extends Parser<Launcher> {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SpoonParser.class);

    private void setLauncher(String sourceOutputPath, String binaryOutputPath,
                            boolean autoImports, boolean commentsEnabled) {
        // create launcher
        if (isMavenProject()) {
            parser = new MavenLauncher(getProjectPath(), MavenLauncher.SOURCE_TYPE.APP_SOURCE);
            LOGGER.info("Maven project detected");
        } else {
            parser = new Launcher();
            LOGGER.info("Non-maven project detected");
        }
        parser.addInputResource(getProjectSrcPath()); // set project source path
        parser.getEnvironment().setSourceClasspath(new String[] {getProjectBinPath()}); // set project classpath
        parser.setSourceOutputDirectory(sourceOutputPath); // set generated source code directory path
        parser.setBinaryOutputDirectory(binaryOutputPath); // set generated binary code directory path
        parser.getEnvironment().setAutoImports(autoImports); // set auto imports
        parser.getEnvironment().setCommentEnabled(commentsEnabled); // set comments enabled/ set should compile
    }
    public SpoonParser(String projectPath, boolean isMavenProject) {
        super(projectPath, isMavenProject);
    }

    @Override
    public void configure() {
        if (OptionsCli.SPOONED_OUTPUT_RESULT != null) {
            String sourceOutputPath = OptionsCli.SPOONED_OUTPUT_RESULT + File.separator + "spooned" + File.separator + "src" + File.separator;
            String binaryOutputPath = OptionsCli.SPOONED_OUTPUT_RESULT + File.separator + "spooned" + File.separator + "bin" + File.separator;
            setLauncher(sourceOutputPath, binaryOutputPath, true, true);
        } else {
            setLauncher(getProjectPath()+"/spooned/src", getProjectPath()+"/spooned/bin",
                    true, true);
        }

    }

    public void addProcessor(Processor<CtClass> processor) {
        parser.addProcessor(processor);
    }

    public void run() {
        parser.run();
    }
}
