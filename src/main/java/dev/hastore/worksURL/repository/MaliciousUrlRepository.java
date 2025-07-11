package dev.hastore.worksURL.repository;

import dev.hastore.worksURL.entity.MaliciousUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaliciousUrlRepository extends JpaRepository<MaliciousUrl,Long> {
}
