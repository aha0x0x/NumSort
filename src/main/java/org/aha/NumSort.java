package org.aha;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class NumSort {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            return;
        }

        final Path input = Paths.get(args[0]);
        final Path output = Paths.get(input.toFile().getParent()).resolve("jsorted-" + input.toFile().getName());

        Instant start = Instant.now();
        writeUsingFileWriter(input, output);
        Instant end = Instant.now();
        System.out.println("Input:" + input);
        System.out.println("output:" + output);
        System.out.println("took: "  + ChronoUnit.MILLIS.between(start,end) + " ms (" + ChronoUnit.SECONDS.between(start,end) + " secs)");
    }

    /**
     * Sort and write using Buffered FileWriter
     * @param input
     * @param output
     * @throws IOException
     */
    private static void writeUsingFileWriter(final Path input, final Path output) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(output.toFile()));
        Files.lines(input).mapToInt(Integer::parseInt).sorted().mapToObj(Integer::toString).forEach(i -> {
            try {
                bw.write(i);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.flush();
        bw.close();
    }

    /**
     * Sort and write using DataOutputStream, no explicit conversion from int to string
     * @param input input file
     * @param output sorted output
     * @throws IOException
     */
    private static void writeUsingDataOutputStream(final Path input, final Path output) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(output.toFile()));
        Files.lines(input).mapToInt(Integer::parseInt).sorted().forEach(i -> {
            try {
                dos.write(i);
                dos.writeChars("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dos.flush();
        dos.close();
    }



}
