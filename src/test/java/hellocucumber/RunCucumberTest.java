package hellocucumber;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("hellocucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "hellocucumber"),
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/hellocucumber"),
        @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "")
})
public class RunCucumberTest {
}
