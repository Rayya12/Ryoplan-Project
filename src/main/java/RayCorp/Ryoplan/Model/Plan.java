package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {

    public Plan() {}

    public Plan(String planName, String description, String location,
                LocalDate tanggalMulai, LocalDate tanggalSelesai) {
        this.planName = planName;
        this.description = description;
        this.location = location;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.budgetRn = BigDecimal.ZERO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planName", columnDefinition = "VARCHAR(40)", nullable = false)
    private String planName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", columnDefinition = "VARCHAR(30)")
    private String location;

    @Column(name = "tanggalMulai")
    private LocalDate tanggalMulai;

    @Column(name = "tanggalSelesai")
    private LocalDate tanggalSelesai;

    @Column(name = "budgetRn", columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    private BigDecimal budgetRn = BigDecimal.ZERO;

    @Column(name = "availableCounter")
    private Integer availableCounter = 1;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> listDay = new ArrayList<>();

    @ManyToMany(mappedBy = "planList")
    private List<User> userList = new ArrayList<>();

    // ============ SETTER ============
    public void setDescription(String description) { this.description = description; }
    public void setId(Long id) { this.id = id; }
    public void setPlanName(String planName) { this.planName = planName; }
    public void setListDay(List<Day> listDay) { this.listDay = listDay; }
    public void setLocation(String location) { this.location = location; }
    public void setBudgetRn(BigDecimal budgetRn) { this.budgetRn = (budgetRn == null ? BigDecimal.ZERO : budgetRn); }
    public void setTanggalMulai(LocalDate tanggalMulai) { this.tanggalMulai = tanggalMulai; }
    public void setTanggalSelesai(LocalDate tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }
    public void setUserList(List<User> userList) { this.userList = userList; }
    public void setAvailableCounter(Integer availableCounter) { this.availableCounter = availableCounter; }

    // ============ GETTER ============
    public Long getId() { return id; }
    public List<Day> getListDay() { return listDay; }
    public String getDescription() { return description; }
    public String getPlanName() { return planName; }
    public String getLocation() { return location; }
    public BigDecimal getBudgetRn() { return budgetRn == null ? BigDecimal.ZERO : budgetRn; }
    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public LocalDate getTanggalSelesai() { return tanggalSelesai; }
    public List<User> getUserList() { return userList; }
    public Integer getAvailableCounter() { return availableCounter; }

    // ============ LOGIC ============
    // Hitung total budget dari semua Day
    public BigDecimal getBudgetPlan() {
        if (listDay == null || listDay.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Day hari : listDay) {
            BigDecimal daily = (hari.getBudgetForDay() == null ? BigDecimal.ZERO : hari.getBudgetForDay());
            total = total.add(daily);
        }
        return total;
    }

    // Hitung progress = (budget total - budget sisa) / budget total * 100
    public Float getProgress() {
        BigDecimal total = getBudgetPlan();
        BigDecimal remaining = getBudgetRn();

        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return 0f; // tidak ada budget → progress 0%
        }

        BigDecimal progress = total.subtract(remaining)
                                   .divide(total, 4, RoundingMode.HALF_UP)
                                   .multiply(BigDecimal.valueOf(100));

        return progress.floatValue();
    }

    // ============ RELATION HELPERS ============
    public void addUser(User user) {
        if (userList.add(user)) {
            user.addPlan(this);
        }
    }

    public void removeUser(User user) {
        if (userList.remove(user)) {
            user.removePlan(this);
        }
    }

    public void addDay(Day day) {
        listDay.add(day);
        day.setPlan(this); // jaga sinkronisasi dua arah
    }

    public void removeDay(Day day) {
        listDay.remove(day);
        day.setPlan(null);
    }

    public void replaceUsers(List<User> newUsers) {
        for (Iterator<User> it = this.userList.iterator(); it.hasNext(); ) {
            User u = it.next();
            if (!newUsers.contains(u)) {
                it.remove();
                u.getPlanList().remove(this);
            }
        }
        for (User u : newUsers) {
            if (this.userList.add(u)) {
                u.getPlanList().add(this);
            }
        }
    }
}
