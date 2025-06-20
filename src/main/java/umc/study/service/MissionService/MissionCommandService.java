package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    Mission createMission(MissionRequestDTO.CreateMissionDTO request);

    Mission acceptMission(MissionRequestDTO.AcceptMissionDTO request);

    boolean existsById(Long missionId);

    boolean existsByMemberAndMission(Long memberId, Long missionId);
}
