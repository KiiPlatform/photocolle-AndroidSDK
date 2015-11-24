package com.kii.sdk.photocolle;

/**
 * Server capacity information.
 */
public class CapacityInfo implements DTO
{
    private final long maxSpace;

    private final long freeSpace;

    /**
     * Create a new server capacity information.
     *
     * Application developers do not need to use this constructor.
     *
     * @param maxSpace maximum space of server.
     * @param freeSpace free space of server.
     */
    CapacityInfo(long maxSpace, long freeSpace) {
        this.maxSpace = maxSpace;
        this.freeSpace = freeSpace;
    }

    /**
     * Get maximum space of server in bytes.
     *
     * @return maximum space of server in bytes.
     */
    public long getMaxSpace() {
        return this.maxSpace;
    }

    /**
     * Get free space of server in bytes.
     *
     * @return free space of server in bytes.
     */
    public long getFreeSpace() {
        return this.freeSpace;
    }
}
