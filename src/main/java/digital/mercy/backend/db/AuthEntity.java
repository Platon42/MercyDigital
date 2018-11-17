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
    private int loginId;

    @Basic
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

    @Id
    @Column(name = "login_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        AuthEntity that = (AuthEntity) o;
        return loginId == that.loginId &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, loginId);
    }
}
