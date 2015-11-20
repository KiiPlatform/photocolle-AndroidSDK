package com.kii.sdk.photocolle;

import java.util.HashMap;
import java.util.Map;

/**
 * The mime type.
 */
public enum MimeType
{
    /** mime type for jpeg */
    JPEG("image/jpeg", null),
    /** mime type for progressive jpeg */
    PJPEG("image/pjpeg", null),
    /** mime type for 3gp */
    THREE_GP("video/3gpp", ".3gp"),
    /** mime type for avi */
    AVI("video/avi", ".avi"),
    /** mime type for quick time */
    QUICKTIME("video/quicktime", ".mov"),
    /** mime type for mp4 */
    MP4("video/mp4", ".mp4"),
    /** mime type for vender spacific */
    VND_MTS("video/vnd.mts", ".mts"),
    /** mime type for mpeg */
    MPEG("video/mpeg", ".m2ts");

    private static final  Map<String, MimeType> MAP;

    static {
        MAP = new HashMap<String, MimeType>();
        for (MimeType mimeType : MimeType.values()) {
            MAP.put(mimeType.getLabel(), mimeType);
        }
    }

    private final String label;
    private final String ext;

    private MimeType(String label, String ext) {
        this.label = label;
        this.ext = ext;
    }

    /**
     * Get a label.
     * 
     * @return the label of this mime type.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Get a extension.
     * 
     * @return the extension of this mime type.
     */
    public String getExt() {
        return this.ext;
    }

    /**
     * Convert from a label to a mime type.
     *
     * @param the label of mime type.
     * @return the mime type.
     * @throws ParameterException if this mime type has no constant with
     * the specified label.
     */
    public static MimeType fromString(
            String label)
        throws ParameterException
    {
        MimeType retval = MAP.get(label);
        if (retval == null) {
            throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("unexptected label %s", label));
        }
        return retval;
    }
}
