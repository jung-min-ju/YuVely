package sw_design.YNUMarketplace.domain.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 2105516343L;

    public static final QUser user = new QUser("user");

    public final StringPath emailId = createString("emailId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final ListPath<sw_design.YNUMarketplace.domain.product.model.Product, sw_design.YNUMarketplace.domain.product.model.QProduct> productList = this.<sw_design.YNUMarketplace.domain.product.model.Product, sw_design.YNUMarketplace.domain.product.model.QProduct>createList("productList", sw_design.YNUMarketplace.domain.product.model.Product.class, sw_design.YNUMarketplace.domain.product.model.QProduct.class, PathInits.DIRECT2);

    public final EnumPath<User.UserRoleEnum> role = createEnum("role", User.UserRoleEnum.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

