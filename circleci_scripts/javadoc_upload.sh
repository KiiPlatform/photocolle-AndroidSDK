#! bin/bash

if [ "$DOC_DEST" = "staging" ]; then
    declare -a uploadhosts=("kiidocs@stg-docs101jp.internal.kii.com")
elif [ "$DOC_DEST" = "production" ]; then
    declare -a uploadhosts=("kiidocs@docs101us.internal.kii.com" "kiidocs@docs102us.internal.kii.com")
else
    echo "invlid value of \$DOC_DEST, should be either staging or production"
fi

basedir="/ext/ebs/references/android/photocolle"
version=$(cat gradle.properties | sed -e 's/photocolleSDKVersion=//g')
echo $version
updir="$basedir/$version"
latestdir="$basedir/latest"
echo ""
for host in "${uploadhosts[@]}"; do
    uptarget="$host:$updir"
    echo "Uploading to : $host"
    rsync -rlptD --chmod=u+rw,g+r,o+r --chmod=Da+x --delete-after photocolle-sdk/sdk/build/docs/javadoc "$uptarget"
    echo "Upload completed to : $uptarget"
    ssh "$host" "rm $latestdir"
    ssh "$host" "ln -s $updir $latestdir"
done

echo "All uploads have completed!"
