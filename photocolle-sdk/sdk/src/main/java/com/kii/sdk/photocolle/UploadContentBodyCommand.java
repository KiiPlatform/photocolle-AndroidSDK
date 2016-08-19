package com.kii.sdk.photocolle;

import java.io.InputStream;
import java.net.URL;


class UploadContentBodyCommand extends
        Command<UploadContentBodyLogic.Args, UploadContentBodyLogic, DataID>
{

    public static class Args extends UploadContentBodyLogic.Args {

        public Args(
                AuthenticationContext context,
                FileType fileType,
                String fileName,
                long size,
                MimeType mimeType,
                InputStream bodyStream)
        {
            super(context, fileType, fileName, size, mimeType, bodyStream);
        }

    }

    public UploadContentBodyCommand(URL baseUrl) {
        super(new UploadContentBodyLogic(), baseUrl);
    }

}
