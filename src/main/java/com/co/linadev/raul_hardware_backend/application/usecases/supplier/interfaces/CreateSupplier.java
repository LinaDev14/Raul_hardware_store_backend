package com.co.linadev.raul_hardware_backend.application.usecases.supplier.interfaces;


import com.co.linadev.raul_hardware_backend.domain.dtos.SupplierDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CreateSupplier {
    Mono<SupplierDTO> create(SupplierDTO supplierDTO);
}