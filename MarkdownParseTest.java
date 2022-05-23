import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

   @Test
   public void test_1() throws IOException{
    Path fileName = Path.of("test-file1.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("`google.com");
    expected.add("google.com");
    expected.add("ucsd.edu");
    assertEquals(expected, links);
}
@Test
public void test_2() throws IOException{
    Path fileName = Path.of("test-file2.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("a.com");
    expected.add("a.com(())");
    expected.add("example.com");
    assertEquals(expected, links);
}
public void test_3() throws IOException{
    Path fileName = Path.of("test-file3.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("https://www.twitter.com");
    expected.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
    expected.add("https://cse.ucsd.edu/");
    assertEquals(expected, links);
}

}