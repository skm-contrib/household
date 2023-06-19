package ua.store.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.store.domain.factory.ReviewFactory;
import ua.store.domain.model.Review;
import ua.store.domain.repository.ReviewRepository;
import ua.store.domain.service.ReviewService;
import ua.store.web.dto.ReviewDTORequest;
import ua.store.web.dto.ReviewDTOResponse;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewFactory factory;

    @Override
    public ReviewDTOResponse add(ReviewDTORequest request) {
        Review reviewToSave = factory.fromDto(request);
        return factory.toDto(repository.save(reviewToSave));
    }

    @Override
    public ReviewDTOResponse update(ReviewDTORequest request, int id) {
        checkExists(id);

        Review reviewToUpdate = factory.fromDto(request);
        reviewToUpdate.setId(id);

        return factory.toDto(repository.save(reviewToUpdate));
    }

    @Override
    public String delete(int id) {
        checkExists(id);

        repository.deleteById(id);
        return "Review with id - " + id + " has been deleted";
    }

    private void checkExists(int id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Cannot find review with id: " + id);
    }
}
