package org.aha;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class RandGen {

    public static void main(final String[] args) throws IOException {
        if(args.length != 1){
            return;
        }

        final Integer size = Integer.parseInt(args[0]);
        Path temp = Paths.get(System.getProperty("java.io.tmpdir"));
        Path inputFile = temp.resolve(Integer.toString(size) + ".random");

        BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile.toFile()));
        ThreadLocalRandom.current().ints(size, Integer.MIN_VALUE, Integer.MAX_VALUE)
                .mapToObj(Integer::toString).forEach(i -> {
            try {
                bw.write(i);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        bw.flush();
        bw.close();
        System.out.println("Created " + inputFile.toFile().getAbsolutePath());
    }

}
