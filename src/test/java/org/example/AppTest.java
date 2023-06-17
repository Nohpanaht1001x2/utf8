package org.example;

import org.junit.Test;
import th.co.geniustre.intern.unicode.UTF8Decoder;
import th.co.geniustre.intern.unicode.UTF8Decoder1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class AppTest {
    @Test
    public void create_file_for_test() throws IOException {
        Files.writeString(Path.of(".","target","testfile.txt"),"กขค", StandardCharsets.UTF_8, WRITE,CREATE,TRUNCATE_EXISTING);
    }
    @Test
    public void display_byte_as_binary() throws IOException {
        byte[] utfBytes = Files.readAllBytes(Path.of(".", "target", "testfile.txt"));
        System.out.println(UTF8Decoder1.decode(utfBytes));
        for(byte b : utfBytes){
//            System.out.println(Integer.toBinaryString(b).substring(24));
        }

    }
}
