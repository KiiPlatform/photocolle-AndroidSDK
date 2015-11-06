package com.kii.sdk.photocolle;


/**
 * Implementation of Tag for placement.
 */
public final class PlacementTag extends Tag
{
    /**
     * Create a new PlacementTag.
     *
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @throws ParameterException One or more arguments are invalid.
     */
    public PlacementTag(
            ContentGUID guid,
            String name,
            int contentsCount)
        throws ParameterException
    {
        super(TagType.PLACEMENT, guid, name, contentsCount);
    }
}
