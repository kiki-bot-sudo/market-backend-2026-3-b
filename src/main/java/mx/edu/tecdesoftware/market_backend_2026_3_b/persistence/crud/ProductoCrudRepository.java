package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //QueryMethods

    //obtener lista de productos listados por id_categoria y
    //ordenados ascendentemente por nombre

    /*
        SELECT *
        FROM Categorias
        WHERE id_categoria = ?
        ORDER BY Nombre ASC
     */
    List<Producto> findByCantidadOrderByNombreAsc(int idcategoria);

    //obtener los productos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);



}
