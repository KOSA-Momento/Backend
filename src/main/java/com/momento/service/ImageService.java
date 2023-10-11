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
import java.util.List;


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
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (StringUtils.hasLength(oriImgName)) {
            imgName = fileService.uploadFile(imageLocation, oriImgName,
                    imageFile.getBytes());
            imgUrl = "/images/product/" + imgName;
        }
        // 상품 이미지 정보 저장
        image.updateImage(oriImgName, imgUrl);
        imageRepository.save(image);
    }

    public void updateImage(Long productId, MultipartFile imageFile) throws Exception {
        if (!imageFile.isEmpty()) {
            List<Image> savedImages = imageRepository.findByProductIdOrderByContentUrlDesc(productId);

            if (savedImages.isEmpty()) {
                throw new EntityNotFoundException("Images not found for product with ID: " + productId);
            }

            // 기존 이미지 삭제
            for (Image savedImage : savedImages) {
                String imageName = savedImage.getImgName();
                if (imageName != null && !imageName.isEmpty()) {
                    String imagePath = imageLocation + "/" + imageName;
                    fileService.deleteFile(imagePath);
                }
            }

            String originalFileName = imageFile.getOriginalFilename();
            String uploadedFileName = fileService.uploadFile(imageLocation, originalFileName, imageFile.getBytes());
            String imageUrl = "/images/product/" + uploadedFileName;

            // 이미지 정보 업데이트
            for (Image savedImage : savedImages) {
                savedImage.updateImage(originalFileName, imageUrl);
            }

            // 이미지 정보 저장 (옵션 - 필요하면 사용)
            // imageRepository.saveAll(savedImages);
        }
    }
}


//    public void updateImage(Integer productId, MultipartFile imageFile) throws Exception{
//        if(!imageFile.isEmpty()){
//            Image savedImage = imageRepository.findById(productId)
//                    .orElseThrow(EntityNotFoundException::new);


//    public void updateImage(Integer productId, MultipartFile imageFile) throws Exception {
//        if (!imageFile.isEmpty()) {
//            // 기존 이미지 정보 가져오기
//            List<Image> savedImages = imageRepository.findByProductContentIdProductIdOrderByProductContentIdProductIdAsc(productId);
//
//            if (!savedImages.isEmpty()) {
//                Image savedImage = savedImages.get(0); // 기존 이미지는 여러 개가 아니라면 첫 번째 이미지를 사용
//
//                // 기존 이미지 파일 삭제
//                if (!org.springframework.util.StringUtils.isEmpty(savedImage.getImgName())) {
//                    fileService.deleteFile(imageLocation + "/" + savedImage.getImgName());
//                }
//
//                String oriImgName = imageFile.getOriginalFilename();
//                String imgName = fileService.uploadFile(imageLocation, oriImgName, imageFile.getBytes());
//                String imgUrl = "/images/product/" + imgName;
//
//                // 이미지 정보 업데이트
//                savedImage.updateImage(oriImgName, imgName, imgUrl);
//
//                // 이미지 정보 저장
//                imageRepository.save(savedImage);
//            }
//        }
//    }