package com.co.linadev.raul_hardware_backend.application.usecases.supplier.implementations;


import com.co.linadev.raul_hardware_backend.application.mappers.SupplierMapper;
import com.co.linadev.raul_hardware_backend.application.usecases.supplier.interfaces.FindAllSuppliers;
import com.co.linadev.raul_hardware_backend.domain.dtos.SupplierDTO;
import com.co.linadev.raul_hardware_backend.domain.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindAllSuppliersUseCase implements FindAllSuppliers {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public Flux<SupplierDTO> findAll() {
        return supplierRepository.findAll().map(supplierMapper.mapToDto());
    }
}
