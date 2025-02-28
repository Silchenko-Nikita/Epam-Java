package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.OrderRow;

import java.util.List;

public interface OrderRowService {
    List<OrderRow> getAllOrderRows();
    OrderRow getOrderRowById(Long id);
    OrderRow saveOrderRow(OrderRow orderRow);
    void deleteOrderRowById(Long id);
}