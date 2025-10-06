package com.blog.blog_project.annotation;

import com.blog.blog_project.validator.NoCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoCode {

    String message() default "Content can not include code pieces.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}