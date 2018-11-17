package digital.mercy.backend.webapi.info;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrgListResp {

    @SerializedName("org_names")
    @Expose
    private List<String> orgNames = null;

    public List<String> getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(List<String> orgNames) {
        this.orgNames = orgNames;
    }

}

