package org.Sikoling.main.restful.files;

public enum StatusDokumenOnlyOfficeType {
	EDITING(1),
    MUST_SAVE(2),
    CORRUPTED(3),
    NO_CHANGE(4),
    MUST_FORCE_SAVE(6),
    CORRUPTED_FORCE_SAVE(7);
    private final int status;

    StatusDokumenOnlyOfficeType(final int codeParam) {
        this.status = codeParam;
    }

    public int getStatus() {
       return status;
    }
}
