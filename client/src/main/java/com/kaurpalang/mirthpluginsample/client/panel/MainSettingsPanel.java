/*
 * Copyright 2021 Kaur Palang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaurpalang.mirthpluginsample.client.panel;

import com.kaurpalang.mirthpluginsample.shared.MyConstants;
import com.mirth.connect.client.ui.AbstractSettingsPanel;
import com.mirth.connect.client.ui.components.MirthCheckBox;
import com.mirth.connect.client.ui.components.MirthPasswordField;
import com.mirth.connect.client.ui.components.MirthTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainSettingsPanel extends AbstractSettingsPanel {

    /**
     * Create component variables
     */
    private JLabel forcefulLabel;
    private MirthCheckBox forcefulCheckbox;

    private JLabel remoteUsernameLabel;
    private MirthTextField remoteUsernameTextField;

    private JLabel remotePasswordLabel;
    private MirthPasswordField remotePasswordField;

    private JButton testRemoteButton;

    public MainSettingsPanel() {
        // The name of our tab in the Settings menu
        super(MyConstants.SETTINGS_TABNAME_MAIN);
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "", "[][][][grow]"));

        /**
         * Instantiate component variables
         */
        forcefulLabel = new JLabel("Force push:");
        forcefulCheckbox = new MirthCheckBox();
        forcefulLabel.setToolTipText("This is a\nmultiline tooltip ;)");
        forcefulCheckbox.setBackground(Color.WHITE);

        remoteUsernameLabel = new JLabel("Username:");
        remoteUsernameTextField = new MirthTextField();

        remotePasswordLabel = new JLabel("Password:");
        remotePasswordField = new MirthPasswordField();

        testRemoteButton = new JButton("Do a thing button");

        /**
         * Create the base container for our components
         */
        JPanel deetsPanel = new JPanel();
        deetsPanel.setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]12[][grow]", ""));
        deetsPanel.setBackground(Color.WHITE);
        deetsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(204, 204, 204)),
                        "MyPlugin",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font("Tahoma", 1, 11)
                )
        );

        /**
         * Add our components to the base container
         */
        deetsPanel.add(forcefulLabel, "right");
        deetsPanel.add(forcefulCheckbox);
        deetsPanel.add(remoteUsernameLabel, "newline, right");
        deetsPanel.add(remoteUsernameTextField, "w 168!");
        deetsPanel.add(remotePasswordLabel, "newline, right");
        deetsPanel.add(remotePasswordField, "w 168!");
        deetsPanel.add(testRemoteButton, "newline");

        /**
         * Add base container to Mirth's more base-er container
         */
        add(deetsPanel, "growx");
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public boolean doSave() {
        return false;
    }
}
