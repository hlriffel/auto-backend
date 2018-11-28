package br.edu.senac.auto.s3;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UseS3StorageConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "TRUE".equalsIgnoreCase(context.getEnvironment().getProperty("AUTO_USE_S3_STORAGE"));
    }
}
