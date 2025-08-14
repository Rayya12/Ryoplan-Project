package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="day")
public class Day {

    public Day(){};

    public Day(Integer dayCounter, String dayTitle, String description, BigDecimal biayaHotel, Plan plan){
        this.dayCounter = dayCounter;
        this.dayTitle = dayTitle;
        this.description = description;
        this.biayaHotel = biayaHotel;
        this.plan = plan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dayTitle",columnDefinition = "VARCHAR(40)")
    private String dayTitle;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name="dayCounter")
    private Integer dayCounter;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="biayaHotel")
    private BigDecimal biayaHotel;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activities> listAktivitas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id",nullable = false)
    private Plan plan;

    public Integer getDayCounter() {return this.dayCounter;}
    public Long getId() {return this.id;}
    public String getDayTitle() {return this.dayTitle;}
    public List<Activities> getListAktivitas() {return this.listAktivitas;}
    public String getDescription() {return this.description;}
    public BigDecimal getBiayaHotel() {return this.biayaHotel;}
    public Plan getPlan(){return this.plan;}

    public void setId(Long id) {this.id = id;}
    public void setDayCounter(Integer dayCounter) {this.dayCounter = dayCounter;}
    public void setDayTitle(String dayTitle) {this.dayTitle = dayTitle;}
    public void setDescription(String description) {this.description = description;}
    public void setListAktivitas(List<Activities> listAktivitas) {this.listAktivitas = listAktivitas;}
    public void setBiayaHotel(BigDecimal biayaHotel) {this.biayaHotel = biayaHotel;}
    public void setPlan(Plan plan) {this.plan = plan;}

    public BigDecimal getBudgetForDay(){
        if (this.listAktivitas == null || this.listAktivitas.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            BigDecimal total = BigDecimal.ZERO;
            for (Activities aktifitas: this.listAktivitas){
                total = total.add(aktifitas.getBudget());
            }
            return total.add(this.biayaHotel);
        }
    }

    public void addAktivitas(Activities aktivitas) {
        listAktivitas.add(aktivitas);
        aktivitas.setDay(this); // jaga sinkronisasi dua arah
    }









}
