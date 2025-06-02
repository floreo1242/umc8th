package umc.study.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResponseDTO {
        Long missionId;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
        Long storeId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcceptMissionDTO {
        Long missionId;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
        Long storeId;
    }
}
