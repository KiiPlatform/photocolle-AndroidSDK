package com.kii.sdk.photocolle;


/**
 * Implementation of Tag for event.
 */
public final class EventTag extends Tag
{
    /**
     * Create a new EventTag.
     *
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @throws ParameterException One or more arguments are invalid.
     */
    public EventTag(
            ContentGUID guid,
            String name,
            int contentsCount)
        throws ParameterException
    {
        super(TagType.EVENT, guid, name, contentsCount);
    }
}
