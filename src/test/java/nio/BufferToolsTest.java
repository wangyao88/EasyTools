package nio;


import com.mohan.project.easytools.file.PathTools;
import com.mohan.project.easytools.nio.BufferTools;
import org.junit.Test;

import java.io.IOException;

public class BufferToolsTest {

    @Test
    public void testProcessBuffer() throws IOException {
        String path = PathTools.getPathInClassesPath("buffer.txt");
        BufferTools.processBuffer(path, (character) -> System.out.print(character));
    }
}