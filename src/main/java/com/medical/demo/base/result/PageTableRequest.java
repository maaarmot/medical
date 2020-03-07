package com.medical.demo.base.result;


import lombok.Data;

@Data
public class PageTableRequest {
    private Integer page;
    private Integer limit;
    private Integer offset;

    public void countOffset(){
        if(this.page==null || this.limit==null){
            this.offset=0;
            return;
        }
        this.offset=(this.page-1)*limit;
    }
}
