package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.mission.MissionCommandService;
import umc.study.validation.annotation.AlreadyAcceptedMission;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.mission.MissionRequestDTO;
import umc.study.web.dto.mission.MissionResponseDTO;

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

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API",
            description = "내가 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH300", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),

    })
    @GetMapping("/me")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMyMissionList(@RequestParam(name = "page") @ValidPage Integer page) {
        int internalPage = page - 1;
        Page<MemberMission> missionsList = missionCommandService.getMyMissionList(internalPage);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missionsList));
    }

    @Operation(
            summary = "미션 완료 API",
            description = "미션을 완료하는 API입니다. 미션 ID를 path variable로 전달해주세요."
    )
    @PostMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.CompleteMissionDTO> completeMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(MissionConverter.toCompleteMissionDTO(missionCommandService.completeMission(missionId)));
    }
}
