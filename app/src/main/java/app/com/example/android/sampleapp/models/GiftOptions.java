
package app.com.example.android.sampleapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GiftOptions {

    @SerializedName("allowGiftWrap")
    @Expose
    private boolean allowGiftWrap;
    @SerializedName("allowGiftMessage")
    @Expose
    private boolean allowGiftMessage;
    @SerializedName("allowGiftReceipt")
    @Expose
    private boolean allowGiftReceipt;

    /**
     * 
     * @return
     *     The allowGiftWrap
     */
    public boolean isAllowGiftWrap() {
        return allowGiftWrap;
    }

    /**
     * 
     * @param allowGiftWrap
     *     The allowGiftWrap
     */
    public void setAllowGiftWrap(boolean allowGiftWrap) {
        this.allowGiftWrap = allowGiftWrap;
    }

    /**
     * 
     * @return
     *     The allowGiftMessage
     */
    public boolean isAllowGiftMessage() {
        return allowGiftMessage;
    }

    /**
     * 
     * @param allowGiftMessage
     *     The allowGiftMessage
     */
    public void setAllowGiftMessage(boolean allowGiftMessage) {
        this.allowGiftMessage = allowGiftMessage;
    }

    /**
     * 
     * @return
     *     The allowGiftReceipt
     */
    public boolean isAllowGiftReceipt() {
        return allowGiftReceipt;
    }

    /**
     * 
     * @param allowGiftReceipt
     *     The allowGiftReceipt
     */
    public void setAllowGiftReceipt(boolean allowGiftReceipt) {
        this.allowGiftReceipt = allowGiftReceipt;
    }

}
