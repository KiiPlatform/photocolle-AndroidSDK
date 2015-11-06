package com.kii.sdk.photocolle;

import java.net.URL;


class GetContentIDListCommand extends
        Command<GetContentIDListLogic.Args, GetContentIDListLogic,
                  ListResponse<ContentInfo>>
{
    public static class Args extends GetContentIDListLogic.Args {

        public Args(
                AuthenticationContext context,
                FileType fileType,
                boolean forDustbox,
                DateFilter dateFilter,
                Integer maxResults,
                Integer start,
                SortType sortType)
        {
            super(context, fileType, forDustbox, dateFilter, maxResults,
                    start, sortType);
        }

    }

   public GetContentIDListCommand(URL baseUrl) {
        super(new GetContentIDListLogic(), baseUrl);
    }

}
