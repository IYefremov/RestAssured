package com.cyberiansoft.test.email.emaildata;

public enum EmailFolder {
    INBOX("INBOX"),
    SPAM("SPAM"),
    DRAFTS("DRAFTS"),
    JUNK("JUNK");

    private String folder;

    private EmailFolder(String folder){
        this.folder = folder;
    }

    public String getFolderName() {
        return folder;
    }
}