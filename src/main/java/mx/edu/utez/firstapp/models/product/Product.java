package mx.edu.utez.firstapp.models.product;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;
import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fileBlob", columnDefinition = "longblob")
    private byte[] fileBase64;
    private int cuantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subcategory;
}
