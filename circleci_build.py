#!/usr/bin/env python
import logging
import httplib
import json
import time
import argparse

parser = argparse.ArgumentParser(description="Circle CI Parameterized Build")
parser.add_argument('-b', '--branch', dest='branch', help='branch to build',
        required=True)

def getLogger():
    logger = logging.getLogger('debug')
    ch = logging.StreamHandler();
    ch.setLevel(logging.DEBUG)
    logger.addHandler(ch)
    logger.setLevel(logging.DEBUG)
    return logger

class CircleCI(object):
    def __init__(self):
        self.circleciAPIToken='c89a4584413592c7c2f46978979fd222f87c450d'
        self.host='circleci.com'
        self.logger = getLogger()

    def parameterizedBuild(self):
        self.logger.debug('trigger parameterized build on Circle CI')
        parameters = {}

        bodyDict = {}
        if parameters:
            bodyDict = {'build_parameters':parameters}

        jsonBody = json.dumps(bodyDict)

        conn = httplib.HTTPSConnection(self.host)
        path = '/api/v1/project/KiiPlatform/photocolle-AndroidSDK/tree/{0}?circle-token={1}'.format(self.branch, self.circleciAPIToken)

        headers = {'content-type': 'application/json'}
        conn.request('POST', path, jsonBody, headers)
        response = conn.getresponse()

        responseMessage = 'Response for parameterized build:\n status:{0}\n {1}'.format(
            response.status, response.read())
        self.logger.debug(responseMessage)

if __name__ == '__main__':
    args = parser.parse_args()
    ci = CircleCI()
    ci.branch = args.branch

    ci.parameterizedBuild()
