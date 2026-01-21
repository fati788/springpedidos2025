package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.ProductoCreateDto;
import com.jaroso.pedidos2026.dtos.ProductoDto;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public ProductoDto create(ProductoCreateDto dto) {
        return null;
    }

    @Override
    public List<ProductoDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<ProductoDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(ProductoDto dto) {

    }
}
