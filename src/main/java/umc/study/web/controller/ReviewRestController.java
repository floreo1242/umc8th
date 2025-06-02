package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.review.ReviewService;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviewResponseDTO> createReview(@RequestBody @Valid ReviewRequestDTO.CreateReviewDTO request) {
        Review review = reviewService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResponseDTO(review));
    }

    @Operation(
            summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH300", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),

    })
    @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = reviewService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }
}