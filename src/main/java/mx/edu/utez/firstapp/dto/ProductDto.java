package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.product.ProductImages;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String status;
    private SubCategory subcategory;
    private List<ProductImages> images;

    public Product cast ()  {
            return new Product(
                    getId(),
                    getName(),
                    getDescription(),
                    getPrice(),
                    getStock(),
                    getBrand(),
                    getStatus(),
                    getSubcategory(),
                    getImages()
            );
    }
}
