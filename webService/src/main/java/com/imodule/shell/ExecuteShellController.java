package com.imodule.shell;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by E1041 on 2019/12/26.
 */
@Api("shell 脚本执行")
@RestController
public class ExecuteShellController {
	@Autowired
	ExecuteShellSerivce executeShellSerivce;
	@ApiOperation(value = "执行git pull",tags = "git pull")
	@RequestMapping(value = "/github-webhook",method = RequestMethod.GET)
	public void execShell() throws Exception {
		String shell = "#!/bin/sh\n" +
				"\n" +
				"##unset GIT_DIR\n" +
				"\n" +
				"HEXO_PATH=\"/usr/local/html/hexo/bianlinge.github.io/\"\n" +
				"\n" +
				"cd $HEXO_PATH\n" +
				"/usr/local/git/bin/git pull origin master 2>&1\n" +
				"\n" +
				"exit 0";
		executeShellSerivce.execShell(shell);
	}
}
