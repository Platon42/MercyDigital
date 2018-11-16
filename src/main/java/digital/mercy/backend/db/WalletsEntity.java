package digital.mercy.backend.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wallets", schema = "public", catalog = "mercydb")
public class WalletsEntity {
    private String address;
    private String brainPrivKey;
    private String wifPrivKey;
    private String pubKey;
    private String ownerType;
    private int loginId;
    private AuthEntity authByLoginId;

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
        WalletsEntity that = (WalletsEntity) o;
        return loginId == that.loginId &&
                Objects.equals(address, that.address) &&
                Objects.equals(brainPrivKey, that.brainPrivKey) &&
                Objects.equals(wifPrivKey, that.wifPrivKey) &&
                Objects.equals(pubKey, that.pubKey) &&
                Objects.equals(ownerType, that.ownerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, brainPrivKey, wifPrivKey, pubKey, ownerType, loginId);
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
