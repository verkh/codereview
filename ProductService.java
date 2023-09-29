import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.Getter;
import some.otherpackage.office;

@Entity
@Getter
@Setter
public class Store {
    private int id;
    private String name;
    private String description;

    @ManyToMany(ma)
    private List<Product> products;
}


@Entity
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private String description;

    @ManyToMany(ma)
    private List<Store> stores;
}


@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final KafkaService kafkaService;

    @Scheduled
    public void checkForDuplicates() {
        doCheckForDuplicates();
    }

    @Transactional
    public void doCheckForDuplicates() {
        List<Product> amazonProducts = productRepository.findByStoreName("Amazon");
        List<Product> EbayProducts = productRepository.findByStoreName("Ebay");

        List<Product> duplicates;
        for(var product : amazonProducts) {
            if(EbayProducts.contains(product)) {
                duplicates.add(product);
                productRepository.delete(product);
            }
        }

        kafkaService.send(duplicates);
    }
}
