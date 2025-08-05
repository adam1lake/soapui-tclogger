package io.github.adam1lake.plugins;

import com.eviware.soapui.plugins.PluginAdapter;
import com.eviware.soapui.plugins.PluginConfiguration;
import com.eviware.soapui.model.iface.SoapUIListener;
import com.eviware.soapui.model.project.Project;
import com.eviware.soapui.model.testsuite.TestSuite;
import io.github.adam1lake.listeners.LoggingTestRunListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import java.util.ArrayList;
import java.util.List;

@PluginConfiguration(
    groupId = "io.github.adam1lake.plugins",
    name = "soapui-tclogger",
    version = "0.1.0",
    autoDetect = true,
    description = "A logger plugin for SoapUI test case execution"
)
public class TCLogger extends PluginAdapter {

    public TCLogger() {
        super();
        configureLog4j();
    }

    private void configureLog4j() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setStatusLevel(org.apache.logging.log4j.Level.DEBUG);
        builder.setConfigurationName("PluginLogConfig");

        AppenderComponentBuilder appenderBuilder = builder.newAppender("fileAppender", "RollingFile")
                .addAttribute("fileName", "${sys:user.home}/.soapuios/logs/test-execution.log")
                .addAttribute("filePattern", "${sys:user.home}/.soapuios/logs/test-execution-%i.log")
                .add(builder.newLayout("PatternLayout").
                        addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} - %msg%n"))
                .addComponent(builder.newComponent("Policies")
                        .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "10 MB")))
                .addComponent(builder.newComponent("DefaultRolloverStrategy").addAttribute("max", "7"));

        builder.add(appenderBuilder);

        builder.add(builder.newLogger("io.github.adam1lake.plugins", org.apache.logging.log4j.Level.DEBUG)
                .add(builder.newAppenderRef("fileAppender"))
                .addAttribute("additivity", false));

        builder.add(builder.newLogger("io.github.adam1lake.listeners", org.apache.logging.log4j.Level.DEBUG)
                .add(builder.newAppenderRef("fileAppender"))
                .addAttribute("additivity", false));

        builder.add(builder.newRootLogger(org.apache.logging.log4j.Level.DEBUG)
                .add(builder.newAppenderRef("fileAppender")));

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.reconfigure(builder.build());
    }

    @Override
    public List<Class<? extends SoapUIListener>> getListeners() {
        List<Class<? extends SoapUIListener>> listeners = new ArrayList<>();
        listeners.add(LoggingTestRunListener.class);
        return listeners;
    }
}