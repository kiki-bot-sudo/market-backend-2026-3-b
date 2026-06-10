package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Le dices a Spring que esta clase se comunicará en la BD

public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;

    // SELECT * FROM pr
    //    public Optional<List<Product>> getByCategory(int categoryId){
    //        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
    //        return Optional.of(productMapper.toProducts(productos));
    //    }
    //
    //    /*
    //        SELECT *
    //        FROM producto
    //        WHERE cantidad_stock < ? AND estado = TRUE
    //    */
    //
    //    public Optional<List<Product>> getScarceProducts(int quantity){
    //        Optional <List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
    //        return Optional.of(productMapper.toProducts(productos.get()));
    //    }
    //
    //    // Obtener un producto dado el id
    //    public Optional<Product> getProduct(int productId){
    //        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));
    //    }
    //
    //    // Guardar un producto
    //    public Product save(Product product){
    //        Producto producto = productMapper.toProducto(product);
    //        return productMapper.toProduct(productoCrudRepository.save(producto));
    //    }
    //
    //    // Eliminar por id
    //    public void delete(int productId){
    //        productoCrudRepository.deleteById(productId);
    //    }
    //
    //}productos
    public List<Product> getAll() {
        //Se "Castea" de Iterable a la Lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }


    public Optional<List<Product>> getByCategory(int categoryId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void delete(int productId) {

    }
}

