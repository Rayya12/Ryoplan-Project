package RayCorp.Ryoplan.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="aktivitas")
public class Activities {

    @Id
    @Column(length=12,columnDefinition="CHAR(12)",name = "activity_id")
    private String activity_id;

    @Column(columnDefinition = "VARCHAR(45)",name = "activity_name")
    private String activity_name;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="budget")
    private BigDecimal budget;

    @Column(name = "jam_mulai")
    private LocalTime jamMulai;

    @Column(name= "jam_selesai")
    private LocalTime jamSelesai;

}
