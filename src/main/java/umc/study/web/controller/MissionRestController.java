package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.AlreadyAcceptedMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateMissionResponseDTO> createMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request) {
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionDTO(mission));
    }

    @PostMapping("/accept")
    public ApiResponse<MissionResponseDTO.AcceptMissionDTO> acceptMission(@RequestBody @Valid @AlreadyAcceptedMission MissionRequestDTO.AcceptMissionDTO request) {
        Mission mission = missionCommandService.acceptMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAcceptMissionDTO(mission));
    }
}
