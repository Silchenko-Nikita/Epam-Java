package com.epam.rd.autocode.assessment.appliances.service.impl;

import com.epam.rd.autocode.assessment.appliances.model.OrderRow;
import com.epam.rd.autocode.assessment.appliances.repository.OrderRowRepository;
import com.epam.rd.autocode.assessment.appliances.service.OrderRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRowServiceImpl implements OrderRowService {
    private final OrderRowRepository orderRowRepository;

    @Autowired
    public OrderRowServiceImpl(OrderRowRepository orderRowRepository) {
        this.orderRowRepository = orderRowRepository;
    }

    @Override
    public List<OrderRow> getAllOrderRows() {
        return orderRowRepository.findAll();
    }

    @Override
    public OrderRow getOrderRowById(Long id) {
        return orderRowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderRow not found with ID: " + id));
    }

    @Override
    public OrderRow saveOrderRow(OrderRow order) {
        return orderRowRepository.save(order);
    }

    @Override
    public void deleteOrderRowById(Long id) {
        orderRowRepository.deleteById(id);
    }
}
