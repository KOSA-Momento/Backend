package com.momento.service;

import com.momento.entity.Image;
import com.momento.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    @Value("${imageLocation}")
    private String imageLocation;

    private final ImageRepository imageRepository;

    private final FileService fileService;

    public void saveImage(Image image, MultipartFile imageFile) throws Exception {
        String oriImgName = imageFile.getOriginalFilename();
        String imageName = "";
        String imageUrl = "";

        // 파일 업로드
        if (StringUtils.hasLength(oriImgName)) {
            imageName = fileService.uploadFile(imageLocation, oriImgName,
                    imageFile.getBytes());
            imageUrl = "/images/product/" + imageName;
        }
        // 상품 이미지 정보 저장
        image.updateImage(oriImgName, imageName, imageUrl);
        imageRepository.save(image);
    }

    public void updateImage(Long imageId, MultipartFile imageFile) throws Exception{
        if (!imageFile.isEmpty()) {
            Image savedImage = imageRepository.findById(imageId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if (StringUtils.hasLength(savedImage.getImgName())) {
                fileService.deleteFile(imageLocation+"/"+
                        savedImage.getImgName());
            }

            String oriImgName = imageFile.getOriginalFilename();
            String imageName = fileService.uploadFile(imageLocation, oriImgName, imageFile.getBytes());
            String imageUrl = "/images/item/" + imageName;
            savedImage.updateImage(oriImgName, imageName, imageUrl);
        }
    }















//    public void updateImage(Long productId, MultipartFile imageFile) throws Exception {
//        if (!imageFile.isEmpty()) {
//            List<Image> savedImages = imageRepository.findByProductIdOrderByImgUrlDesc(productId);
//
//            if (savedImages.isEmpty()) {
//                throw new EntityNotFoundException("Images not found for product with ID: " + productId);
//            }
//
//            // 기존 이미지 삭제
//            for (Image savedImage : savedImages) {
//                String imageName = savedImage.getImgName();
//                if (imageName != null && !imageName.isEmpty()) {
//                    String imagePath = imageLocation + "/" + imageName;
//                    fileService.deleteFile(imagePath);
//                }
//            }
//
//            String originalFileName = imageFile.getOriginalFilename();
//            String uploadedFileName = fileService.uploadFile(imageLocation, originalFileName, imageFile.getBytes());
//            String imageUrl = "/images/product/" + uploadedFileName;
//
//            // 이미지 정보 업데이트
//            for (Image savedImage : savedImages) {
//                savedImage.updateImage(originalFileName, imageUrl);
//            }
//
//            // 이미지 정보 저장 (옵션 - 필요하면 사용)
//            // imageRepository.saveAll(savedImages);
//        }
//    }
}