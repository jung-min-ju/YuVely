package sw_design.YNUMarketplace.domain.user.model;

import jakarta.persistence.*;
import lombok.*;
import sw_design.YNUMarketplace.domain.product.model.Product;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 255, unique = true)
    private String emailId;

    @Column(nullable = false, length = 11, unique = true)
    private String phoneNum;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;

    public enum UserRoleEnum {
        USER, ADMIN
    }
}

//1. mappedBy : 현재 db가 종속테이블임을 나타냄
//2. fetch : 지연로딩
//3. casade : 연관된 데이터 간 자동 crud 관리
//4. 고아db 관리
