package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Employee;
import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> getAllManufacturers();
    Manufacturer getManufacturerById(Long id);
    Manufacturer saveManufacturer(Manufacturer manufacturer);
    void deleteManufacturerById(Long id);

    Page<Manufacturer> getManufacturersPage(Pageable pageable);
}
