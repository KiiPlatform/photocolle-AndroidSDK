package com.kii.sdk.photocolle;

/**
 * File ID of uploaded content.
 */
public class DataID implements DTO
{
    private final String string;

    /**
     * Constructor of this class.
     * 
     * @param string ID.
     */
    DataID(String string) {
        this.string = string;
    }

    /**
     * Get ID.
     *
     * @return ID.
     */
    public String getString() {
        return this.string;
    }
}
