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

    private JLabel forcefulLabel;
    private MirthCheckBox forcefulCheckbox;

    private JLabel remoteUsernameLabel;
    private MirthTextField remoteUsernameTextField;

    private JLabel remotePasswordLabel;
    private MirthPasswordField remotePasswordField;

    private JButton testRemoteButton;

    public MainSettingsPanel() {
        super(MyConstants.SETTINGS_TABNAME_MAIN);
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "", "[][][][grow]"));

        forcefulLabel = new JLabel("Force push:");
        forcefulCheckbox = new MirthCheckBox();
        forcefulLabel.setToolTipText("This is a\nmultiline tooltip ;)");
        forcefulCheckbox.setBackground(Color.WHITE);

        remoteUsernameLabel = new JLabel("Username:");
        remoteUsernameTextField = new MirthTextField();

        remotePasswordLabel = new JLabel("Password:");
        remotePasswordField = new MirthPasswordField();

        testRemoteButton = new JButton("Do a thing button");

        JPanel hostDetailsPanel = new JPanel();
        hostDetailsPanel.setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]12[][grow]", ""));
        hostDetailsPanel.setBackground(Color.WHITE);
        hostDetailsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(204, 204, 204)),
                        "MyPlugin",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font("Tahoma", 1, 11)
                )
        );

        hostDetailsPanel.add(forcefulLabel, "right");
        hostDetailsPanel.add(forcefulCheckbox);
        hostDetailsPanel.add(remoteUsernameLabel, "newline, right");
        hostDetailsPanel.add(remoteUsernameTextField, "w 168!");
        hostDetailsPanel.add(remotePasswordLabel, "newline, right");
        hostDetailsPanel.add(remotePasswordField, "w 168!");
        hostDetailsPanel.add(testRemoteButton, "newline");

        add(hostDetailsPanel, "growx");
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public boolean doSave() {
        return false;
    }
}
