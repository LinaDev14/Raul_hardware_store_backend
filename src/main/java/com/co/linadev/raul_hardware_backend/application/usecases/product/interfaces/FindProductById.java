package com.co.linadev.raul_hardware_backend.application.usecases.product.interfaces;

import com.example.raul_hardware_store.domain.dtos.ProductDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface FindProductById {
    Mono<ProductDTO> findById(String id);
}