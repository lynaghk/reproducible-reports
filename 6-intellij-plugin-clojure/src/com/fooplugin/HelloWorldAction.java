package com.fooplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;


import clojure.java.api.Clojure;
import clojure.lang.IFn;


import java.net.URL;
import java.net.URLClassLoader;

public class HelloWorldAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {


        IFn plus = Clojure.var("clojure.core", "+");
        System.out.println(plus.invoke(1, 2));


        Project project = e.getProject();
        Messages.showMessageDialog(project, "Hello World", "Title", Messages.getInformationIcon());
    }
}
