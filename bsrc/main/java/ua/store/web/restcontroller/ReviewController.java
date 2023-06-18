package ua.store.web.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.store.domain.service.ReviewService;
import ua.store.web.dto.ReviewDTORequest;
import ua.store.web.dto.ReviewDTOResponse;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTOResponse> addReview(@RequestBody @Valid ReviewDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTOResponse> updateReview(@RequestBody ReviewDTORequest request, @PathVariable int id) {
        return ResponseEntity.ok(reviewService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.delete(id));
    }
}
