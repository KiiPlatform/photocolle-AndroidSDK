package com.kii.sdk.photocolle;

import java.util.Date;


/**
 * Implementation of Tag for person.
 */
public final class PersonTag extends Tag
{
    private Date birthDate;

    /**
     * Create a new PersonTag.
     *
     * @param guid content id.
     * @param name tag name.
     * @param contentsCount count of contents.
     * @param birthDate birthday of person.
     * @throws ParameterException One or more arguments are invalid.
     */
    public PersonTag(
            ContentGUID guid,
            String name,
            int contentsCount,
            Date birthDate)
        throws ParameterException
    {
        super(TagType.PERSON, guid, name, contentsCount);
        this.birthDate = birthDate;
    }

    /**
     * Get person's birthday.
     *
     * @return Date of person's birthday.
     */
    public Date getBirthDate() {
        return this.birthDate;
    }

}
