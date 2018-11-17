package digital.mercy.backend.webapi.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrgRequest {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("legal_address")
    @Expose
    private String legalAddress;
    @SerializedName("ogrn")
    @Expose
    private String ogrn;
    @SerializedName("inn")
    @Expose
    private Integer inn;
    @SerializedName("founders")
    @Expose
    private String founders;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    public String getFounders() {
        return founders;
    }

    public void setFounders(String founders) {
        this.founders = founders;
    }
}
