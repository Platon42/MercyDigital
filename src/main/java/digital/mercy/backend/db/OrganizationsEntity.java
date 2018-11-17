package digital.mercy.backend.db;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "public", catalog = "mercydb")
public class OrganizationsEntity {
    private String login;
    private String organizationName;
    private Date registrationDate;
    private String legalAddress;
    private String founders;
    private int loginId;
    private String ogrn;
    private String inn;

    @Id
    @Column(name = "login", nullable = false, length = 32)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "organization_name", nullable = true, length = 100)
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Basic
    @Column(name = "registration_date", nullable = true)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "legal_address", nullable = true, length = 100)
    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
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
    @Column(name = "login_id", nullable = false)
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "ogrn", nullable = true, length = 100)
    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    @Basic
    @Column(name = "inn", nullable = true, length = 100)
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationsEntity that = (OrganizationsEntity) o;
        return loginId == that.loginId &&
                Objects.equals(login, that.login) &&
                Objects.equals(organizationName, that.organizationName) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                Objects.equals(legalAddress, that.legalAddress) &&
                Objects.equals(founders, that.founders) &&
                Objects.equals(ogrn, that.ogrn) &&
                Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, organizationName, registrationDate, legalAddress, founders, loginId, ogrn, inn);
    }
}
