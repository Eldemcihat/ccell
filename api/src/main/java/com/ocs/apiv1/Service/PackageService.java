package com.ocs.apiv1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocs.apiv1.Models.Pack;
import com.ocs.apiv1.Repository.PackageRepository;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Pack> listAll() {
        return packageRepository.findAll();
    }

    public Pack save(Pack pack) {
        return packageRepository.save(pack);
    }

    public Pack get(Integer id) {
        return packageRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        packageRepository.deleteById(id);
    }

}
