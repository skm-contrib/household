package ua.store.domain.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface ImageService {
    String save(MultipartFile file);

    Resource get(String name) throws FileNotFoundException;

    String save(String name, MultipartFile file);
}
