package com.wjakobczyk.meme_me.controller;


import com.wjakobczyk.meme_me.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class ImageController {
    private final PostRepository postRepository;


    @PostMapping(value = "/upload/images/memes/{category}/{path}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @PathVariable String category, @PathVariable String path) throws IOException {
        System.out.println(category);
        System.out.println(path);
        file.transferTo(new File("F:/SpringUdemyKursPL/WebProjectApp — kopia/src/main/resources/images/memes/"+category+"/"+path));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/images/memes/{category}/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable String category, @PathVariable String name) throws IOException {
        InputStream stream = new FileInputStream("F:/SpringUdemyKursPL/WebProjectApp — kopia/src/main/resources/images/memes/"+category+"/"+name);  // Relative do not work without rebuild
        if (stream == null) {
            throw new FileNotFoundException("readFilesInBytes: File "
                    + " does not exist");
        }
        return IOUtils.toByteArray(stream);


    }
}
