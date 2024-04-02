package com.oguzhan_gundogan.whitegoods_project.controller;



import com.oguzhan_gundogan.whitegoods_project.dto.OrderRequest;
import com.oguzhan_gundogan.whitegoods_project.entity.Orders;
import com.oguzhan_gundogan.whitegoods_project.Service.orderservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class Order {

    private final orderservice orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Orders> doOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.doOrder(orderRequest), HttpStatus.OK);
    }
}