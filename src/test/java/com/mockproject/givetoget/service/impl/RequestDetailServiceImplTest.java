package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.ImageEntity;
import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.response.RequestDetailResponse;
import com.mockproject.givetoget.utils.Utils;
import com.mockproject.givetoget.utils.exception.NoDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestDetailServiceImplTest {

    @InjectMocks
    private RequestDetailServiceImpl requestDetailService; // Real service instance

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Utils utils;

    @Test
    void findRequestDetailById_Success() {
        int id = 1;

        // Mock RequestEntity
        RequestEntity mockRequestEntity = new RequestEntity();
        mockRequestEntity.setId(id);
        mockRequestEntity.setTitle("Test Request");
        mockRequestEntity.setDescription("Test Description");
        mockRequestEntity.setCreateDate(LocalDateTime.now());
        mockRequestEntity.setItem(Collections.singletonList(
                new ItemEntity(1, "Item1", "description", 3, 3, mockRequestEntity)
        ));
        mockRequestEntity.setImageEntities(Collections.singletonList(
                new ImageEntity(1, "image.jpg", mockRequestEntity)
        ));

        // Mock the repository to return null
        when(requestRepository.findByIdAndStatus_Status(id, "OPENING")).thenReturn(mockRequestEntity);

        // Mock ModelMapper
        RequestDetailResponse mockResponse = new RequestDetailResponse();
        mockResponse.setId(id);
        mockResponse.setTitle("Test Request");
        mockResponse.setDescription("Test Description");
        when(modelMapper.map(mockRequestEntity, RequestDetailResponse.class)).thenReturn(mockResponse);

        // Mock Utils
        when(utils.formatDateTime(any(LocalDateTime.class))).thenReturn("2024-12-02 12:00:00");
        when(utils.convertAddressToString(any(RequestEntity.class))).thenReturn("123 Test Address");

        // Act
        RequestDetailResponse response = requestDetailService.findRequestDetailById(id);

        // Assert
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Test Request", response.getTitle());
        assertEquals("Test Description", response.getDescription());
        assertEquals("2024-12-02 12:00:00", response.getCreateDate());
        assertEquals("123 Test Address", response.getAddress());
        assertEquals(1, response.getImages().size());
        assertEquals("image.jpg", response.getImages().get(0).getImageName());
        assertEquals(1, response.getItems().size());
        assertEquals(3, response.getItems().get("Item1"));
    }

    @Test
    void findRequestDetailById_NoData() {
        int id = 1;
        // Mock the repository to return null
        when(requestRepository.findByIdAndStatus_Status(id, "OPENING")).thenReturn(null);

        // Act & Assert:
        try {
            requestDetailService.findRequestDetailById(id);
        } catch (NoDataException e) {
            assertEquals("requestEntity is null", e.getMessage());
        }
    }
}
