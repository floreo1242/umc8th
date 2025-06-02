package umc.study.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.EntityNotFoundHandler;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.store.StoreRepository;
import umc.study.web.dto.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundHandler(ErrorStatus.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(review);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundHandler(ErrorStatus.STORE_NOT_FOUND));
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}
