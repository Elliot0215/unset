package com.wp.unset.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import com.wp.unset.action.dialog.FormTestDialog;
import org.jetbrains.annotations.NotNull;

public class MyAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        FormTestDialog formTestDialog = new FormTestDialog(e.getProject());
        formTestDialog.setResizable(true);
        formTestDialog.show();
    }
}
