package digital.mercy.backend.webapi.info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccInfoReq {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
