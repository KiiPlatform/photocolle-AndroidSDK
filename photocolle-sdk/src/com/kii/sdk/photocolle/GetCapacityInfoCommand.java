package com.kii.sdk.photocolle;

import java.net.URL;


class GetCapacityInfoCommand extends
        Command<GetCapacityInfoLogic.Args, GetCapacityInfoLogic, CapacityInfo>
{

    public GetCapacityInfoCommand(URL baseUrl) {
        super(new GetCapacityInfoLogic(), baseUrl);
    }

    public static class Args extends GetCapacityInfoLogic.Args {

        public Args(AuthenticationContext context) {
            super(context);
        }

    }

}
