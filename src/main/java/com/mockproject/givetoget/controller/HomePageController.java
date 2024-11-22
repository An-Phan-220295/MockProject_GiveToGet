package com.mockproject.givetoget.controller;


import com.mockproject.givetoget.entity.DistrictEntity;
import com.mockproject.givetoget.entity.ProvincesEntity;
import com.mockproject.givetoget.entity.WardEntity;
import com.mockproject.givetoget.reponse.DistrictRespone;
import com.mockproject.givetoget.reponse.ProvinceRespone;
import com.mockproject.givetoget.reponse.WardResponse;
import com.mockproject.givetoget.repository.DistrictRepository;
import com.mockproject.givetoget.repository.ProvinceRepository;
import com.mockproject.givetoget.repository.WardRepository;
import com.mockproject.givetoget.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomePageController {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private AddressService addressService;

    // Get all provinces
    @GetMapping("/provinces")
    public List<ProvinceRespone> getProvinces() {
        return addressService.findAllProvinces();
    }

    // Get districts by province code
    @GetMapping("/provinces/{province_code}/districts")
    public List<DistrictRespone> getDistricts(@PathVariable String province_code) {
        return addressService.findAllDistrictByProvinceCode(province_code);
    }

    // Get wards by district code
    @GetMapping("/districts/{district_code}/wards")
    public List<WardResponse> getWards(@PathVariable String district_code) {
        return addressService.findAllWardByDistrictCode(district_code);
    }
}

