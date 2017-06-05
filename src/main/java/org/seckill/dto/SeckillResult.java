package org.seckill.dto;

/**
 * Created by Apple on 2017/4/28.
 * 所有Ajax请求返回类型，封装json结果
 */
public class SeckillResult<T> {

//    true通知用户（初始设置似乎有问题，在SeckillResult<Exposer>(true,exposer)时）
    private boolean success;
//    优
    private  T data;

    private  String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
