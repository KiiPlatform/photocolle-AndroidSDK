package com.kii.sdk.photocolle;


/**
 * Implementation of Tag for favorite.
 */
public final class FavoriteTag extends Tag
{
    /**
     * Create a new FavoriteTag.
     *
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @throws ParameterException One or more arguments are invalid.
     */
    public FavoriteTag(
            ContentGUID guid,
            String name,
            int contentsCount)
        throws ParameterException
    {
        super(TagType.FAVORITE, guid, name, contentsCount);
    }
}
