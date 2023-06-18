package ua.store.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.store.domain.jwt.JwtService;
import ua.store.domain.model.Review;
import ua.store.domain.repository.ProductRepository;
import ua.store.domain.repository.UserRepository;
import ua.store.web.dto.ReviewDTORequest;
import ua.store.web.dto.ReviewDTOResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ReviewFactory {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final JwtService jwtService;

    public Review fromDto(ReviewDTORequest review) {
        return Review.builder()
                .id(review.getId())
                .product(productRepository.findById(review.getProductId()).orElseThrow())
                .reviewOwner(userRepository.findByEmail(jwtService.extractUsername(review.getJwt())).orElseThrow())
                .text(review.getText())
                .timeOfReview(new Date())
                .build();
    }

    public ReviewDTOResponse toDto(Review review) {
        return ReviewDTOResponse.builder()
                .text(review.getText())
                .owner(userFactory.toDto(review.getReviewOwner()))
                .timestamp(review.getTimeOfReview())
                .build();
    }
}
