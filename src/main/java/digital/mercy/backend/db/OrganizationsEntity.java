package digital.mercy.backend.db;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "public", catalog = "mercydb")
public class OrganizationsEntity {
    private String login;
    private String name;
    private Date registerDate;
    private String address;
    private String founders;
    private Integer balance;
    private Integer collectedFunds;
    private Integer spentFunds;
    private int loginId;
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
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "register_date", nullable = true)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "founders", nullable = true, length = 100)
    public String getFounders() {
        return founders;
    }

    public void setFounders(String founders) {
        this.founders = founders;
    }

    @Basic
    @Column(name = "balance", nullable = true)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "collected_funds", nullable = true)
    public Integer getCollectedFunds() {
        return collectedFunds;
    }

    public void setCollectedFunds(Integer collectedFunds) {
        this.collectedFunds = collectedFunds;
    }

    @Basic
    @Column(name = "spent_funds", nullable = true)
    public Integer getSpentFunds() {
        return spentFunds;
    }

    public void setSpentFunds(Integer spentFunds) {
        this.spentFunds = spentFunds;
    }

    @Id
    @Column(name = "login_id", nullable = false)
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationsEntity that = (OrganizationsEntity) o;
        return loginId == that.loginId &&
                Objects.equals(login, that.login) &&
                Objects.equals(name, that.name) &&
                Objects.equals(registerDate, that.registerDate) &&
                Objects.equals(address, that.address) &&
                Objects.equals(founders, that.founders) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(collectedFunds, that.collectedFunds) &&
                Objects.equals(spentFunds, that.spentFunds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, registerDate, address, founders, balance, collectedFunds, spentFunds, loginId);
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
