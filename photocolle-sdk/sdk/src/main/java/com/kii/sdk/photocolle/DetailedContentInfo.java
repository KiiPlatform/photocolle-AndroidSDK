package com.kii.sdk.photocolle;

import java.util.Date;
import java.util.List;


/**
 * Content information with tags.
 */
public class DetailedContentInfo extends ContentInfo
{
    private String ratio;

    private Score score;

    private Orientation orientation;

    private final List<NamedTagHead> persons;

    private final List<NamedTagHead> events;

    private final List<NamedTagHead> favorites;

    private final List<NamedTagHead> places;

    private final List<NamedTagHead> yearMonths;

    /**
     * Create a new content information.
     *
     * Application developers do not need to use this constructor.
     *
     * @param guid guid of this content.
     * @param name name of this content.
     * @param fileType file type of this content
     * @param exifCameraDate photo taking day extracted from EXIF.
     * @param modifiedDate modified date time.
     * @param uploadedDate uploaded date time.
     * @param fileDataSize file size of this content.
     * @param ratio XY ratio(Y/X) of this content.
     * @param score sore of this content.
     * @param orientation orientation of this content.
     * @param resizable flag to determine resizing content is allowed or not.
     * @param persons list of person tag.
     * @param events list of event tag.
     * @param favorites list of favorites tag.
     * @param places list of placement tag.
     * @param yearMonths list of year month tag.
     */
    DetailedContentInfo(
            ContentGUID guid,
            String name,
            FileType fileType,
            Date exifCameraDate,
            Date modifiedDate,
            Date uploadedDate,
            long fileDataSize,
            String ratio,
            Score score,
            Orientation orientation,
            boolean resizable,
            List<NamedTagHead> persons,
            List<NamedTagHead> events,
            List<NamedTagHead> favorites,
            List<NamedTagHead> places,
            List<NamedTagHead> yearMonths)
    {
        super(guid, name, fileType, exifCameraDate, modifiedDate, uploadedDate,
                fileDataSize, resizable);
        this.ratio = ratio;
        this.score = score;
        this.orientation = orientation;
        this.persons = persons;
        this.events = events;
        this.favorites = favorites;
        this.places = places;
        this.yearMonths = yearMonths;
    }

    /**
     * Get ratio of this content.
     *
     * @return XY ratio of this content.
     */
    public String getRatio() {
        return this.ratio;
    }

    /**
     * Get score of this content.
     *
     * @return score of this content.
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Get orientation of this content.
     *
     * @return orientation of this content.
     */
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Get person tag list of this content.
     *
     * @return person tag list of this content.
     */
    public List<NamedTagHead> getPersons() {
        return this.persons;
    }

    /**
     * Get event tag list of this content.
     *
     * @return event tag list of this content.
     */
    public List<NamedTagHead> getEvents() {
        return this.events;
    }

    /**
     * Get favorite tag list of this content.
     *
     * @return favorite tag list of this content.
     */
    public List<NamedTagHead> getFavorites() {
        return this.favorites;
    }

    /**
     * Get placement tag list of this content.
     *
     * @return placement tag list of this content.
     */
    public List<NamedTagHead> getPlaces() {
        return this.places;
    }

    /**
     * Get year month tag list of this content.
     *
     * @return year month tag list of this content.
     */
    public List<NamedTagHead> getYearMonths() {
        return this.yearMonths;
    }

}
