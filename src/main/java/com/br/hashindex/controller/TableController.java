package com.br.hashindex.controller;

import com.br.hashindex.model.Configuration;
import com.br.hashindex.model.Database;
import com.br.hashindex.model.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {

    @PostMapping
    public ResponseEntity create(@RequestParam("file") MultipartFile file) throws IOException {
        if (Configuration.getPageSize() == 0 || Configuration.getBucketSize() == 0) {
            Map<String, Object> res = new LinkedHashMap<>();
            res.put("error", "Page size = 0 or Bucket size = 0" );

            return ResponseEntity.status(400).body(res);
        } else {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), "ISO_8859_1")
            );

            Database database = Configuration.getDatabase();
            database.readTable(br);

            Map<String, Object> res = new LinkedHashMap<>();
            res.put("success", "Read Completed");
            res.put(
                "readSize",
                database.getTable().getTuplas().size()
            );
            res.put("pageSize", Configuration.getPageSize());
            res.put("bucketSize", Configuration.getBucketSize());
            res.put("colisionCount", Configuration.getColisionCount());
            res.put("overflowCount", Configuration.getOverflowCount());

            return ResponseEntity.status(201).body(res);
        }
    }

    @GetMapping
    public ResponseEntity show() {
        Database database = Configuration.getDatabase();

        Map<String, Object> res = new LinkedHashMap<>();
        res.put(
            "readSize",
            database.getTable().getTuplas().size()
        );
        res.put("pageSize", Configuration.getPageSize());
        res.put("bucketSize", Configuration.getBucketSize());
        res.put("colisionCount", Configuration.getColisionCount());
        res.put("overflowCount", Configuration.getOverflowCount());

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/search")
    public ResponseEntity searchReg(@RequestParam(value = "search") String search) {
        Database database = Configuration.getDatabase();
        Table table = database.getTable();
        Map<String, Object> res = new LinkedHashMap<>();

        if (table == null) {
            res.put("erro", "no table found");
            return ResponseEntity.status(404).body(res);
        }

        int cost = table.getName(search);
        res.put("name", search);
        res.put("cost", cost);

        return ResponseEntity.status(200).body(res);
    }
}
