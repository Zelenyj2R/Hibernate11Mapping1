package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude= "tickets")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }
}
