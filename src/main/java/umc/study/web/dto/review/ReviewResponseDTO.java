package umc.study.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResponseDTO {
        Long reviewId;
        String title;
        Float score;
        String body;
        Long memberId;
        Long storeId;
        LocalDateTime createdAt;
    }
}
