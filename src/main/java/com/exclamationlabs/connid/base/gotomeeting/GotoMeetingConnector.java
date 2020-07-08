/*
    Copyright 2020 Exclamation Labs
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.exclamationlabs.connid.base.gotomeeting;

import com.exclamationlabs.connid.base.connector.BaseConnector;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeMapBuilder;
import com.exclamationlabs.connid.base.connector.authenticator.OAuth2TokenPasswordAuthenticator;
import com.exclamationlabs.connid.base.gotomeeting.adapter.GotoMeetingGroupsAdapter;
import com.exclamationlabs.connid.base.gotomeeting.adapter.GotoMeetingUsersAdapter;
import com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingGroupAttribute;
import com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingUserAttribute;
import com.exclamationlabs.connid.base.gotomeeting.configuration.GotoMeetingConfiguration;
import com.exclamationlabs.connid.base.gotomeeting.driver.rest.GotoMeetingDriver;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingGroup;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingUser;
import org.identityconnectors.framework.spi.ConnectorClass;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.*;
import static com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingGroupAttribute.*;
import static com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingUserAttribute.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.*;

@ConnectorClass(displayNameKey = "gotomeeting.connector.display",
        configurationClass = GotoMeetingConfiguration.class)
public class GotoMeetingConnector extends BaseConnector<GotoMeetingUser, GotoMeetingGroup> {

    public GotoMeetingConnector() {

        setAuthenticator(new OAuth2TokenPasswordAuthenticator());
        setDriver(new GotoMeetingDriver());
        setUsersAdapter(new GotoMeetingUsersAdapter());
        setGroupsAdapter(new GotoMeetingGroupsAdapter());
        setUserAttributes( new ConnectorAttributeMapBuilder<>(GotoMeetingUserAttribute.class)
                .add(USER_KEY, STRING, NOT_UPDATEABLE)
                .add(FIRST_NAME, STRING)
                .add(LAST_NAME, STRING)
                .add(EMAIL, STRING)
                .add(LOCALE, STRING)
                .add(ADMIN, STRING)
                .add(ROLE, STRING)
                .add(TIME_ZONE, STRING)
                .add(STATUS, STRING)
                .add(NAME_LOCKED, BOOLEAN, NOT_UPDATEABLE)
                .add(EMAIL_LOCKED, BOOLEAN, NOT_UPDATEABLE)
                .add(IDENTITY_READ_ONLY, BOOLEAN, NOT_UPDATEABLE)
                .add(CAN_AUTHENTICATE, BOOLEAN, NOT_UPDATEABLE)
                .add(INVITE_COUNT, INTEGER, NOT_UPDATEABLE)
                .add(ADMIN_ROLES, STRING, NOT_UPDATEABLE, MULTIVALUED)
                .add(PRODUCTS, STRING, NOT_UPDATEABLE, MULTIVALUED)
                .add(LICENSE_KEYS, STRING, NOT_UPDATEABLE, MULTIVALUED)
                .add(ADMIN_ROLES, STRING, NOT_UPDATEABLE, MULTIVALUED)
                .add(GROUP_IDS, STRING, NOT_UPDATEABLE, MULTIVALUED)
                .build());

        setGroupAttributes(new ConnectorAttributeMapBuilder<>(GotoMeetingGroupAttribute.class)
                .add(GROUP_KEY, STRING, NOT_UPDATEABLE)
                .add(GROUP_NAME, STRING, REQUIRED)
                .add(KEY_PATH, STRING, NOT_UPDATEABLE)
                .add(NAME_PATH, STRING, NOT_UPDATEABLE)
                .add(USER_KEYS, STRING, NOT_UPDATEABLE)
                .add(TOTAL_MEMBER_COUNT, INTEGER, NOT_UPDATEABLE)
                .build());
    }

}
