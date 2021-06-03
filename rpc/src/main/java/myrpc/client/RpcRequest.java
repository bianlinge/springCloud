package myrpc.client;

import java.io.Serializable;

/**
 * 请求封装 用于远程传递就要序列化
 */
public class RpcRequest implements Serializable {
    //请求的服务名字 service名称 就是请求那个服务
    private String className;
    //请求服务的方法
    private String methodName;
    //请求的参数
    private Object[] parameters;

    public RpcRequest() {
    }

    public RpcRequest(final String className, final String methodName, final Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public void setParameters(final Object[] parameters) {
        this.parameters = parameters;
    }
}
