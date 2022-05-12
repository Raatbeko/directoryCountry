package com.example.directorycountry.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.directorycountry.dto.file.request.FileRequest;
import com.example.directorycountry.dto.file.response.FileResponse;
import com.example.directorycountry.entity.FileEntity;
import com.example.directorycountry.enums.FileType;
import com.example.directorycountry.exception.FileNotFoundException;
import com.example.directorycountry.repository.FileRepository;
import com.example.directorycountry.service.FileService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    final FileRepository fileRepository;

    final static String CLOUDINARY_URL = "CLOUDINARY_URL=cloudinary://379513361635134:yG00u8tW6g3_Hv1OK0QpVj7ZM0w@doltdryzx";

    @Override
    public FileResponse save(FileRequest fileRequest) {
        File file;
        try {
            file = Files.createTempFile(System.currentTimeMillis() + "", Objects.requireNonNull(fileRequest.getMultipartFile().getOriginalFilename())
                    .substring(fileRequest.getMultipartFile().getOriginalFilename().length()-4)).toFile();
            fileRequest.getMultipartFile().transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap());

            FileEntity fileEntity = FileEntity.builder()
                    .fileType(FileType.IMG)
                    .name(fileRequest.getMultipartFile().getName())
                    .url((String)uploadResult.get("url") )
                    .build();
            fileRepository.save(fileEntity);

            return FileResponse.builder()
                    .id(fileEntity.getId())
                    .url(fileEntity.getUrl()).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //todo null?
        return null;
    }

    @Override
    public List<FileResponse> getAll() {
        List<FileEntity> fileEntities = fileRepository.findAll();
        List<FileResponse> fileResponses = new ArrayList<>();

        for (FileEntity fileEntity : fileEntities) {

            fileResponses.add(FileResponse.builder()
                    .id(fileEntity.getId())
                    .url(fileEntity.getUrl()).build());

        }

        return fileResponses;
    }

    @Override
    public FileResponse findById(Long id) {
        FileEntity fileEntity = fileRepository.getById(id);
        if (fileEntity == null) throw new FileNotFoundException("file not found", HttpStatus.NOT_FOUND);
        return FileResponse.builder()
                .id(fileEntity.getId())
                .url(fileEntity.getUrl())
                .build();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
