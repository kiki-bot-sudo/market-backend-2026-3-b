package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import jakarta.persistence.EntityManager;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Cliente;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProducto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final PurchaseMapper purchaseMapper;
    private final EntityManager entityManager;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper purchaseMapper,
                            EntityManager entityManager) {
        this.compraCrudRepository = compraCrudRepository;
        this.purchaseMapper = purchaseMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClientId(String clientId) {
        List<Purchase> purchases = purchaseMapper.toPurchases(compraCrudRepository.findByCliente_Id(clientId));
        return purchases.isEmpty() ? Optional.empty() : Optional.of(purchases);
    }

    @Override
    public Optional<Purchase> getPurchase(int purchaseId) {
        return compraCrudRepository.findById(purchaseId).map(purchaseMapper::toPurchase);
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.setCliente(entityManager.getReference(Cliente.class, purchase.getClientId()));
        if (compra.getProductos() != null) {
            for (CompraProducto item : compra.getProductos()) {
                item.setCompra(compra);
                item.setProducto(entityManager.getReference(Producto.class, item.getId().getIdProducto()));
            }
        }
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public void delete(int purchaseId) {
        compraCrudRepository.deleteById(purchaseId);
    }
}
