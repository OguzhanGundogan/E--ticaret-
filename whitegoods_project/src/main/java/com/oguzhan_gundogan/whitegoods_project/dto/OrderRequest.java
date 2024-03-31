package com.oguzhan_gundogan.whitegoods_project.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long customerId;

    private List<OrderProduct> orderProductInfoList;

}