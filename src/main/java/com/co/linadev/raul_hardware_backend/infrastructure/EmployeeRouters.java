package com.co.linadev.raul_hardware_backend.infrastructure;


import com.co.linadev.raul_hardware_backend.application.usecases.employee.implementations.*;
import com.co.linadev.raul_hardware_backend.domain.dtos.EmployeeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EmployeeRouters {

    @Bean
    public RouterFunction<ServerResponse> createEmployeeRouterFunction(CreateEmployeeUseCase createEmployeeUseCase){
        return route(POST("api/employees/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(EmployeeDTO.class)
                        .flatMap(createEmployeeUseCase::create)
                        .flatMap(response -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteEmployeeRouterFunction(DeleteEmployeeUseCase deleteEmployeeUseCase){
        return route(DELETE("api/employees/delete/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(deleteEmployeeUseCase
                                        .delete(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> findAllEmployeesRouterFunction(FindAllEmployeesUseCase findAllEmployeesUseCase){
        return route(GET("api/employees"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(findAllEmployeesUseCase
                                        .findAll(), EmployeeDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> findEmployeeByIdRouterFunction(FindEmployeeByIdUseCase findEmployeeByIdUseCase){
        return route(GET("api/employees/find/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(findEmployeeByIdUseCase
                                        .findById(request.pathVariable("id")), EmployeeDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> fillEmployeeDataRouterFunction(FillEmployeeDataUseCase fillEmployeeDataUseCase){
        return route(POST("api/employees/fillData").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(new ParameterizedTypeReference<List<EmployeeDTO>>() {})
                        .flatMapMany(fillEmployeeDataUseCase::fillData)
                        .collect(Collectors.toList())
                        .flatMap(response -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAllDataEmployeeRouterFunction(DeleteAllDataEmployeeUseCase deleteAllDataEmployeeUseCase){
        return route(
                DELETE("/api/delete_all_employees"),
                request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(deleteAllDataEmployeeUseCase.deleteAll(),Void.class))
        );
    }

}
