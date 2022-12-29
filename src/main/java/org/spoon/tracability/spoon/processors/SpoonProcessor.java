package org.spoon.tracability.spoon.processors;

import org.spoon.tracability.processor.Processor;
import org.spoon.tracability.spoon.parsers.SpoonParser;

public class SpoonProcessor extends Processor<SpoonParser> {

    public SpoonProcessor(String projectPath, boolean isMavenProject) {
        super(projectPath, isMavenProject);
    }


    public void setParser(String projectPath, boolean isMavenProject) {
        parser = new SpoonParser(projectPath, isMavenProject);
    }

    public void setParser(SpoonParser parser) {
        this.parser = parser;
    }
}
