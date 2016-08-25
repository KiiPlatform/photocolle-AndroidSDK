#! bin/bash

git config --global user.name "circleci"
git config --global user.email "circleci@kii.com"
git clone git@github.com:KiiPlatform/photocolle-AndroidSDK.git RepoForDoc

cd RepoForDoc
git checkout gh-pages
rm -fr docs/
cp -fr ../photocolle-sdk/sdk/build/docs/javadoc docs
git commit -a -m "update api doc"
git push origin gh-pages

