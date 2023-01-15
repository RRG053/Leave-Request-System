package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Region;
import com.example.demo.repositories.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region getById(Integer Id) {
        return regionRepository.findById(Id)
        .orElseThrow(() -> new IllegalArgumentException("Region Tidak Ditemukan"));
    }

    @Override
    public Boolean save(Region region) {
        regionRepository.save(region);
        return regionRepository.findById(region.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer Id) {
        regionRepository.deleteById(Id);
        return !regionRepository.findById(Id).isPresent();
    }
    
    
}
