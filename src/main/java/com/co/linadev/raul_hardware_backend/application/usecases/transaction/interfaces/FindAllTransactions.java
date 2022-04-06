package com.co.linadev.raul_hardware_backend.application.usecases.transaction.interfaces;

import com.example.raul_hardware_store.domain.dtos.TransactionDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface FindAllTransactions {
    Flux<TransactionDTO> findAll();
}
