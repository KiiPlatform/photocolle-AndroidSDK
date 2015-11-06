package com.kii.sdk.photocolle;

import java.util.Date;

/**
 * The filter of modfied date time.
 */
public class ModifiedDateFilter extends DateFilter {

    /**
     * Create a new ModifiedDateFilter.
     *
     * @param date the value of filter.
     * @throws ParameterException One or more arguments are invalid.
     */
    public ModifiedDateFilter(Date date) throws ParameterException {
        if (date == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "date must not be null.");
        }
        this.date = date;
    }

    @Override
    public String getFilterName() {
        return "min_date_modified";
    }

}
