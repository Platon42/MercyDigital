package digital.mercy.backend.webapi.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class AccInfoResp {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("job")
    @Expose
    private String job;


    @SerializedName("organizationName")
    @Expose
    private String organizationName;

    @SerializedName("registrationDate")
    @Expose
    private Date registrationDate;

    @SerializedName("legalAddress")
    @Expose
    private String legalAddress;

    @SerializedName("founders")
    @Expose
    private String founders;

    @SerializedName("ogrn")
    @Expose
    private String ogrn;

    @SerializedName("inn")
    @Expose
    private String inn;



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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getFounders() {
        return founders;
    }

    public void setFounders(String founders) {
        this.founders = founders;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

}