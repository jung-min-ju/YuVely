package sw_design.YNUMarketplace.domain.product.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private String id;

    @Column(nullable = false, length = 255)
    private String productName;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    @Column(nullable = false, length = 255)
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuantityTypeEnum quantityType;

    @Column(nullable = false, length = 255)
    private int quantity;

    @Column(nullable = false, length = 255)
    private int price;

    public enum QuantityTypeEnum{
        UNLIMITED, LIMITED;
    }

}
