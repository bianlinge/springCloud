package com.dove.web.easypoi;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

import javax.validation.constraints.NotNull;

/**
 * Created by E1041 on 2020/2/24.
 */
public class Plc {
	@Excel(name = "保單號碼")
	@NotNull
	private String plcno;

	public String getPlcno() {
		return plcno;
	}

	public void setPlcno(String plcno) {
		this.plcno = plcno;
	}

	@Override
	public String toString() {
		return "Plc{" +
				"plcno='" + plcno + '\'' +
				'}';
	}
}
