package dev.hastore.worksURL.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDetectedUrl is a Querydsl query type for DetectedUrl
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDetectedUrl extends EntityPathBase<DetectedUrl> {

    private static final long serialVersionUID = 1998972964L;

    public static final QDetectedUrl detectedUrl = new QDetectedUrl("detectedUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath issue = createString("issue");

    public final DateTimePath<java.time.LocalDateTime> issuedAt = createDateTime("issuedAt", java.time.LocalDateTime.class);

    public final BooleanPath malicious = createBoolean("malicious");

    public final BooleanPath processed = createBoolean("processed");

    public final StringPath url = createString("url");

    public QDetectedUrl(String variable) {
        super(DetectedUrl.class, forVariable(variable));
    }

    public QDetectedUrl(Path<? extends DetectedUrl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDetectedUrl(PathMetadata metadata) {
        super(DetectedUrl.class, metadata);
    }

}

