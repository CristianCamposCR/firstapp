package mx.edu.utez.firstapp.services.product;

import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.product.ProductImageRepository;
import mx.edu.utez.firstapp.models.product.ProductImages;
import mx.edu.utez.firstapp.models.product.ProductRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productrepository;
    @Autowired
    private ProductImageRepository imageRepository;
    @Value("${spring.os}")
    private String rootPath;
    String separator = FileSystems.getDefault().getSeparator();
    private String BASEURL = "http://localhost:8080/api-market/products/loadfile/{uid}";
    public ResponseEntity<Resource> getImage(String uid) throws IOException {
        Path path = Paths.get(rootPath + separator + uid);
        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(path)
        );
        //consulta a productImages -> MimeType y aqui deberia de regresar un mimeType
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @Transactional(rollbackFor = {SQLDataException.class, IOException.class})
    public CustomResponse<Object> insert(Product product){
        List<ProductImages> images = new ArrayList<>();

        product.getImages().forEach(image -> {
            byte[] bytes = Base64.getDecoder().decode(image.getFileBase64());
            String uid = UUID.randomUUID().toString();
            try (OutputStream stream = new FileOutputStream(rootPath + separator + uid + image.getMimeType())){
                stream.write(bytes);
                image.setUrl(BASEURL + uid + image.getMimeType());
            }catch (IOException e){
                throw  new RuntimeException(e);
            }
        });

        if(this.productrepository.existsByName(product.getName()))
            return new CustomResponse<>(null, true, 400, "el producto ya existe");
        return  new CustomResponse<>(this.productrepository.saveAndFlush(product), false, 200, "Producto registrado correstamente");

    }


}
