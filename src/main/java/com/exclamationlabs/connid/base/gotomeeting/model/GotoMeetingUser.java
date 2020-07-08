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

import com.exclamationlabs.connid.base.connector.model.UserIdentityModel;

import java.util.List;

public class GotoMeetingUser implements UserIdentityModel {
    private String key;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String timeZone;
    private String locale;
    private List<String> licenseKeys;
    private List<String> products;
    private List<String> adminRoles;
    private Boolean passwordCreated;
    private Boolean nameLocked;
    private Boolean emailLocked;
    private Boolean identityReadOnly;
    private Boolean canAuthenticate;
    private Boolean admin;
    private String status;
    private Integer inviteCount;

    private String groupKey;
    private String groupName;

    @Override
    public String getIdentityIdValue() {
        return getKey();
    }

    @Override
    public String getIdentityNameValue() {
        return getEmail();
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<String> getLicenseKeys() {
        return licenseKeys;
    }

    public void setLicenseKeys(List<String> licenseKeys) {
        this.licenseKeys = licenseKeys;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<String> adminRoles) {
        this.adminRoles = adminRoles;
    }

    public Boolean getPasswordCreated() {
        return passwordCreated;
    }

    public void setPasswordCreated(Boolean passwordCreated) {
        this.passwordCreated = passwordCreated;
    }

    public Boolean getNameLocked() {
        return nameLocked;
    }

    public void setNameLocked(Boolean nameLocked) {
        this.nameLocked = nameLocked;
    }

    public Boolean getEmailLocked() {
        return emailLocked;
    }

    public void setEmailLocked(Boolean emailLocked) {
        this.emailLocked = emailLocked;
    }

    public Boolean getIdentityReadOnly() {
        return identityReadOnly;
    }

    public void setIdentityReadOnly(Boolean identityReadOnly) {
        this.identityReadOnly = identityReadOnly;
    }

    public Boolean getCanAuthenticate() {
        return canAuthenticate;
    }

    public void setCanAuthenticate(Boolean canAuthenticate) {
        this.canAuthenticate = canAuthenticate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }


    @Override
    public String getAssignedGroupsAttributeName() {
        return null;
    }

    @Override
    public List<String> getAssignedGroupIds() {
        return null;
    }
}
