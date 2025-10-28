package net.ayad.rappel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ayad.rappel.dto.CreateProductDTO;
import net.ayad.rappel.dto.ProductResponseDTO;
import net.ayad.rappel.dto.UpdateProductDTO;
import net.ayad.rappel.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product management APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details.")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        ProductResponseDTO productCreated =  productService.createProduct(createProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all products.")
    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        List<ProductResponseDTO> products = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    @ApiResponse(responseCode = "200", description = "Product retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "Update a product", description = "Updates the details of an existing product.")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO updateProductDTO) {
        ProductResponseDTO productUpdated = productService.updateProduct(id, updateProductDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }


    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
