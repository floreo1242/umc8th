package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.mapping.AcceptedMission;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<AcceptedMission> acceptedMissionList = new ArrayList<>();
}
