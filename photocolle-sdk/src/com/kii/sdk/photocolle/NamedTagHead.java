package com.kii.sdk.photocolle;


/**
 * TagHead with name.
 */
public class NamedTagHead extends TagHead {

    private String name;

    /**
     * Create a new NamedTagHead.
     *
     * @param type the type of tag.
     * @param guid content id.
     * @param name the name of tag.
     * @throws ParameterException One or more arguments are invalid.
     */
    public NamedTagHead(
            TagType type,
            ContentGUID guid,
            String name)
        throws ParameterException
    {
        super(type, guid);
        if (name == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "name must not be null.");
        }
        this.name = name;
    }

    /**
     * Get name.
     *
     * @return the name of this tag.
     */
    public String getName() {
        return this.name;
    }

}
