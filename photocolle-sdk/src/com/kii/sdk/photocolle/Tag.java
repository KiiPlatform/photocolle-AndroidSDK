package com.kii.sdk.photocolle;


/**
 * The abstract definition for the data model that provides a tag.
 */
public abstract class Tag extends TagHead
{
    private String name;

    private int contentsCount;

    /**
     * Create a new Tag.
     *
     * @param type type of tag.
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @throws ParameterException One or more arguments are invalid.
     */
    protected Tag(
            TagType type,
            ContentGUID guid,
            String name,
            int contentsCount)
        throws ParameterException
    {
        super(type, guid);
        if (name == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "name must not be null.");
        }
        this.name = name;
        this.contentsCount = contentsCount;
    }

    /**
     * Set tag name.
     *
     * @param value tag name.
     */
    protected void setName(String value) {
        this.name = value;
    }

    /**
     * Get tag name.
     *
     * @return tag name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get count of contents.
     *
     * @return count of contents.
     */
    public int getContentsCount() {
        return this.contentsCount;
    }
}
