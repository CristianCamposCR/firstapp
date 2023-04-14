package mx.edu.utez.firstapp.models.product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String status;
    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private SubCategory subcategory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> images;

}
