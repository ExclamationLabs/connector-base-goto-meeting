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

package com.exclamationlabs.connid.base.gotomeeting.model.request;

import com.exclamationlabs.connid.base.gotomeeting.model.GotoMeetingUser;

import java.util.List;

public class UserCreationRequest {

    private List<GotoMeetingUser> users;
    private List<String> adminRoles;
    private List<String> managedGroupKeys;
    private String groupKey;

    public List<String> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<String> adminRoles) {
        this.adminRoles = adminRoles;
    }

    public List<String> getManagedGroupKeys() {
        return managedGroupKeys;
    }

    public void setManagedGroupKeys(List<String> managedGroupKeys) {
        this.managedGroupKeys = managedGroupKeys;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public List<GotoMeetingUser> getUsers() {
        return users;
    }

    public void setUsers(List<GotoMeetingUser> users) {
        this.users = users;
    }
}
