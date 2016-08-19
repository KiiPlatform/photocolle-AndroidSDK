package com.kii.sdk.photocolle;

import java.util.Date;


/**
 * The abstract definition for the data model that provides a filter with
 * date time.
 */
public abstract class DateFilter
{
    protected Date date;

    DateFilter() {
        // nothing to do.
    }

    /**
     * Get date time value.
     *
     * @return date time value.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Get filter name.
     *
     * @return name of filter.
     */
    public abstract String getFilterName();

    /**
     * Get filtering value as String.
     *
     * @return filtering value.
     */
    public String getFilterValue() {
        return MiscUtils.formatAsUTC(this.date);
    }

}
