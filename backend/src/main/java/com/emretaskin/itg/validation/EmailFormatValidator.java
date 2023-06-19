package com.emretaskin.itg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailFormatValidator implements ConstraintValidator<EmailFormat, String> {
    @Override
    public void initialize(EmailFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String emailRegex = "^(?i)[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        return email.matches(emailRegex);
    }
}
