import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessingTest {
    protected FileProcessing proc = new FileProcessing();

    @Before
    void before() {
        System.out.println("Выполняюсь до начала тестирования");
    }

    @After
    public void afterTest() {
        System.out.println("Выполняюсь после тестов");
    }

    @Test
    void readFile() throws IOException {
        Path tmpDir = Files.createTempDirectory("tmp");
        tmpDir.toFile().delete();
        Throwable thrown = assertThrows(IOException.class, () -> {
            Files.createTempFile(tmpDir, "test", ".txt");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void testCategory() {
        proc.processingTsv.put("eda", "bylochka");

        boolean title = false;
        Assertions.assertEquals(title, proc.processingTsv.isEmpty());

    }

    @Test
    void testMeaning() {

        proc.processingTsv.put("eda", "hleb");

        boolean keyTitle = true;
        Assertions.assertEquals(keyTitle, proc.processingTsv.containsKey("eda"));
    }

    @Test
    void testTitle() {
        proc.processingTsv.put("avto", "koleso");

        for (String key : proc.processingTsv.keySet()) {
            System.out.println(key);
            String testKey = "avto";

            Assertions.assertEquals(testKey, key);
        }

    }

    @Test
    void testProductName() {
        proc.processingTsv.put("одежда", "шапка");
        for (String value : proc.processingTsv.values()) {
            String testValue = "шапка";

            Assertions.assertEquals(testValue, value);
        }
    }

}