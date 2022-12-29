package org.spoon.tracability.spoon.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtComment;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;

public class ClassProcessor extends AbstractProcessor<CtClass> {
    @Override
    public void process(CtClass ctClass) {
        final CtTypeReference<org.slf4j.Logger> loggerRef = getFactory().Code().createCtTypeReference(org.slf4j.Logger.class);
        final CtTypeReference<org.slf4j.LoggerFactory> loggerFactoryRef = getFactory().Code().createCtTypeReference(org.slf4j.LoggerFactory.class);

        final CtField<org.slf4j.Logger> loggerField = getFactory().Core().createField();
        loggerField.<CtField<org.slf4j.Logger>>setSimpleName("logger");
        loggerField.<CtField<org.slf4j.Logger>>setType(loggerRef);
        loggerField.<CtField<org.slf4j.Logger>>addModifier(ModifierKind.PRIVATE);
        loggerField.<CtField<org.slf4j.Logger>>addModifier(ModifierKind.STATIC);
        loggerField.<CtField<org.slf4j.Logger>>addModifier(ModifierKind.FINAL);

        CtComment autoGeneratedComment = getFactory().createComment();
        autoGeneratedComment.setContent("Automatically generated by Spoon");
        loggerField.addComment(autoGeneratedComment);

        String expression = loggerFactoryRef+".getLogger(" + ctClass.getSimpleName() + ".class.getName())";
        final CtCodeSnippetExpression<org.slf4j.Logger> loggerExpression = getFactory().Code().createCodeSnippetExpression(expression);
        loggerField.setDefaultExpression(loggerExpression);
        ctClass.addFieldAtTop(loggerField);
    }
}
