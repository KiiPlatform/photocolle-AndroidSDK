#! bin/bash

git clone git@github.com:KiiPlatform/photocolle-AndroidSDK.git RepoForDoc

cd RepoForDoc
git checkout gh-pages
rm -fr docs/
cp -fr ../photocolle-sdk/sdk/build/docs/javadoc docs
git add docs
git commit -a -m "update api doc"
git push origin gh-pages

