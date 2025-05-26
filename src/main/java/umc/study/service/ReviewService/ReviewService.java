package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReviewDTO request);
}
