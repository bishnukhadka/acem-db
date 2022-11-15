package com.acem.db.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static Optional<List<String>> validate(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        List<String> violations = validator
                .validate(object)
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .collect(Collectors.toList());

        return violations.isEmpty() ? Optional.empty() : Optional.of(violations);
    }
}
