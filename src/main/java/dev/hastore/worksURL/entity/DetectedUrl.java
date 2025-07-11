package dev.hastore.worksURL.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "url_list")
@NoArgsConstructor
@AllArgsConstructor
public class DetectedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @Column(nullable = false)
    private String url;

    private String issue;
    @Column(nullable = true)
    private Boolean processed ;
    @Column(nullable = true)
    private Boolean malicious ;

    public void setMalicious(){
        this.malicious = true;
        this.processed = true;
    }
    public void setNormal(){
        this.malicious = false;
        this.processed = true;
    }
    public void infoReset(){
        this.malicious = false;
        this.processed = false;
    }

}
