package com.kii.sdk.photocolle;


/**
 * Implementation of header info for tag.
 */
public class TagHead implements DTO
{
    private final TagType type;

    private final ContentGUID guid;

    /**
     * Create a new TagHead.
     *
     * @param type type of tag.
     * @param guid content id.
     * @throws ParameterException One or more arguments are invalid.
     */
    public TagHead(TagType type, ContentGUID guid) throws ParameterException
    {
        if (type == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "type must not be null.");
        } else if (guid == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "guid must not be null.");
        }
        this.type = type;
        this.guid = guid;
    }

    /**
     * Get type of tag.
     *
     * @return type of tag.
     */
    public final TagType getTagType() {
        return this.type;
    }

    /**
     * Get content id.
     *
     * @return content id.
     */
    public final ContentGUID getTagGUID() {
        return this.guid;
    }
}
