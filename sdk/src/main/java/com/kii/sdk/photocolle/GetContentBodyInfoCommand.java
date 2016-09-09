package com.kii.sdk.photocolle;

import java.net.URL;


class GetContentBodyInfoCommand extends
        Command<GetContentBodyInfoLogic.Args,
            GetContentBodyInfoLogic, ContentBodyInfo>
{

    public static class Args extends GetContentBodyInfoLogic.Args {

        public Args(
                AuthenticationContext context,
                FileType fileType,
                ContentGUID contentGUID,
                ResizeType resizeType)
        {
            super(context, fileType, contentGUID, resizeType);
        }

    }

    public GetContentBodyInfoCommand(URL baseUrl) {
        super(new GetContentBodyInfoLogic(), baseUrl);
    }

}
