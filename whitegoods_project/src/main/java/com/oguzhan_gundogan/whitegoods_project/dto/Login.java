package com.oguzhan_gundogan.whitegoods_project.dto;

import lombok.Data;

@Data
public class Login {
    private String token;
    private Long customerId;
}
