package mx.edu.tecdesoftware.market_backend_2026_3_b.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage purchases in the store")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    @Operation(summary = "Get all purchases", description = "Return a list of all available purchases")
    @ApiResponse(responseCode = "200", description = "successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase id", description = "Return a purchase by ist id")
    @ApiResponse(responseCode = "200", description = "purchase found")
    @ApiResponse(responseCode = "404", description = "Purchase not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> getPurchase(@Parameter(description = "Id of the purchase to be retrieved", example = "1", required = true) @PathVariable int id) {
        return purchaseService.getPurchase(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get client id", description = "Return a client by his ID")
    @ApiResponse(responseCode = "200", description = "client found")
    @ApiResponse(responseCode = "404", description = "client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClientId(@PathVariable String clientId) {
        return purchaseService.getByClientId(clientId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new purchase", description = "Register a new purchase and return it",
            requestBody =   @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example Purchase",
                                    value = """
                    {
                      "purchaseId": 60,
                      "clientId": "983824",
                      "date": "2026-07-16T11:15:53",
                      "payMethod": "T",
                      "comment": "compra sin problemas",
                      "status": "V",
                      "items": [
                        {
                          "productId": 51,
                          "quantity": 2,
                          "total": 5,
                          "status": true
                        }
                      ]
                    }
                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict (duplicate code)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@Valid @RequestBody Purchase purchase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchase));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a purchase by Id", description = "Deletes a purchase using his Id")
    @ApiResponse(responseCode = "201", description = "Purchase deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase id")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Purchase not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(@Parameter(description = "Id of the purchase to be deleted", example = "1", required = true )@PathVariable int id) {
        return purchaseService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
