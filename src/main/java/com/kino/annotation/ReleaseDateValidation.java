package com.kino.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateValidationImpl.class)
public @interface ReleaseDateValidation {
    String startDate();
    String errorMessage() default "{com.kino.annotation.ReleaseDateValidation.errorMessage}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
//push, дописать тесты

