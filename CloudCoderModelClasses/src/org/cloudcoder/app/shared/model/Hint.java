package org.cloudcoder.app.shared.model;

import java.io.Serializable;

public class Hint implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    //XXX placeholder
    private String hintText;

    public void setHintText(String hint) {
        this.hintText=hint;
    }
    public String getHintText() {
        return hintText;
    }
}
