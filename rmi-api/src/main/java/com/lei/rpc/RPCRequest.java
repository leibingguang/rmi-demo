package com.lei.rpc;

import java.io.Serializable;
import java.util.Arrays;

public class RPCRequest implements Serializable{
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法参数
     */
    private Object[] params;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RPCRequest{");
        sb.append("methodName='").append(methodName).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", params=").append(Arrays.toString(params));
        sb.append('}');
        return sb.toString();
    }
}
