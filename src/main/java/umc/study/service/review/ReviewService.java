package umc.study.service.review;

import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;

public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReviewDTO request);
}
