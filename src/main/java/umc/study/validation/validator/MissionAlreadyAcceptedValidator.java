package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.service.mission.MissionCommandService;
import umc.study.validation.annotation.AlreadyAcceptedMission;
import umc.study.web.dto.mission.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionAlreadyAcceptedValidator implements ConstraintValidator<AlreadyAcceptedMission, MissionRequestDTO.AcceptMissionDTO> {

    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(AlreadyAcceptedMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.AcceptMissionDTO request, ConstraintValidatorContext context) {
        return missionCommandService.existsByMemberAndMission(1L, request.getMissionId());
    }
}
