package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="day")
public class Day {

    public Day(){};

    public Day(Integer day_counter,String day_title,String description,BigDecimal biaya_hotel,Plan plan){
        this.day_counter = day_counter;
        this.day_title = day_title;
        this.description = description;
        this.biaya_hotel = biaya_hotel;
        this.plan = plan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long day_id;

    @Column(name="day_title",columnDefinition = "VARCHAR(40)")
    private String day_title;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name="day_counter")
    private Integer day_counter;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="biaya_hotel")
    private BigDecimal biaya_hotel;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activities> listAktivitas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id",nullable = false)
    private Plan plan;

    public Integer getDay_counter() {return this.day_counter;}
    public Long getDay_id() {return this.day_id;}
    public String getDay_title() {return this.day_title;}
    public List<Activities> getListAktivitas() {return this.listAktivitas;}
    public String getDescription() {return this.description;}
    public BigDecimal getBiaya_hotel() {return this.biaya_hotel;}
    public Plan getPlan(){return this.plan;}

    public void setDay_id(Long day_id) {this.day_id = day_id;}
    public void setDay_counter(Integer day_counter) {this.day_counter = day_counter;}
    public void setDay_title(String day_title) {this.day_title = day_title;}
    public void setDescription(String description) {this.description = description;}
    public void setListAktivitas(List<Activities> listAktivitas) {this.listAktivitas = listAktivitas;}
    public void setBiaya_hotel(BigDecimal biaya_hotel) {this.biaya_hotel = biaya_hotel;}

    public BigDecimal getBudgetForDay(){
        if (this.listAktivitas == null || this.listAktivitas.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            BigDecimal total = BigDecimal.ZERO;
            for (Activities aktifitas: this.listAktivitas){
                total = total.add(aktifitas.getBudget());
            }
            return total.add(this.biaya_hotel);
        }
    }

    public void addAktivitas(Activities aktivitas) {
        listAktivitas.add(aktivitas);
        aktivitas.setDay(this); // jaga sinkronisasi dua arah
    }









}
