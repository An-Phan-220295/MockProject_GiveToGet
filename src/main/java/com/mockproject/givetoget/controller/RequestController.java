package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.repository.ItemRepository;
import com.mockproject.givetoget.repository.RequestRepository;
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

    private ImageService imageService;

    @PostMapping("/v1/givenrequest/create")
    public String createRequest(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("itemNames") List<String> itemNames,
            @RequestParam("quantities") List<Integer> quantities
    ) {
        try {
            // Save image file and get file name
            String fileName = image.getOriginalFilename();
            imageService.saveImgItem(image);

            // Create request entity
            RequestEntity request = RequestEntity.builder()
                    .title(title)
                    .description(description)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .itemNames(String.join(", ", itemNames))
                    .build();

            request = requestRepository.save(request);

            // Create and save items
            List<ItemEntity> items = new ArrayList<>();
            for (int i = 0; i < itemNames.size(); i++) {
                ItemEntity item = ItemEntity.builder()
                        .itemName(itemNames.get(i))
                        .quantity(quantities.get(i))
                        .currentQuantity(quantities.get(i)) // Initialize currentQuantity as 0
                        .request(request)
                        .build();
                items.add(item);
            }
            itemRepository.saveAll(items);

            // Update request with image file name
            request.setItemNames(fileName); // Assuming itemNames column is used for the image file name
            requestRepository.save(request);

            return "Request created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create request: " + e.getMessage();
        }
    }
}
