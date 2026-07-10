package com.kino.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReleaseDateValidationImpl implements ConstraintValidator<ReleaseDateValidation, LocalDate> {
    private String startDate;

    @Override
    public void initialize(ReleaseDateValidation constraintAnnotation) {
        startDate = constraintAnnotation.startDate();
    }
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context){
        LocalDate currentDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
     return currentDate.isBefore(localDate);
    }
}
