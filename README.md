# GotoMeeting Connector

Open source Identity Management connector for [GotoMeeting](https://www.gotomeeting.com/)

Now leverages the [Connector Base Framework](https://github.com/ExclamationLabs/connector-base)

Developed and tested in [Midpoint](https://evolveum.com/midpoint/), but also could be utilized in any [ConnId](https://connid.tirasa.net/) framework. 

## Introductory Notes

- This software is Copyright 2020 Exclamation Labs.  Licensed under the Apache License, Version 2.0.

- As of this writing, there is no public Java API for Goto Meeting Admin, which is 
required to create/manage users and groups (there are API's for some of the other
GotoMeeting aspects, but not Admin)

- Limitation: In GotoMeeting, a user can belong to only ONE group at a time.  If you
need to assign a user to multiple groups, the way to achieve this is have a top-level
group with child groups underneath it (which I did not add support to for this connector).
In addition, GotoMeeting API only provides a call to remove a user
from ALL groups at once.  Therefore this is what the connector supports, and
there is no support for removing a user from a single group.

## Getting started
There are several RESTful API's for GotoMeeting, but the only one useful for IAM is the
GotoMeeting Admin API.

https://goto-developer.logmeininc.com/how-get-started

Sign up for a developer account.  You may have to sign up for a two-week limited trial, but
that time limit appears to be a limit for actual meetings, not using the API's (which
still appears to be working fine after 2 weeks)

Log into the Developer Center - https://goto-developer.logmeininc.com/ - and 
go to My Apps and 'Add a new App'.  Fill out whatever you like for App Name, 
App Description and Application URL.  Select 'Goto Meeting' for Product API.

Once you create an APP, you will be able to see your Consumer Key and Consumer Secret, which
are needed by the properties to authenticate.  Make sure you keep track of these.

Your developer account has a special key for it, which is needed in the properties.  This
can be found by seeing the string in the URL when you use the Goto Meeting 
Admin Web UI - https://admin.logmeininc.com/ (.../accounts/xxxxxx/). This Admin Web UI allows you manage users and groups.

API postman doc - https://documenter.getpostman.com/view/7571707/SVzxXeWo?version=latest#f318cc59-c33b-482f-a3cc-e32cad2d585e

## Midpoint configuration

See XML files in src/test/resources folder for Midpoint examples.  resourceOverlay.xml is an example
resource configuration setup for Midpoint.

## Configuration properties
 
- CONNECTOR_BASE_CONFIGURATION_ACTIVE - Set this to Y to activate the configuration 
 
- CONNECTOR_BASE_AUTH_OAUTH2_TOKEN_URL - Currently is https://api.getgo.com/oauth/v2/token and not likely to change often

- CONNECTOR_BASE_AUTH_OAUTH2_ENCODED_SECRET - This is an encoded combination of your Consumer Key and Consumer Key.
In order to generate it, you can do the following from UNIX (embedding key and secret without the '<' and '>' characters):
```
echo "<Consumer Key>|<Consumer Secret>" | base64

```

- CONNECTOR_BASE_AUTH_OAUTH2_USERNAME - The user ID for your developer account.

- CONNECTOR_BASE_AUTH_OAUTH2_PASSWORD - The password for your developer account.

- CONNECTOR_GOTO_MEETING_ACCOUNT_KEY - Account Key for your developer GotoMeeting account, visible from
the Goto Meeting Admin Web UI.

