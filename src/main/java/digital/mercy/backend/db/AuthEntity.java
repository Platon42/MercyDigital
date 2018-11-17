package digital.mercy.backend.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auth", schema = "public", catalog = "mercydb")
@NamedQueries({
        @NamedQuery(name = "checkLogin",
                query = "SELECT count(*) FROM AuthEntity WHERE login = :login and password=:password"),

})
public class AuthEntity {
    private String login;
    private String password;
    private ClientsEntity clientsByLogin;
    private OrganizationsEntity organizationsByLogin;
    private WalletsEntity walletsByLogin;

    @Id
    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthEntity that = (AuthEntity) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @OneToOne(mappedBy = "authByLogin")
    public ClientsEntity getClientsByLogin() {
        return clientsByLogin;
    }

    public void setClientsByLogin(ClientsEntity clientsByLogin) {
        this.clientsByLogin = clientsByLogin;
    }

    @OneToOne(mappedBy = "authByLogin")
    public OrganizationsEntity getOrganizationsByLogin() {
        return organizationsByLogin;
    }

    public void setOrganizationsByLogin(OrganizationsEntity organizationsByLogin) {
        this.organizationsByLogin = organizationsByLogin;
    }

    @OneToOne(mappedBy = "authByLogin")
    public WalletsEntity getWalletsByLogin() {
        return walletsByLogin;
    }

    public void setWalletsByLogin(WalletsEntity walletsByLogin) {
        this.walletsByLogin = walletsByLogin;
    }
}
