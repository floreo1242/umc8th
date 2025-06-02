package umc.study.web.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDTO {
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
        @NotNull @ExistStore
        Long storeId;
    }
}
