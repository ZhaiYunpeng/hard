package com.work.auth.base;

import com.github.pagehelper.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一返回实体类
 *
 * @author ZhaiYunpeng
 */
@Slf4j
@Data
public class ResultDto<T> {

    private int code;
    private String msg;
    private T data;
    private long count;

    public ResultDto() {
    }

    public ResultDto(int code) {
        this.code = code;
        if(code == 0){
            this.msg = "操作成功";
        }else{
            this.msg = "操作失败";
        }
    }

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDto(T data) {
        this.code = 0;
        this.msg = "查询成功";
        this.data = data;
        if (data instanceof Page) {
            Page<?> page = (Page<?>) data;
            this.count = page.getTotal();
        }
//        this.count = 1L;
    }

    public ResultDto(T data, int count) {
        this.code = 0;
        this.msg = "查询成功";
        this.data = data;
        this.count = count;
    }

    public ResultDto(int code, String msg, T data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
}
