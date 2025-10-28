package net.ayad.rappel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Data Transfer Object for creating a new Product")
@AllArgsConstructor
@Data
public class CreateProductDTO {
    @Schema(description = "Name of the product", example = "Laptop")
    private String name;
    @Schema(description = "Description of the product", example = "A high-performance laptop")
    private String description;
    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;
}
