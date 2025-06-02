package umc.study.service.review;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;

public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReviewDTO request);

    Page<Review> getReviewList(Long storeId, Integer page);
}
