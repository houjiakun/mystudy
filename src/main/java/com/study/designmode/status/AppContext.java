package com.study.designmode.status;

public class AppContext {
    public static final UserState STATE_LOGIN = new LoginInState();
    public static final UserState STATE_UNLOGIN = new UnLoginState();
    private UserState currentState = STATE_UNLOGIN;
    {
        STATE_LOGIN.setContext(this);
        STATE_UNLOGIN.setContext(this);
    }

    public void setState(UserState state) {
        this.currentState = state;
        this.currentState.setContext(this);
    }

    public UserState getState() {
        return this.currentState;
    }

    public void favorite() {
        this.currentState.favorite();
    }

    public void comment(String comment) {
        this.currentState.comment(comment);
    }
}