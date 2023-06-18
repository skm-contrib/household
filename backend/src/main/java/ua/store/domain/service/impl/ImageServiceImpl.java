package ua.store.domain.service.impl;


import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.store.domain.service.ImageService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String save(MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalArgumentException("Please select a file to upload");

        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        return saveFile(filename, file);
    }

    @Override
    public Resource get(String name) throws FileNotFoundException {
        Path imagePath = ResourceUtils.getFile(new FileSystemResource("").getFile().getAbsolutePath() + "/images/" + name).toPath();
        Resource resource = new FileSystemResource(imagePath);
        if (!resource.exists())
            throw new FileNotFoundException("Cannot find image with name: " + name);

        return resource;
    }

    @Override
    public String save(String name, MultipartFile file) {
        saveFile(name, file);
        return name;
    }

    private String saveFile(String filename, MultipartFile file) {
        try {
            String uploadDir = new FileSystemResource("").getFile().getAbsolutePath() + "/images/";

            File dest = new File(uploadDir + filename);
            file.transferTo(dest);

            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while uploading image");
        }
    }
}
