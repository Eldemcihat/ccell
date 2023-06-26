package com.ocs.apiv1.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ocs.apiv1.Models.Pack;
import com.ocs.apiv1.Service.PackageService;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("")
    public List<Pack> list() {
        return packageService.listAll();
    }

    @PostMapping("")
    public ResponseEntity<Pack> create(@RequestBody Pack pack) {
        Pack newPackage = packageService.save(pack);
        return new ResponseEntity<Pack>(newPackage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> get(@PathVariable Integer id) {
        Pack pack = packageService.get(id);
        if (pack == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pack, HttpStatus.OK);
        }
    }

    @GetMapping("packageIdName")
    public ResponseEntity<List<PackageResponse>> getPackageNameId() {
        List<Pack> packageList = packageService.listAll();
        if (packageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<PackageResponse> responseList = new ArrayList<>();
            for (Pack pack : packageList) {
                PackageResponse response = new PackageResponse();
                response.setId(pack.getId());
                response.setName(pack.getName());
                responseList.add(response);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pack> update(@PathVariable Integer id, @RequestBody Pack pack) {
        Pack existingPackage = packageService.get(id);
        if (existingPackage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            pack.setId(id);
            Pack updatedPackage = packageService.save(pack);
            return new ResponseEntity<Pack>(updatedPackage, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        Pack existingPackage = packageService.get(id);
        if (existingPackage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            packageService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
