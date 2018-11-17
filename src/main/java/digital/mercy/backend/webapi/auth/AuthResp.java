package digital.mercy.backend.webapi.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResp {

    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("type")
    @Expose
    private String type;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
