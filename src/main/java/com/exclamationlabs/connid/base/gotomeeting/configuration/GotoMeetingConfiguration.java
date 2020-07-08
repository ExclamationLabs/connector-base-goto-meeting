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

package com.exclamationlabs.connid.base.gotomeeting.configuration;

import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import org.identityconnectors.framework.spi.ConfigurationClass;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.Collections;
import java.util.List;

@ConfigurationClass(skipUnsupported = true)
public class GotoMeetingConfiguration extends BaseConnectorConfiguration {

    public static final String ACCOUNT_KEY = "CONNECTOR_GOTO_MEETING_ACCOUNT_KEY";

    public GotoMeetingConfiguration() {
        super();
    }

    public GotoMeetingConfiguration(String configurationName) {
        super(configurationName);
    }

    @Override
    public List<String> getAdditionalPropertyNames() {
        return Collections.singletonList(ACCOUNT_KEY);
    }

    @Override
    @ConfigurationProperty(
            displayMessageKey = "GotoMeeting Configuration File Path",
            helpMessageKey = "File path for the GotoMeeting Configuration File",
            required = true)
    public String getConfigurationFilePath() {
        return getMidPointConfigurationFilePath();
    }
}
