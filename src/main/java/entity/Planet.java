package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "planet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"fromPlanets", "toPlanets"})
@EqualsAndHashCode(exclude = {"fromPlanets", "toPlanets"})
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fromPlanet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ticket> fromPlanets;
    @OneToMany(mappedBy = "toPlanet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ticket> toPlanets;
}