package com.stackroute.service;

import com.stackroute.exceptions.PartitionAlreadyExists;
import com.stackroute.exceptions.WarehouseAlreadyExistsException;
import com.stackroute.exceptions.WarehouseNotfound;
import com.stackroute.model.Warehouse;
import com.stackroute.repository.PartitionRepository;
import com.stackroute.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    WarehouseRepository warehouseRepository;
    PartitionRepository partitionRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, PartitionRepository partitionRepository) {
        this.warehouseRepository = warehouseRepository;
        this.partitionRepository = partitionRepository;
    }


    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getOneWarehouse(int id) throws WarehouseNotfound {

        boolean status = false;
        Warehouse warehouse = warehouseRepository.findById(id);
        if (warehouse != null) {

            return warehouse;
        } else {
            throw new WarehouseNotfound("warehouse not exists");
        }
    }


    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) throws WarehouseAlreadyExistsException {

        if (warehouseRepository.existsById(warehouse.getId())) {
            throw new WarehouseAlreadyExistsException("warehouse already exists with id:[" + warehouse.getId() + "]");
        }
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return savedWarehouse;
    }

    @Override
    public Warehouse saveWarehousePart(Warehouse warehouse) throws PartitionAlreadyExists {

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return savedWarehouse;
    }


    @Override
    public boolean deleteWarehouse(int id) throws WarehouseNotfound {
        boolean status = false;
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            status = true;
            return status;

        } else {
            throw new WarehouseNotfound("warehouse not exists");
        }

    }


    @Override
    public Warehouse updateWarehouse(Warehouse wareHouse) throws WarehouseNotfound {
        if (warehouseRepository.existsById(wareHouse.getId())) {
            Warehouse savedWarehouse = warehouseRepository.save(wareHouse);
            return savedWarehouse;
        } else {
            throw new WarehouseNotfound("wareHouse not found");
        }

    }

}
