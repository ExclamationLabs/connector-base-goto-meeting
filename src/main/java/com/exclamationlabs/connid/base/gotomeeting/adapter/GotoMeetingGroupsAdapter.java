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
import com.exclamationlabs.connid.base.connector.adapter.BaseGroupsAdapter;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingGroup;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingUser;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ConnectorObject;

import java.util.Set;

import static com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingGroupAttribute.*;

public class GotoMeetingGroupsAdapter extends BaseGroupsAdapter<GotoMeetingUser, GotoMeetingGroup> {


    @Override
    protected GotoMeetingGroup constructGroup(Set<Attribute> attributes, boolean creation) {
        GotoMeetingGroup group = new GotoMeetingGroup();
        group.setKey(AdapterValueTypeConverter.getIdentityIdAttributeValue(attributes));

        if (group.getKey()==null) {
            group.setKey(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, GROUP_KEY));
        }

        group.setName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, GROUP_NAME));
        return group;
    }

    @Override
    protected ConnectorObject constructConnectorObject(GotoMeetingGroup group) {
        return getConnectorObjectBuilder(group)
                .addAttribute(AttributeBuilder.build(GROUP_KEY.name(), group.getKey()))
                .addAttribute(AttributeBuilder.build(GROUP_NAME.name(), group.getName()))
                .addAttribute(AttributeBuilder.build(KEY_PATH.name(), group.getKeyPath()))
                .addAttribute(AttributeBuilder.build(NAME_PATH.name(), group.getNamePath()))
                .addAttribute(AttributeBuilder.build(USER_KEYS.name(), group.getUserKeys()))
                .addAttribute(AttributeBuilder.build(TOTAL_MEMBER_COUNT.name(), group.getTotalMemberCount()))
                .build();
    }
}
