package com.kii.sdk.photocolle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is container of response list.
 */
public class ListResponse<T extends DTO>
{
    private final int start;

    private final int nextPage;

    private int count;

    private final List<T> list = new ArrayList<T>();

    /**
     * Create a new ListResponse.
     *
     * @param start start index in all results of search.
     * @param nextPage if the next page exist, this value is start + count.
     * otherwise 0.
     * @param count count of responses in current page.
     */
    ListResponse(
            int start,
            int nextPage,
            int count)
    {
        this.start = start;
        this.nextPage = nextPage;
        this.count = count;
    }

    /**
     * Create a new ListResponse.
     *
     * @param start start index in all results of search.
     * @param nextPage if the next page exist, this value is start + count.
     * otherwise 0.
     */
    ListResponse(int start, int nextPage) {
        this(start, nextPage, -1);
    }

    /**
     * Create a new ListResponse.
     */
    ListResponse() {
        this(-1, 0, -1);
    }

    /**
     * Get start value.
     * 
     * @return value of start.
     */
    public int getStart() {
        return this.start;
    }

    /**
     * Get nextPage value.
     * 
     * @return value of nextPage.
     */
    public int getNextPage() {
        return this.nextPage;
    }

    /**
     * Get response list.
     * 
     * @return response list.
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * Set count.
     * 
     * @param value the value setting to count.
     */
    void setCount(int value) {
        this.count = value;
    }

    /**
     * Get count.
     *
     * @return the value of count.
     */
    public int getCount() {
        return this.count >= 0 ? this.count : this.list.size();
    }
}
