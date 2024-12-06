package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.entity.*;
import com.mockproject.givetoget.repository.*;
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
    private WardRepository wardRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ImageService imageService;

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/v1/givenrequest/create")
    public String createRequest(
            @RequestPart("data") CreateRequestData data,
            @RequestPart("files") List<MultipartFile> files
    ) {
        int id_user = 1;
        UserInforEntity user = userRepository.findById(id_user).orElse(null);
        try {
            // Save all files
            List<String> fileNames = imageService.saveMultipleImgItems(files);



            // Combine item names
            String items = data.getItem().stream()
                    .map(ItemRequest::getItemName)
                    .reduce("", (acc, name) -> acc + ", " + name);

            AddressEntity address = AddressEntity.builder()
                    .ward(wardRepository.findById(data.getWard()).orElse(null))
                    .user(user)
                    .street(data.getAddress())
                    .build();
            AddressEntity address1 = addressRepository.save(address);

            // Create request entity
            RequestEntity request = RequestEntity.builder()
                    .title(data.getTitle())
                    .description(data.getDescription())
                    .status(statusRepository.findById(1))
                    .type(true)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .address(address1)
                    .itemNames(items)
                    .user(user)
                    .build();


            request = requestRepository.save(request);





            List<ImageEntity> images = new ArrayList<>();
            for (String fileName : fileNames) {
                images.add(ImageEntity.builder().requestEntity(request).imageName(fileName).build());
            }
            request.setImageEntities(images);
            request = requestRepository.save(request);


            // Create and save items
            List<ItemEntity> itemEntities = new ArrayList<>();
            for (ItemRequest item : data.getItem()) {
                ItemEntity itemEntity = ItemEntity.builder()
                        .itemName(item.getItemName())
                        .quantity(item.getQuantities())
                        .currentQuantity(item.getQuantities())
                        .request(request)
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
