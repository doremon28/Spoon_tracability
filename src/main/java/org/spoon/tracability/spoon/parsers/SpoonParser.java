package org.spoon.tracability.spoon.parsers;

import org.spoon.tracability.parser.Parser;
import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.processing.Processor;
import spoon.reflect.declaration.CtClass;

public class SpoonParser extends Parser<Launcher> {

    private void setLauncher(String sourceOutputPath, String binaryOutputPath,
                            boolean autoImports, boolean commentsEnabled) {
        // create launcher
        if (isMavenProject()) {
            parser = new MavenLauncher(getProjectPath(), MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        } else {
            parser = new Launcher();
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
        setLauncher(getProjectPath()+"/spooned/src", getProjectPath()+"/spooned/src",
                true, true);
    }

    public void addProcessor(Processor<CtClass> processor) {
        parser.addProcessor(processor);
    }

    public void run() {
        parser.run();
    }
}
