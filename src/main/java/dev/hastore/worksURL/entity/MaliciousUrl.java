package dev.hastore.worksURL.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "url_list_malicious")
@NoArgsConstructor
@AllArgsConstructor
public class MaliciousUrl {

    @Id
    @Column(name="url_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="url_id")
    private DetectedUrl detectedUrl;

    private String distinction;

    private String detectionName;
    @Column(nullable = false)
    private Boolean addVirus ;

}
