package com.kii.sdk.photocolle;

import java.net.URL;


class GetContentThumbnailInfoCommand extends
        Command<GetContentThumbnailInfoLogic.Args, GetContentThumbnailInfoLogic,
            ContentThumbnailInfoList>
{

    public static class Args extends GetContentThumbnailInfoLogic.Args {

        public Args(AuthenticationContext context, ContentGUID... contentGUIDs) {
            super(context, contentGUIDs);
        }

    }

    public GetContentThumbnailInfoCommand(URL baseUrl) {
        super(new GetContentThumbnailInfoLogic(), baseUrl);
    }

}
