//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        int backindex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if(openBracket == -1){
                break;
            }
            int backtick = markdown.indexOf("`",backindex);
            backindex = markdown.indexOf(")",currentIndex)+1;
            if(backtick < openBracket){
                currentIndex = openBracket+1;
                continue;
            }
            //System.out.println(openBracket);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            //System.out.println(closeBracket);
            //System.out.println(openParen);
            if(openParen == -1){
                break;
            }
            /*
            if(openParen != openBracket+1){
                currentIndex = openParen;
                continue;
            }
            */
            int closeParen = markdown.indexOf(")", openParen);
            //System.out.println(closeParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            //System.out.println(toReturn);
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("test-file1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
