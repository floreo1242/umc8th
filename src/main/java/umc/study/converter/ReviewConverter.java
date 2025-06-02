package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResponseDTO toCreateReviewResponseDTO(Review review) {
        return ReviewResponseDTO.CreateReviewResponseDTO.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .body(review.getBody())
                .memberId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
