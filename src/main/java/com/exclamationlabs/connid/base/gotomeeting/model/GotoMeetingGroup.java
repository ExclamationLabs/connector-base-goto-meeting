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

package com.exclamationlabs.connid.base.gotomeeting.model;

import com.exclamationlabs.connid.base.connector.model.GroupIdentityModel;

import java.util.List;

public class GotoMeetingGroup implements GroupIdentityModel {
    private String key;
    private String name;
    private List<String> keyPath;
    private List<String> namePath;
    private List<String> userKeys;
    private Integer totalMemberCount;

    @Override
    public String getIdentityIdValue() {
        return getKey();
    }

    @Override
    public String getIdentityNameValue() {
        return getName();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(List<String> keyPath) {
        this.keyPath = keyPath;
    }

    public List<String> getNamePath() {
        return namePath;
    }

    public void setNamePath(List<String> namePath) {
        this.namePath = namePath;
    }

    public List<String> getUserKeys() {
        return userKeys;
    }

    public void setUserKeys(List<String> userKeys) {
        this.userKeys = userKeys;
    }

    public Integer getTotalMemberCount() {
        return totalMemberCount;
    }

    public void setTotalMemberCount(Integer totalMemberCount) {
        this.totalMemberCount = totalMemberCount;
    }

}
