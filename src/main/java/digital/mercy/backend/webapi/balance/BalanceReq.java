package digital.mercy.backend.webapi.balance;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceReq {

    @SerializedName("account_name")
    @Expose
    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
