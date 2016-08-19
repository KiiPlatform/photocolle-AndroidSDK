package com.kii.sdk.photocolle;

import java.net.URL;
import java.util.Date;


class GetTagIDListCommand extends
        Command<GetTagIDListLogic.Args, GetTagIDListLogic, ListResponse<Tag>> {

    public GetTagIDListCommand(URL baseUrl) {
        super(new GetTagIDListLogic(), baseUrl);
    }

    public static class Args extends GetTagIDListLogic.Args {

        public Args(
                AuthenticationContext context,
                Category category,
                FileType fileType,
                Date minDateModified)
        {
            super(context, category, fileType, minDateModified);
        }

    }

}
