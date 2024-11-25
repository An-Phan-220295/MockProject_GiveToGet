package com.mockproject.givetoget.service;


import com.mockproject.givetoget.entity.ProvincesEntity;
import com.mockproject.givetoget.reponse.DistrictRespone;
import com.mockproject.givetoget.reponse.ProvinceRespone;
import com.mockproject.givetoget.reponse.WardResponse;
import com.mockproject.givetoget.repository.DistrictRepository;
import com.mockproject.givetoget.repository.ProvinceRepository;
import com.mockproject.givetoget.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository  wardRepository;
    public List<ProvinceRespone> findAllProvinces() {
        // Fetch all provinces from the repository
        List<ProvincesEntity> provinces = provinceRepository.findAll();

        // Convert ProvincesEntity to ProvinceRespone using Java Stream API
        return provinces.stream()
                .map(province -> ProvinceRespone.builder()
                        .code(province.getCode())
                        .name(province.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<DistrictRespone> findAllDistrictByProvinceCode(String provinceCode) {
        return districtRepository.findByProvince_Code(provinceCode).stream()
                .map(district -> DistrictRespone.builder()
                        .code(district.getCode())
                        .name(district.getName())
                        .build())
                .collect(Collectors.toList());
    }


    public List<WardResponse> findAllWardByDistrictCode(String districtCode) {
        return wardRepository.findByDistrict_Code(districtCode).stream()
                .map(ward -> WardResponse.builder()
                        .code(ward.getCode())
                        .name(ward.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
