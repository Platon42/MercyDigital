package digital.mercy.backend.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wallets", schema = "public", catalog = "mercydb")
@NamedQueries({
        @NamedQuery(name = "walletType",
                query = "SELECT ownerType FROM WalletsEntity WHERE login = :login"),

})
public class WalletsEntity {
    private String address;
    private String brainPrivKey;
    private String wifPrivKey;
    private String pubKey;
    private String ownerType;
    private String login;
    private AuthEntity authByLogin;

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "brain_priv_key", nullable = true, length = 100)
    public String getBrainPrivKey() {
        return brainPrivKey;
    }

    public void setBrainPrivKey(String brainPrivKey) {
        this.brainPrivKey = brainPrivKey;
    }

    @Basic
    @Column(name = "wif_priv_key", nullable = true, length = 100)
    public String getWifPrivKey() {
        return wifPrivKey;
    }

    public void setWifPrivKey(String wifPrivKey) {
        this.wifPrivKey = wifPrivKey;
    }

    @Basic
    @Column(name = "pub_key", nullable = true, length = 100)
    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    @Basic
    @Column(name = "owner_type", nullable = true, length = 20)
    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    @Id
    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletsEntity that = (WalletsEntity) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(brainPrivKey, that.brainPrivKey) &&
                Objects.equals(wifPrivKey, that.wifPrivKey) &&
                Objects.equals(pubKey, that.pubKey) &&
                Objects.equals(ownerType, that.ownerType) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, brainPrivKey, wifPrivKey, pubKey, ownerType, login);
    }

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    public AuthEntity getAuthByLogin() {
        return authByLogin;
    }

    public void setAuthByLogin(AuthEntity authByLogin) {
        this.authByLogin = authByLogin;
    }
}
