package contest.cleansea.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProblemInputReader {

    public static ProblemInput read(Path inputPath) {
        return read(ProblemInput.DEFAULT_STEPS, inputPath);
    }

    public static ProblemInput read(int steps, Path inputPath) {
        try (BufferedReader br = Files.newBufferedReader(inputPath)) {
            String line = br.readLine();
            String[] parts = line.split(" ");
            int rows = Integer.parseInt(parts[0]);
            int cols = Integer.parseInt(parts[1]);
            int boatsCount = Integer.parseInt(parts[2]);
            boolean[][] wasteAt = new boolean[rows][cols];
            for(int i = 0; i < rows; i++) {
                line = br.readLine();
                for(int j = 0; j < cols; j++) {
                    wasteAt[i][j] = line.charAt(j) == 'X';
                }
            }
            return new ProblemInput(steps, rows, cols, boatsCount, wasteAt);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
