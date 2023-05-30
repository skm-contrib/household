package ua.store.web.restcontroller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.store.domain.service.ImageService;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{name:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String name) throws FileNotFoundException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(imageService.get(name));
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(imageService.save(file));
    }
}