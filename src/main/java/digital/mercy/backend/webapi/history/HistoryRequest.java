package digital.mercy.backend.webapi.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryRequest {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("depth")
    @Expose
    private String depth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}

