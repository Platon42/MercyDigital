
package digital.mercy.backend.webapi.transfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransferReq {

    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("payload")
    @Expose
    private String payload;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}