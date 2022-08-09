package com.wp.unset.action.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.wp.unset.action.swing.FormTestSwing;

import javax.annotation.Nullable;
import javax.swing.*;

public class FormTestDialog extends DialogWrapper {
 
    private String projectName;
    private FormTestSwing formTestSwing = new FormTestSwing();
 
    public FormTestDialog(@Nullable Project project) {
        super(true);
        setTitle("wp");
        this.projectName = project.getName();
        init();
    }
 
    @Override
    protected JComponent createNorthPanel() {
        return formTestSwing.initNorth();
    }

    @Override
    protected JComponent createSouthPanel() {
        return formTestSwing.initSouth();
    }
 
    @Override
    protected JComponent createCenterPanel() {
        //定义表单的主题，放置到IDEA会话框的中央位置
        return formTestSwing.initCenter();
    }
}
