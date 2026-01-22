package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.LineaPedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoDto;
import com.jaroso.pedidos2026.entities.*;
import com.jaroso.pedidos2026.mappers.PedidoMapper;
import com.jaroso.pedidos2026.repositories.ClienteRepository;
import com.jaroso.pedidos2026.repositories.PedidoRepository;
import com.jaroso.pedidos2026.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper mapper;

    @Override
    @Transactional
    public PedidoDto create(PedidoCreateDto dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow();
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        for(LineaPedidoCreateDto lineaDto : dto.lineas()) {
            Producto producto = productoRepository.findById(lineaDto.productoId()).orElseThrow();
            LineaPedido linea = new LineaPedido();
            linea.setCantidad(lineaDto.cantidad());
            linea.setProducto(producto);
            pedido.addLineaPedido(linea);
        }
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido = repo.save(pedido);
        return mapper.toDto(pedido);
    }

    @Override
    public Optional<PedidoDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<Pedido> pedido = repo.findById(id);

        if (pedido.isPresent()){
            repo.delete(pedido.get());
            return  true;
        }else {
            return  false;
        }
    }

    @Transactional(readOnly = true)
    public List<PedidoDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
}
