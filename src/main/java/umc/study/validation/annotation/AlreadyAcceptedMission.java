package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.CategoryExistValidator;
import umc.study.validation.validator.MissionAlreadyAcceptedValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionAlreadyAcceptedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyAcceptedMission {

    String message() default "이미 수락한 미션입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
