package com.poetry.poetry_documentation_reporting.request;

import lombok.Data;

@Data
public class WithdrawRequest {

    private Long authorId;
    private Double amount;

}
