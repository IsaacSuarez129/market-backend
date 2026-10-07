package com.tecnm.merida.market_backend_26_3.persistence.crud;

import com.tecnm.merida.market_backend_26_3.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Metodos abstractos que despues se implementarán
public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer> {

    /* SQL Query Method
    SELECT *
    FROM productos
    WHERE id_categoria = 10?
    ORDER BY nombre ASC
     */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Cantidad stock
    Optional<List<Producto>> findByCantidadStockLessThenAndEstado (int cantidadStock, boolean estado);
}
