package com.mini_project.productservice.productservice.model.dto;

import com.mini_project.productservice.productservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductStatusDTO {
    private Long productCatalogRequestId;
    private RequestStatus status;
}
