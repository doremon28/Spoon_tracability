package org.spoon.tracability.spoon.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class LogGeneratorProcessor extends SpoonProcessor{
    public LogGeneratorProcessor(String projectPath, boolean isMavenProject) {
        super(projectPath, isMavenProject);
    }

    public void apply(AbstractProcessor<CtClass> logGenerator) {
        parser.addProcessor(logGenerator);
        parser.run();
    }
}
