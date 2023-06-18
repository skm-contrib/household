package ua.store.domain.service;

import ua.store.web.dto.ReviewDTORequest;
import ua.store.web.dto.ReviewDTOResponse;

public interface ReviewService {
    ReviewDTOResponse add(ReviewDTORequest request);
    ReviewDTOResponse update(ReviewDTORequest request, int id);
    String delete(int id);
}
