package contest.cleansea.model;

import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProblemSolutionReader {

    public static ProblemSolution read(ProblemInput input, Path solutionFilePath) {
        try (BufferedReader br = Files.newBufferedReader(solutionFilePath)) {
            val gridSolution = new EnumDirectionOrBase[input.rows][input.cols];
            for(int i = 0; i < input.rows; i++) {
                String line = br.readLine();
                for(int j = 0; j < input.cols; j++) {
                    gridSolution[i][j] = EnumDirectionOrBase.fromSymbol(line.charAt(j));
                }
            }
            return new ProblemSolution(input, gridSolution);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
