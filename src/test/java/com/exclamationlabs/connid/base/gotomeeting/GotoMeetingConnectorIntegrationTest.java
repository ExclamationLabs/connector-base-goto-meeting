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

import com.exclamationlabs.connid.base.connector.configuration.ConfigurationNameBuilder;
import com.exclamationlabs.connid.base.connector.test.IntegrationTest;
import com.exclamationlabs.connid.base.connector.test.util.ConnectorTestUtils;
import com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingGroupAttribute;
import com.exclamationlabs.connid.base.gotomeeting.attribute.GotoMeetingUserAttribute;
import com.exclamationlabs.connid.base.gotomeeting.configuration.GotoMeetingConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.identityconnectors.framework.common.objects.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GotoMeetingConnectorIntegrationTest extends IntegrationTest {

    private GotoMeetingConnector connector;

    private static String generatedUserId;
    private static String generatedGroupId;

    @Override
    public String getConfigurationName() {
        return new ConfigurationNameBuilder().withConnector(() -> "GOTOMEETING").build();
    }

    @Before
    public void setup() {
        connector = new GotoMeetingConnector();
        setup(connector, new GotoMeetingConfiguration(getConfigurationName()));
    }

    @Test
    public void test050TestMethod() {
        connector.test();
    }

    @Test
    public void test110UserCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(GotoMeetingUserAttribute.FIRST_NAME.name()).addValue("Fred").build());
        attributes.add(new AttributeBuilder().setName(GotoMeetingUserAttribute.LAST_NAME.name()).addValue("Rubble").build());
        attributes.add(new AttributeBuilder().setName(GotoMeetingUserAttribute.EMAIL.name()).addValue("fred@rubble.com").build());

        Uid newId = connector.create(ObjectClass.ACCOUNT, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedUserId = newId.getUidValue();
    }

    @Test
    public void test120UserModify() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(GotoMeetingUserAttribute.LAST_NAME.name()).addValue("Flinstone").build());
        attributes.add(new AttributeBuilder().setName(GotoMeetingUserAttribute.USER_KEY.name()).addValue(generatedUserId).build());

        Uid newId = connector.update(ObjectClass.ACCOUNT, new Uid(generatedUserId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test130UsersGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.ACCOUNT, "", resultsHandler, new OperationOptionsBuilder().build());
        assertTrue(idValues.size() >= 1);
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }

    @Test
    public void test140UserGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.ACCOUNT, generatedUserId, resultsHandler, new OperationOptionsBuilder().build());
        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
    }


    @Test
    public void test210GroupCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(GotoMeetingGroupAttribute.GROUP_NAME.name()).addValue("Flinstones").build());

        Uid newId = connector.create(ObjectClass.GROUP, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedGroupId = newId.getUidValue();
    }

    @Test
    public void test220GroupModify() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(GotoMeetingGroupAttribute.GROUP_NAME.name()).addValue("Flinstones2").build());
        attributes.add(new AttributeBuilder().setName(GotoMeetingGroupAttribute.GROUP_KEY.name()).addValue(generatedGroupId).build());

        Uid newId = connector.update(ObjectClass.GROUP, new Uid(generatedGroupId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test230GroupsGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.GROUP, "", resultsHandler, new OperationOptionsBuilder().build());
        assertTrue(idValues.size() >= 1);
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }

    @Test
    public void test240GroupGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.GROUP, generatedGroupId, resultsHandler, new OperationOptionsBuilder().build());
        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }

    @Test
    public void test290GroupDelete() {
        connector.delete(ObjectClass.GROUP, new Uid(generatedGroupId), new OperationOptionsBuilder().build());
    }

    @Test
    public void test390UserDelete() {
        connector.delete(ObjectClass.ACCOUNT, new Uid(generatedUserId), new OperationOptionsBuilder().build());
    }

}