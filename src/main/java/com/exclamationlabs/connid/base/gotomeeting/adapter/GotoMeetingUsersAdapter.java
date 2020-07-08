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

package com.exclamationlabs.connid.base.gotomeeting.adapter;

import com.exclamationlabs.connid.base.connector.adapter.AdapterValueTypeConverter;
import com.exclamationlabs.connid.base.connector.adapter.BaseUsersAdapter;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingGroup;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingUser;
import org.identityconnectors.framework.common.objects.*;

import java.util.Set;

import static com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingUserAttribute.*;

public class GotoMeetingUsersAdapter extends BaseUsersAdapter<GotoMeetingUser, GotoMeetingGroup> {
    @Override
    protected GotoMeetingUser constructUser(Set<Attribute> attributes, boolean creation) {
        GotoMeetingUser user = new GotoMeetingUser();
        user.setKey(AdapterValueTypeConverter.getIdentityIdAttributeValue(attributes));

        if (user.getKey()==null) {
            user.setKey(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, USER_KEY));
        }

        user.setFirstName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, FIRST_NAME));
        user.setLastName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LAST_NAME));
        user.setEmail(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, EMAIL));
        user.setTimeZone(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, TIME_ZONE));
        user.setLocale(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LOCALE));
        return user;
    }

    @Override
    protected ConnectorObject constructConnectorObject(GotoMeetingUser user) {
        return getConnectorObjectBuilder(user)
                .addAttribute(AttributeBuilder.build(USER_KEY.name(), user.getKey()))
                .addAttribute(AttributeBuilder.build(EMAIL.name(), user.getEmail()))
                .addAttribute(AttributeBuilder.build(FIRST_NAME.name(), user.getFirstName()))
                .addAttribute(AttributeBuilder.build(LAST_NAME.name(), user.getLastName()))
                .addAttribute(AttributeBuilder.build(TIME_ZONE.name(), user.getTimeZone()))
                .addAttribute(AttributeBuilder.build(STATUS.name(), user.getStatus()))
                .addAttribute(AttributeBuilder.build(GROUP_IDS.name(), user.getGroupKey()))
                .addAttribute(AttributeBuilder.build(LOCALE.name(), user.getLocale()))
                .addAttribute(AttributeBuilder.build(NAME_LOCKED.name(), user.getNameLocked()))
                .addAttribute(AttributeBuilder.build(EMAIL_LOCKED.name(), user.getEmailLocked()))
                .addAttribute(AttributeBuilder.build(IDENTITY_READ_ONLY.name(), user.getIdentityReadOnly()))
                .addAttribute(AttributeBuilder.build(CAN_AUTHENTICATE.name(), user.getCanAuthenticate()))
                .addAttribute(AttributeBuilder.build(INVITE_COUNT.name(), user.getInviteCount()))
                .addAttribute(AttributeBuilder.build(ADMIN_ROLES.name(), user.getAdminRoles()))
                .addAttribute(AttributeBuilder.build(PRODUCTS.name(), user.getProducts()))
                .addAttribute(AttributeBuilder.build(LICENSE_KEYS.name(), user.getLicenseKeys()))
                .build();
    }
}
