package com.kii.sdk.photocolle;

import java.net.URL;
import java.util.List;


class GetContentIDListWithTagsCommand extends
        Command<GetContentIDListWithTagsLogic.Args,
            GetContentIDListWithTagsLogic, ListResponse<DetailedContentInfo>>
{

    public GetContentIDListWithTagsCommand(URL baseUrl) {
        super(new GetContentIDListWithTagsLogic(), baseUrl);
    }

    public static class Args extends GetContentIDListWithTagsLogic.Args {

        public Args(
                AuthenticationContext context,
                ProjectionType projectionType,
                FileType fileType,
                List<TagHead> criteriaList,
                boolean forDustbox,
                DateFilter dateFilter,
                Integer maxResults,
                Integer start,
                SortType sortType)
        {
            super(context, projectionType, fileType, criteriaList,
                    forDustbox, dateFilter, maxResults, start, sortType);
        }
    }

}
