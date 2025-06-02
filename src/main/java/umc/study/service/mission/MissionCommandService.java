package umc.study.service.mission;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {

    Mission createMission(MissionRequestDTO.CreateMissionDTO request);

    Mission acceptMission(MissionRequestDTO.AcceptMissionDTO request);

    boolean existsById(Long missionId);

    boolean existsByMemberAndMission(Long memberId, Long missionId);

    Page<MemberMission> getMyMissionList(int page);

    Mission completeMission(Long missionId);
}
