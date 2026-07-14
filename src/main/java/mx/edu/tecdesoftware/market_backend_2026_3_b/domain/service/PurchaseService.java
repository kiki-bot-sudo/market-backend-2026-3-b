package mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<Purchase> getPurchase(int purchaseId){
        return purchaseRepository.getPurchase(purchaseId);
    }

    public Optional<List<Purchase>> getByClientId(String clientId){
        return purchaseRepository.getByClientId(clientId);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public boolean delete(int purchaseId){
        if(getPurchase(purchaseId).isPresent())
        {
            purchaseRepository.delete(purchaseId);
            return true;
        } else{
            return false;
        }
    }
}
