package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "createdAt")
    private ZonedDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Planet fromPlanet;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Planet toPlanet;

}