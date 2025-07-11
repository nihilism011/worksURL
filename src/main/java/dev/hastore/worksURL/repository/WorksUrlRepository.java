package dev.hastore.worksURL.repository;

import dev.hastore.worksURL.entity.DetectedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksUrlRepository extends JpaRepository<DetectedUrl,Long> {

}
