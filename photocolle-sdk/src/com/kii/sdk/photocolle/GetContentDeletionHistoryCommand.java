package com.kii.sdk.photocolle;

import java.net.URL;
import java.util.Date;


class GetContentDeletionHistoryCommand extends
        Command<GetContentDeletionHistoryLogic.Args,
            GetContentDeletionHistoryLogic, ListResponse<ContentGUID>>
{
    public static class Args extends GetContentDeletionHistoryLogic.Args {

        public Args(
                AuthenticationContext context,
                FileType fileType,
                Date minDateDeleted,
                Integer maxResults,
                Integer start)
        {
            super(context, fileType, minDateDeleted, maxResults, start);
        }

    }

    public GetContentDeletionHistoryCommand(URL baseUrl) {
        super(new GetContentDeletionHistoryLogic(), baseUrl);
    }

}
