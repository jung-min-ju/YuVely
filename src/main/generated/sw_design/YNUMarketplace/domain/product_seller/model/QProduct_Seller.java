package sw_design.YNUMarketplace.domain.product_seller.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct_Seller is a Querydsl query type for Product_Seller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_Seller extends EntityPathBase<Product_Seller> {

    private static final long serialVersionUID = 946553815L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct_Seller product_Seller = new QProduct_Seller("product_Seller");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final sw_design.YNUMarketplace.domain.product.model.QProduct product;

    public final sw_design.YNUMarketplace.domain.user.model.QUser user;

    public QProduct_Seller(String variable) {
        this(Product_Seller.class, forVariable(variable), INITS);
    }

    public QProduct_Seller(Path<? extends Product_Seller> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct_Seller(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct_Seller(PathMetadata metadata, PathInits inits) {
        this(Product_Seller.class, metadata, inits);
    }

    public QProduct_Seller(Class<? extends Product_Seller> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new sw_design.YNUMarketplace.domain.product.model.QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new sw_design.YNUMarketplace.domain.user.model.QUser(forProperty("user")) : null;
    }

}

