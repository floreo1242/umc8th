package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.mission.MissionRequestDTO;
import umc.study.web.dto.mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.CreateMissionResponseDTO toCreateMissionDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResponseDTO.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static MissionResponseDTO.AcceptMissionDTO toAcceptMissionDTO(Mission mission) {
        return MissionResponseDTO.AcceptMissionDTO.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();
    }

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<MemberMission> missionList) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(memberMission -> {
                    Mission mission = memberMission.getMission();
                    return toMissionPreviewDTO(mission);
                })
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
