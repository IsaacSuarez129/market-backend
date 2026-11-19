package com.tecnm.merida.market_backend_26_3.persistence;

import com.tecnm.merida.market_backend_26_3.domain.Product;
import com.tecnm.merida.market_backend_26_3.domain.repository.ProductRepository;
import com.tecnm.merida.market_backend_26_3.persistence.crud.ProductoCrudRepository;
import com.tecnm.merida.market_backend_26_3.persistence.entity.Producto;
import com.tecnm.merida.market_backend_26_3.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProduct(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProduct(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProduct(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId)
                .map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public Product delete(Product product) {
        Producto producto = mapper.toProducto(product);
        productoCrudRepository.delete(producto);
        return product;
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}