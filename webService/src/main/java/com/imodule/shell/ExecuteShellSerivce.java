package com.imodule.shell;

import org.springframework.stereotype.Service;

/**
 * Created by E1041 on 2019/12/26.
 */
@Service
public class ExecuteShellSerivce {
	public void execShell(String shell) throws Exception {
		try {
			JavaExcuShellUtil.execShell(shell);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("shell 脚本执行错误");
		}
	}
}
