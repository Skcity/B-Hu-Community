package com.firstJava.community.community.entity;

//封装分页的相关信息
public class Page {
    //当前页码
    private int pageCurrent=1;
    //显示上限
    private int pageMax=10;
    // 数据总数（用于计算总页数）
    private int rows;
    //查询路径（用于复用分页链接）
    private String path;

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        if(pageCurrent>=1) {
            this.pageCurrent = pageCurrent;
        }
    }

    public int getPageMax() {
        return pageMax;
    }

    public void setPageMax(int pageMax) {
        if(pageMax>=1&&pageMax<=100) {
            this.pageMax = pageMax;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //获取当前页起始行
    public int getOffsect(){
        return (pageCurrent-1)*pageMax;
    }
    //获取总页数
    public int getTotal(){
        if((rows&pageMax)==0){
            return rows/pageMax;
        }else{
            return rows/pageMax+1;
        }
    }

    //获取起始页码
    public int getFrom(){
        int from=pageCurrent-2;
        return from <1?1:from;
    }

    //获取结束页码
    public int getTo(){
        int to=pageCurrent+2;
        int total=getTotal();
        return to>total?total:to;
    }
}
