package sw_design.YNUMarketplace.domain.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sw_design.YNUMarketplace.domain.user.model.User;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, unique = true, length = 255)
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Enum<quantityType> quantity_type;

    @Column(nullable = false)
    @Min(-1)
    private int quantity; //무제한 선택 시 자동으로 -1, 유제한 선택시 사용자 정의 수량 입력

    @Column(nullable = false)
    @Min(0)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum quantityType{
        UNLIMITED, LIMITED
    }
}
