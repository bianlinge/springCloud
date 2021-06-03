package dove.sort;

/**
 * 通知书类型枚举 排序
 */
    public enum InformSortEnum {

    /**
     * 新單投保
     */
    UN(1001, "補充資料通知書"),
    CF(1002, "條件承保通知書"),
    AM(1003, "中介人補充資料通知書"),
    UV(1004, "客人簽回醫院授權書1"),
    UW(1005, "客人簽回醫院授權書２"),
    UM(1006, "驗體化驗推薦信(受保人)"),
    UO(1007, "驗體化驗推薦信(投保人)"),
    UNP(1008, "核保處理通知書"),
    UR(1009, "催辦通知書"),

    /**
     * 繳費通知
     */
	RECV_INFORM_RENEW(2001, "繳費通知書"),
	RECV_INFORM_AUTO_TRANSFER_DEBIT(2002, "自動轉賬扣賬通知書"),
	RECV_INFORM_OVERDUE_PRE_NOTIFI(2003, "逾期繳費通知書"),
	RECV_INFORM_LEVY_OVERDUE_PRE_NOTIFI(2004, "逾期保費徵費繳費通知書"),
	RECV_INFORM_POLICY_APL(2005, "自動保費貸款提醒通知書"),


    /**
     * 保障、保單狀態通知書
     */
	PLC_STATUS_INFORM_INC_AMT(3001, "保單增額通知書"),
	PLC_STATUS_INFORM_EXPIRATION_NOTICE(3002, "保單滿期通知書"),
	PLC_STATUS_INFORM_ADV_EXPRT(3003, "提前滿期通知書"),
	PLC_STATUS_INFORM_FAILURE_CONFIRMATION(3004, "保單失效確認書"),
	PLC_STATUS_INFORM_INSUE_NOTICE(3005, "保單發出通知書"),

    /**
     *  保單週年報表
     */
	PLC_ANU_INFORM_TYPE(4001, "保單週年報表"),
	FUND_ANU_INFORM_TYPE(4002, "週年財務報告(基金保單)"),

    /**
     * 理赔通知书
     */
	CLAIM_ADDITIONAL_INFOS_INFORM(5001, "理賠補充資料通知書"),
	CLAIM_REFUSE_INFORM_TYPE(5002, "拒賠通知書"),
	CLAIM_REPARATIONS_INFORM_TYPE(5003, "賠付通知書"),

    /**
     * 公司通知
     */
	SINGLE_PREMIUM_LEVY_LETTER(6001, "一次性保費徵費通知信函"),
	REFUSE_UNDERWRITE_INFORM_TYPE(6002, "拒絕承保通知書"),
	POSTPONE_UNDERWRITE_INFORM_TYPE(6003, "延期承保通知書"),
	CANCELINSURE_INFORM_TYPE(6004, "取消投保通知書"),
    OVERDUECANCEL(6005, "逾期取消通知書");




    public int code;
    public String value;

    InformSortEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static int sort(String value) {
        InformSortEnum[] values = InformSortEnum.values();
        for (InformSortEnum informEnum : values) {
            if (informEnum.value.equals(value)) {
                return informEnum.code;
            }
        }
        return 99;
    }
}
