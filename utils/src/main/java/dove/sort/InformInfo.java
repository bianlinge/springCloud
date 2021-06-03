package dove.sort;

import com.google.common.collect.ComparisonChain;

/**
 * Created by E1041 on 2020/1/15.
 */
public class InformInfo implements Comparable<InformInfo> {
	/**
	 * 保单状态(申请中,不是申请中)
	 * 投保日期,
	 * 通知书类型
	 * <p>
	 * =============================
	 * 发出日期,
	 * 通知书类型,
	 * 保单日期,
	 * 保单号码
	 * <p>
	 * 如果申请中没有发出日期===========可以按照以下顺序排序
	 * 保单状态(申请中,不是申请中)
	 * 投保日期,
	 * 发出日期,
	 * 通知书类型
	 * 保单日期,
	 * 保单号码
	 */
	private String prtdate;//发出日期
	private String efftdate;//保单日期
	private String toubaodate;//投保日期
	private String informType;
	private String informName;
	private String plcNo;

	private boolean applying;//
	private int enumOrder;//排序

	public int getEnumOrder() {
		return enumOrder;
	}

	public void setEnumOrder(int enumOrder) {
		this.enumOrder = enumOrder;
		this.enumOrder = InformSortEnum.sort(informName);
	}

	public String getPrtdate() {
		return prtdate;
	}

	public void setPrtdate(String prtdate) {
		this.prtdate = prtdate;
	}

	public String getEfftdate() {
		return efftdate;
	}

	public void setEfftdate(String efftdate) {
		this.efftdate = efftdate;

	}

	public String getToubaodate() {
		return toubaodate;
	}

	public void setToubaodate(String toubaodate) {
		this.toubaodate = toubaodate;
	}

	public String getInformType() {
		return informType;
	}

	public void setInformType(String informType) {
		this.informType = informType;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}

	public String getPlcNo() {
		return plcNo;
	}

	public void setPlcNo(String plcNo) {
		this.plcNo = plcNo;
	}

	public boolean isApplying() {
		return applying;
	}

	public void setApplying(boolean applying) {
		this.applying = applying;
	}

	/**
	 * 重写compareTo 方法 排序使用guava ComparisonChain 排序规则
	 * @param inform
	 * @return
	 */
	@Override
	public int compareTo(InformInfo inform) {
		return ComparisonChain.start()
				.compareTrueFirst(this.isApplying(),inform.isApplying())
				.compare(inform.getToubaodate(), this.getToubaodate())
				.compare(inform.getPrtdate(), this.getPrtdate())
				.compare( this.getEnumOrder(),inform.getEnumOrder())
				.compare(inform.getEfftdate(), this.getEfftdate())
				.compare(inform.getPlcNo(), this.getPlcNo())
				.result();
	}

	public InformInfo(String prtdate, String efftdate, String toubaodate, String informType, String informName, String plcNo, boolean applying, int enumOrder) {
		this.prtdate = prtdate;
		this.efftdate = efftdate;
		this.toubaodate = toubaodate;
		this.informType = informType;
		this.informName = informName;
		this.plcNo = plcNo;
		this.applying = applying;
		this.enumOrder = InformSortEnum.sort(this.getInformName());
	}

	@Override
	public String toString() {
		return "InformInfo{" +
				"prtdate='" + prtdate + '\'' +
				", efftdate='" + efftdate + '\'' +
				", toubaodate='" + toubaodate + '\'' +
				", informType='" + informType + '\'' +
				", informName='" + informName + '\'' +
				", plcNo='" + plcNo + '\'' +
				", applying=" + applying +
				", enumOrder=" + enumOrder +
				'}';
	}
}
