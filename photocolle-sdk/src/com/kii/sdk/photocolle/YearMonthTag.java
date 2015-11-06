package com.kii.sdk.photocolle;


/**
 * Implementation of Tag for years.
 */
public final class YearMonthTag extends Tag
{
    /**
     * Create a new YearMonthTag.
     *
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @throws ParameterException One or more arguments are invalid.
     */
    public YearMonthTag(
            ContentGUID guid,
            String name,
            int contentsCount)
        throws ParameterException
    {
        super(TagType.YEAR_MONTH, guid, name, contentsCount);
    }
}
