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

package com.exclamationlabs.connid.base.gotomeeting.driver.rest;

import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.connector.driver.rest.BaseRestDriver;
import com.exclamationlabs.connid.base.connector.driver.rest.RestFaultProcessor;
import com.exclamationlabs.connid.base.gotomeeting.configuration.GotoMeetingConfiguration;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingGroup;
import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingUser;
import com.exclamationlabs.connid.base.gotomeeting.model.request.UserCreationRequest;
import com.exclamationlabs.connid.base.gotomeeting.model.response.ListGroupsResponse;
import com.exclamationlabs.connid.base.gotomeeting.model.response.ListUsersResponse;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GotoMeetingDriver extends BaseRestDriver<GotoMeetingUser, GotoMeetingGroup> {

    private String getAccountUrl() {
        return "accounts/" + configuration.getProperty(GotoMeetingConfiguration.ACCOUNT_KEY);
    }

    @Override
    protected RestFaultProcessor getFaultProcessor() {
        return GotoMeetingFaultProcessor.getInstance();
    }

    @Override
    protected String getBaseServiceUrl() {
        return "https://api.getgo.com/admin/rest/v1/";
    }

    @Override
    protected boolean usesBearerAuthorization() {
        return true;
    }

    @Override
    public Set<ConnectorProperty> getRequiredPropertyNames() {
        return null;
    }

    @Override
    public void test() throws ConnectorException {
        try {
            GotoMeetingUser meUser = executeGetRequest("me", GotoMeetingUser.class);
            if (meUser == null) {
                throw new ConnectorException("Self-identification for GotoMeeting connection user returned invalid response.");
            }
        } catch (Exception e) {
            throw new ConnectorException("Self-identification for GotoMeeting connection user failed.", e);
        }
    }

    @Override
    public void close() {
        configuration = null;
        authenticator = null;
    }

    @Override
    public String createUser(GotoMeetingUser userModel) throws ConnectorException {
        UserCreationRequest requestData = new UserCreationRequest();
        requestData.setUsers(new ArrayList<>());
        requestData.getUsers().add(userModel);
        GotoMeetingUser[] newUsers = executePostRequest(getAccountUrl() + "/users?allOrNothing=false", GotoMeetingUser[].class, requestData);

        if (newUsers == null || newUsers.length == 0) {
            throw new ConnectorException("Response from user creation was invalid");
        }
        return newUsers[0].getKey();
    }

    @Override
    public String createGroup(GotoMeetingGroup groupModel) throws ConnectorException {
        return executePostRequest(getAccountUrl() + "/groups", String.class, groupModel);
    }

    @Override
    public void updateUser(String userId, GotoMeetingUser userModel) throws ConnectorException {
        String userKey = userModel.getKey();
        userModel.setKey(null); // cannot pass key value in update JSON, can only be present in URL
        executePutRequest(getAccountUrl() + "/users/" + userKey + "?allOrNothing=true", null, userModel);
    }

    @Override
    public void updateGroup(String groupId, GotoMeetingGroup groupModel) throws ConnectorException {
        GotoMeetingGroup modifyGroup = new GotoMeetingGroup();
        // Cannot send key in update JSON, and name is the only field to update,
        // so create a new object w/ just the name set
        modifyGroup.setName(groupModel.getName());

        executePutRequest(getAccountUrl() + "/groups/" + groupModel.getKey(), null, modifyGroup);
    }

    @Override
    public void deleteUser(String userId) throws ConnectorException {
        executeDeleteRequest(getAccountUrl() + "/users/" + userId, null);
    }

    @Override
    public void deleteGroup(String groupId) throws ConnectorException {
        executeDeleteRequest(getAccountUrl() + "/groups/" + groupId, null);
    }

    @Override
    public List<GotoMeetingUser> getUsers() throws ConnectorException {
        ListUsersResponse response = executeGetRequest(getAccountUrl() + "/users", ListUsersResponse.class);
        return response.getResults();
    }

    @Override
    public List<GotoMeetingGroup> getGroups() throws ConnectorException {
        ListGroupsResponse response = executeGetRequest(getAccountUrl() + "/groups", ListGroupsResponse.class);
        return response.getResults();
    }

    @Override
    public GotoMeetingUser getUser(String userId) throws ConnectorException {
        return executeGetRequest(getAccountUrl() + "/users/" + userId, GotoMeetingUser.class);
    }

    @Override
    public GotoMeetingGroup getGroup(String groupId) throws ConnectorException {
        return executeGetRequest(getAccountUrl() + "/groups/" + groupId, GotoMeetingGroup.class);
    }

    @Override
    public void addGroupToUser(String groupId, String userId) throws ConnectorException {
        executePutRequest(getAccountUrl() + "/groups/" + groupId + "/users/" + userId,
                null, null);
    }

    @Override
    public void removeGroupFromUser(String groupId, String userId) throws ConnectorException {
        // Note: GotoMeeting only seems to support removing a user from ALL groups it
        // currently belongs to.  So that is the logic that is invoked here.
        executeDeleteRequest(getAccountUrl() + "/users/" + userId + "/groups", null);
    }
}