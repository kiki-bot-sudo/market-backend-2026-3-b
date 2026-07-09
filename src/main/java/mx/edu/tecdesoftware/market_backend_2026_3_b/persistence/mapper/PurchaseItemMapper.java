package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper;



import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel ="spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "idCompra", target ="purchaseId"),
            @Mapping(source = "idProducto", target ="productId"),
            @Mapping(source = "cantidad", target ="quantity"),
            @Mapping(source = "estado", target ="status"),
    })
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);
    @InheritInverseConfiguration
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "compra", ignore = true)
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}
