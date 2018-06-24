package com.ing.classification.business.reader;

import com.ing.classification.result.Transaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class TransactionReader {

    public List<Transaction> readTransactions() {
        return readTransactions("data.txt");
    }

    public List<Transaction> readTransactions(String fileName) {
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Transaction.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
