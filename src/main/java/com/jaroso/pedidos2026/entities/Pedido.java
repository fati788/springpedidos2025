package com.jaroso.pedidos2026.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private EstadoPedido estado;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<LineaPedido> lineasPedido = new ArrayList<>();

/*Methodos: */
    public void addLineaPedido(LineaPedido lp){
        lineasPedido.add(lp);
        lp.setPedido(this);
    }
    public void removeLineaPedido(LineaPedido lp){
        lp.setPedido(null);
        lineasPedido.remove(lp);
    }


}
