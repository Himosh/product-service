package com.mini_project.model.dto;

import com.mini_project.model.enums.RequestStatus;
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
