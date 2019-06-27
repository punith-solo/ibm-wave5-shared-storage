package com.stackroute.service;

import com.stackroute.exceptions.PartitionAlreadyExists;
import com.stackroute.exceptions.WarehouseAlreadyExistsException;
import com.stackroute.exceptions.WarehouseNotfound;
import com.stackroute.model.Partition;
import com.stackroute.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    public List<Warehouse> getAllWarehouses();

    public Warehouse saveWarehouse(Warehouse wareHouse) throws WarehouseAlreadyExistsException;

    public Warehouse saveWarehousePart(Warehouse warehouse) throws PartitionAlreadyExists;

    public boolean deleteWarehouse(int id) throws WarehouseNotfound;

    public Warehouse getOneWarehouse(int id) throws WarehouseNotfound;

    public Warehouse updateWarehouse(Warehouse wareHouse) throws WarehouseNotfound;
    // public Partition savePartition(Partition partition) throws WarehouseAlreadyExistsException;

}
