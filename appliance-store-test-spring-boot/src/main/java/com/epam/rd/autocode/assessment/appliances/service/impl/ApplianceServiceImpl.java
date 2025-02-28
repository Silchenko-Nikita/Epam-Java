package com.epam.rd.autocode.assessment.appliances.service.impl;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.repository.ApplianceRepository;
import com.epam.rd.autocode.assessment.appliances.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    private final ApplianceRepository applianceRepository;

    @Autowired
    public ApplianceServiceImpl(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    @Override
    public List<Appliance> getAllAppliances() {
        return applianceRepository.findAll();
    }

    @Override
    public Appliance getApplianceById(Long id) {
        return applianceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appliance not found with ID: " + id));
    }

    @Override
    public Appliance saveAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
    }

    @Override
    public void deleteApplianceById(Long id) {
        applianceRepository.deleteById(id);
    }

    @Override
    public Page<Appliance> getAppliancesPage(Pageable pageable) {
        return applianceRepository.findAll(pageable);
    }
}