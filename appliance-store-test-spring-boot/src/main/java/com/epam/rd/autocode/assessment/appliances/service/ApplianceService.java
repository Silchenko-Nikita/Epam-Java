package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplianceService {
    List<Appliance> getAllAppliances();
    Appliance getApplianceById(Long id);
    Appliance saveAppliance(Appliance appliance);
    void deleteApplianceById(Long id);

    Page<Appliance> getAppliancesPage(Pageable pageable);
}
