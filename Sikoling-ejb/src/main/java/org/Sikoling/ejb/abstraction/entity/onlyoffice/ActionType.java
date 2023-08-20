package org.Sikoling.ejb.abstraction.entity.onlyoffice;

public enum ActionType {
	USER_DISCONNECT_CO_EDITING(0),
	NEW_USER_CONNECT_CO_EDITING(1),
	USER_CLICK_FORCE_SAVE_BUTTON(2);
    private final int code;

    ActionType(final int codeParam) {
        this.code = codeParam;
    }

    public int getCode() {
       return code;
    }
}
