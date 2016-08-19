# get the aar file name to upload
version=$(cat gradle.properties | sed -e 's/photocolleSDKVersion=//g')
sdk_body_file="package/PhotoColleSDK-Android-$version.aar"

if [ -f "$sdk_body_file" ]; then

    echo "{ \"platform\" : \"android\", \"sdk\" : \"photocolle\", \"type\" : \"sdk\", \"version\" : \"v$version\", \"extension\" : \"aar\"}" > sdk-metadata.json

    cd internal-tools/sdk-uploader

    sdk_meta_file=../../sdk-metadata.json
    sdk_body_file=../../$sdk_body_file

    if [ $SDK_DEST == 'release' ]; then
        # release to kii cloud server
        echo "release to kii cloud"
        python sdk_uploader.py --meta=$sdk_meta_file --file=$sdk_body_file --content-type=application/zip --kii-bucket=sdks

        # release to bintray
        echo "release to bintray"
        cd ../../
        ./gradlew bintrayUpload -Pbintray_token=$BINTRAY_TOKEN -Pbintray_user=$BINTRAY_USER

    elif [ $SDK_DEST == 'test' ]; then
        python sdk_uploader.py --meta=$sdk_meta_file --file=$sdk_body_file --content-type=application/zip
        echo "test"
        cd ../../
    else
        echo "SDK_DEST should either release or test"
        exit 1
    fi
    echo "Succeeded to release sdk"
else
    echo "no release aar file($sdk_body_file) found to upload"
    exit 1
fi
