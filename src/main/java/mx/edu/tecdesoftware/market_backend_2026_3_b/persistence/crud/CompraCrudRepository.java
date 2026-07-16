package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer>{
    //Query Methods
    //Obtener una lista de de compras filtrada por el ID del cliente
    /*
        SELECT *
        FROM compras
        WHERE id_cliente = ?
     */
    List<Compra> findByCliente_Id(String clientId);


}
