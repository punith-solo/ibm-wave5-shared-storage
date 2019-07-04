package com.stackroute.recommendations.controller.relationcontroller;

import com.stackroute.recommendations.domain.StorageUnit;
import com.stackroute.recommendations.service.HasAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/recommendation")
public class HasAController
{
    HasAService hasAService;

    @Autowired
    public HasAController(HasAService hasAService) {
        this.hasAService = hasAService;
    }

    @PostMapping("/hasa/{warehouseId}/{pid}")
    public StorageUnit storageUnitRelationship(@PathVariable long warehouseId, @PathVariable long pid) {
        return hasAService.createStorageUnitRelationship(warehouseId,pid);

    }
}
