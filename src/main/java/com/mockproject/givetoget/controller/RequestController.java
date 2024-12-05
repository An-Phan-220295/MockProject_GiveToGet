package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.entity.ImageEntity;
import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.entity.StatusEntity;
import com.mockproject.givetoget.repository.ItemRepository;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.repository.StatusRepository;
import com.mockproject.givetoget.request.CreateRequestData;
import com.mockproject.givetoget.request.ItemRequest;
import com.mockproject.givetoget.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ImageService imageService;

    @Autowired
    private StatusRepository statusRepository;

    @PostMapping("/v1/givenrequest/create")
    public String createRequest(
            @RequestPart("data") CreateRequestData data,
            @RequestPart("files") List<MultipartFile> files
    ) {
        try {
            // Save all files
            List<String> fileNames = imageService.saveMultipleImgItems(files);

            List<ImageEntity> images = new ArrayList<>();
            for (String fileName : fileNames) {
                images.add(ImageEntity.builder().imageName(fileName).build());
            }

            // Combine item names
            String items = data.getItem().stream()
                    .map(ItemRequest::getItemName)
                    .reduce("", (acc, name) -> acc + ", " + name);

            // Create request entity
            RequestEntity request = RequestEntity.builder()
                    .title(data.getTitle())
                    .description(data.getDescription())
                    .status(statusRepository.findById(1))
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .itemNames(items)
                    .imageEntities(images)
                    .build();

            request = requestRepository.save(request);

            // Create and save items
            List<ItemEntity> itemEntities = new ArrayList<>();
            for (ItemRequest item : data.getItem()) {
                ItemEntity itemEntity = ItemEntity.builder()
                        .itemName(item.getItemName())
                        .quantity(item.getQuantities())
                        .currentQuantity(item.getQuantities())
                        .build();
                itemEntities.add(itemEntity);
            }

            request.setItem(itemEntities);
            requestRepository.save(request);

            return "Request created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create request: " + e.getMessage();
        }
    }


}
