package net.ayad.rappel.service;

import lombok.RequiredArgsConstructor;
import net.ayad.rappel.dto.CreateProductDTO;
import net.ayad.rappel.dto.ProductResponseDTO;
import net.ayad.rappel.dto.UpdateProductDTO;
import net.ayad.rappel.exception.product.ProductNotFoundException;
import net.ayad.rappel.mapper.ProductMapper;
import net.ayad.rappel.model.Product;
import net.ayad.rappel.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO createProduct(CreateProductDTO createProductDTO) {
        Product product = productMapper.toProductEntity(createProductDTO);
        Product savedProduct =  productRepository.save(product);

        return productMapper.toProductResponseDTO(savedProduct);
    }


    public List<ProductResponseDTO> findAllProducts() {
        return  productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponseDTO)
                .toList();
    }

    public ProductResponseDTO findProductById(Long id) {

        return
                productRepository.findById(id)
                        .map(productMapper::toProductResponseDTO)
                        .orElseThrow(() -> new ProductNotFoundException(id));
    }


    public ProductResponseDTO updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct != null) {
            Product updatedProduct = productMapper.updateProductFromDTO(updateProductDTO, existingProduct);
            return productMapper.toProductResponseDTO(productRepository.save(updatedProduct));
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
