package no.ldx.project;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static no.ldx.project.ProjectProperties.CONFIG_DIR;
import static no.ldx.project.ProjectProperties.ENV_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * IMPORTANT!! This class uses configuration properties provided when you run the test command and
 * will create problems if you don't provide the maven profile to run with, as there's
 * configuration properties in the maven profile "test" you will need!
 */
public class ProjectPropertiesTest {

    @Test
    public void shouldLoadPropertyFile() {
        ProjectProperties.init("application.properties");
        Properties systemProperties = System.getProperties();
        assertEquals("resources", systemProperties.getProperty("config.file"));
    }

    @Test
    public void shouldUseConfigDirAsDefaultFileIfPropertyIsNotSet() {
        ProjectProperties.init("not.set.config.arg", "application.properties");
        Properties systemProperties = System.getProperties();

        assertEquals("weird", systemProperties.getProperty("config.file"));
    }

    @Test
    public void resolveDirShouldReturnLocalResources() {
        String result = ProjectProperties.resolveDir(CONFIG_DIR);
        assertEquals("src/main/resources", result);
    }

    @Test
    public void shouldUseEtcDirConfigFile() {
        String result = ProjectProperties.resolveDir(ENV_DIR);
        assertEquals("etc", result);
    }

    @Test
    public void shouldThrowExceptionWhenPropertyFileNotFound() {
        String filenameThatDontExist = "notfound.properties";
        ProjectPropertyFileException thrown = assertThrows(ProjectPropertyFileException.class, () -> {
            ProjectProperties.init(CONFIG_DIR, filenameThatDontExist);
        });
        assertEquals(ProjectPropertyFileException.class, thrown.getClass());
    }
}
