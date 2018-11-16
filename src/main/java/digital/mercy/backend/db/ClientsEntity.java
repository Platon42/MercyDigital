package digital.mercy.backend.db;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "public", catalog = "mercydb")
public class ClientsEntity {
    private String login;
    private String phone;
    private String firstName;
    private Date birthDate;
    private String region;
    private String job;
    private Integer balance;
    private int loginId;
    private String lastName;
    private AuthEntity authByLoginId;

    @Basic
    @Column(name = "login", nullable = true, length = 32)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "birth_date", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "region", nullable = true, length = 100)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "job", nullable = true, length = 100)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "balance", nullable = true)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Id
    @Column(name = "login_id", nullable = false)
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return loginId == that.loginId &&
                Objects.equals(login, that.login) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(region, that.region) &&
                Objects.equals(job, that.job) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, phone, firstName, birthDate, region, job, balance, loginId, lastName);
    }

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id", nullable = false)
    public AuthEntity getAuthByLoginId() {
        return authByLoginId;
    }

    public void setAuthByLoginId(AuthEntity authByLoginId) {
        this.authByLoginId = authByLoginId;
    }
}
