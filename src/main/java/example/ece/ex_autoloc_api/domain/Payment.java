package example.ece.ex_autoloc_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private String montant;

    private LocalDate datepayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ModePayment modePayment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;
}