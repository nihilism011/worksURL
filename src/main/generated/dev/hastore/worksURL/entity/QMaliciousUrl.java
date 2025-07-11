package dev.hastore.worksURL.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMaliciousUrl is a Querydsl query type for MaliciousUrl
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMaliciousUrl extends EntityPathBase<MaliciousUrl> {

    private static final long serialVersionUID = -634255838L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaliciousUrl maliciousUrl = new QMaliciousUrl("maliciousUrl");

    public final BooleanPath addVirus = createBoolean("addVirus");

    public final QDetectedUrl detectedUrl;

    public final StringPath detectionName = createString("detectionName");

    public final StringPath distinction = createString("distinction");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMaliciousUrl(String variable) {
        this(MaliciousUrl.class, forVariable(variable), INITS);
    }

    public QMaliciousUrl(Path<? extends MaliciousUrl> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMaliciousUrl(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMaliciousUrl(PathMetadata metadata, PathInits inits) {
        this(MaliciousUrl.class, metadata, inits);
    }

    public QMaliciousUrl(Class<? extends MaliciousUrl> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.detectedUrl = inits.isInitialized("detectedUrl") ? new QDetectedUrl(forProperty("detectedUrl")) : null;
    }

}

