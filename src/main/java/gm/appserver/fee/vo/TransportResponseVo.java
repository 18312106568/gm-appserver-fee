package gm.appserver.fee.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 签收单接收响应实体
 */
@Data
public class TransportResponseVo implements Serializable {

    public static String SUCCESS = "1";

    public static String SUCCESS_MSG = "SUCCESS";

    public static String FAIL = "2";

    @SerializedName("LOADING_LIST_ID")
    private Long loadingListId;

    @SerializedName("RECEIPT_ID")
    private Long receiptId;

    @SerializedName("ADDRESSID")
    private Long addressId;

    private String msgty;

    private String message;
}
