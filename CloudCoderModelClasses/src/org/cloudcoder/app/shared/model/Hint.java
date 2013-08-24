package org.cloudcoder.app.shared.model;

import java.io.Serializable;

public class Hint implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String hintText;
    private String hintTag;

    public void setHintText(String hint) {
        this.hintText=hint;
    }
    public String getHintText() {
        return hintText;
    }
    public String getHintTag() {
        return hintTag;
    }
    public void setHintTag(String hintTag) {
        this.hintTag = hintTag;
    }
}
