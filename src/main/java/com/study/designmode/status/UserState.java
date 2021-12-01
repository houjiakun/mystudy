package com.study.designmode.status;

public abstract class UserState {
    protected AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    public abstract void favorite();

    public abstract void comment(String comment);
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("评论: 好文章，360个赞!");
    }
}