package group.bigone.api.common.annotation;


import group.bigone.api.common.constants.ProcessStepCode;
import group.bigone.api.common.constants.Step;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationProcessStep {
    ProcessStepCode processStepCode()   default ProcessStepCode.ELSE;
    Step step()     default Step.ONGOING;
}
