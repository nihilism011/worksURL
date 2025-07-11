package dev.hastore.worksURL.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.hastore.worksURL.entity.DetectedUrl;
import dev.hastore.worksURL.entity.QDetectedUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UrlQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<DetectedUrl> findSearchUrls(String urlKeyword,String issueKeyword,Boolean processed, Boolean malicious){
        QDetectedUrl detectedUrl = QDetectedUrl.detectedUrl;

        return queryFactory
                .selectFrom(detectedUrl)
                .where(
                        containsUrlKeyword(urlKeyword),
                        containsIssueKeyword(issueKeyword),
                        eqProcessed(processed),
                        eqMalicious(malicious)
                ).fetch();
    }

    private BooleanExpression containsUrlKeyword(String urlKeyword){
        return StringUtils.hasText(urlKeyword) ? QDetectedUrl.detectedUrl.url.contains(urlKeyword) :null;
    }
    private BooleanExpression containsIssueKeyword(String issueKeyword) {
        return StringUtils.hasText(issueKeyword) ? QDetectedUrl.detectedUrl.issue.contains(issueKeyword) : null;
    }
    private BooleanExpression eqProcessed(Boolean processed) {
        return processed != null ? QDetectedUrl.detectedUrl.processed.eq(processed) : null;
    }
    private BooleanExpression eqMalicious(Boolean malicious) {
        return malicious != null ? QDetectedUrl.detectedUrl.malicious.eq(malicious) : null;
    }


}
