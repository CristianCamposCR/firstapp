package mx.edu.utez.firstapp.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    //no debe aparacer en base de datos
    private String fileBase64;
    private String name;
    private String mimeType;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


}
