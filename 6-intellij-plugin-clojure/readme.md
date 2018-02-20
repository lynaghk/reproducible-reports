## Problem

I'd like to ship a Clojure "uberjar" as a dependency in an IntelliJ plugin.
However, attempting to invoke Clojure via the Java API:

```java

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class HelloWorldAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
    
        IFn plus = Clojure.var("clojure.core", "+");
        System.out.println(plus.invoke(1, 2));

    }
}
```

blows up with

    Caused by: java.io.FileNotFoundException: Could not locate clojure/core__init.class or clojure/core.clj on classpath.

Curiously, the stacktrace does show that several Clojure-related bits are working properly (and `core__init.class` does exist in the uberjar), so I suspect this is some kind of fancy classloader stuff that's above my pay grade.


## Steps to reproduce

Run:
    
    mkdir -p lib/

    cd uberjar
    lein uberjar && cp target/*-standalone.jar ../lib


Then open up the project in IntelliJ and press the compile/run button to open up a debug build of IntelliJ with the plugin installed.
In this new IntelliJ window, open any file (to get in the project view) and then press the "Hello World" menu button.

In the first IntelliJ window, the `idea.log` console will show:

```
2018-02-20 20:55:28,816 [  36282]  ERROR - llij.ide.plugins.PluginManager - null 
java.lang.ExceptionInInitializerError
	at clojure.lang.Namespace.<init>(Namespace.java:34)
	at clojure.lang.Namespace.findOrCreate(Namespace.java:176)
	at clojure.lang.Var.intern(Var.java:148)
	at clojure.java.api.Clojure.var(Clojure.java:82)
	at clojure.java.api.Clojure.<clinit>(Clojure.java:96)
	at com.fooplugin.HelloWorldAction.actionPerformed(HelloWorldAction.java:22)
	at com.intellij.openapi.actionSystem.ex.ActionUtil$1.run(ActionUtil.java:220)
	at com.intellij.openapi.actionSystem.ex.ActionUtil.performActionDumbAware(ActionUtil.java:237)
	at com.intellij.openapi.actionSystem.impl.ActionMenuItem$ActionTransmitter.lambda$actionPerformed$0(ActionMenuItem.java:321)
	at com.intellij.openapi.wm.impl.FocusManagerImpl.runOnOwnContext(FocusManagerImpl.java:911)
	at com.intellij.openapi.wm.impl.IdeFocusManagerImpl.runOnOwnContext(IdeFocusManagerImpl.java:136)
	at com.intellij.openapi.actionSystem.impl.ActionMenuItem$ActionTransmitter.actionPerformed(ActionMenuItem.java:311)
	at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:2022)
	at com.intellij.openapi.actionSystem.impl.ActionMenuItem.lambda$fireActionPerformed$0(ActionMenuItem.java:130)
	at com.intellij.openapi.application.TransactionGuardImpl.runSyncTransaction(TransactionGuardImpl.java:88)
	at com.intellij.openapi.application.TransactionGuardImpl.lambda$submitTransaction$1(TransactionGuardImpl.java:111)
	at com.intellij.openapi.application.TransactionGuardImpl.submitTransaction(TransactionGuardImpl.java:120)
	at com.intellij.openapi.application.TransactionGuard.submitTransaction(TransactionGuard.java:122)
	at com.intellij.openapi.actionSystem.impl.ActionMenuItem.fireActionPerformed(ActionMenuItem.java:130)
	at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2348)
	at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:402)
	at javax.swing.JToggleButton$ToggleButtonModel.setPressed(JToggleButton.java:308)
	at javax.swing.AbstractButton.doClick(AbstractButton.java:376)
	at com.apple.laf.ScreenMenuItemCheckbox.itemStateChanged(ScreenMenuItemCheckbox.java:193)
	at java.awt.CheckboxMenuItem.processItemEvent(CheckboxMenuItem.java:389)
	at java.awt.CheckboxMenuItem.processEvent(CheckboxMenuItem.java:357)
	at java.awt.MenuComponent.dispatchEventImpl(MenuComponent.java:357)
	at java.awt.MenuComponent.dispatchEvent(MenuComponent.java:345)
	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:761)
	at java.awt.EventQueue.access$500(EventQueue.java:97)
	at java.awt.EventQueue$3.run(EventQueue.java:709)
	at java.awt.EventQueue$3.run(EventQueue.java:703)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:80)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:90)
	at java.awt.EventQueue$4.run(EventQueue.java:731)
	at java.awt.EventQueue$4.run(EventQueue.java:729)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:80)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:728)
	at com.intellij.ide.IdeEventQueue.defaultDispatchEvent(IdeEventQueue.java:822)
	at com.intellij.ide.IdeEventQueue._dispatchEvent(IdeEventQueue.java:650)
	at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:366)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:201)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:116)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:105)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:93)
	at java.awt.EventDispatchThread.run(EventDispatchThread.java:82)
Caused by: java.io.FileNotFoundException: Could not locate clojure/core__init.class or clojure/core.clj on classpath.
	at clojure.lang.RT.load(RT.java:463)
	at clojure.lang.RT.load(RT.java:426)
	at clojure.lang.RT.doInit(RT.java:468)
	at clojure.lang.RT.<clinit>(RT.java:336)
	... 49 more
```

