package net.ayad.rappel.mapper;

import net.ayad.rappel.dto.CreateProductDTO;
import net.ayad.rappel.dto.ProductResponseDTO;
import net.ayad.rappel.dto.UpdateProductDTO;
import net.ayad.rappel.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProductEntity(CreateProductDTO createProductDTO);

    ProductResponseDTO toProductResponseDTO(Product product);

    Product updateProductFromDTO(UpdateProductDTO updateProductDTO, @MappingTarget Product product);

}
