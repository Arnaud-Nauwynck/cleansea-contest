package contest.cleansea.tests.test01;

import contest.cleansea.model.*;
import lombok.val;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EmulateSol01Main {

    public static void main(String[] args) {
        Path testDirPath = Paths.get("src/test");

        System.out.println("Emulate Solution for pb 01");
        ProblemInput pbInput = ProblemInputReader.read(testDirPath.resolve("data/01_simple_example.txt"));
        emulatePbSolution("01", pbInput, testDirPath.resolve("data-solutions/01/sol_10lines.txt"));
    }

    private static void emulatePbSolution(String pbName, ProblemInput pbInput, Path solutionFilePath) {
        ProblemSolution sol = ProblemSolutionReader.read(pbInput, solutionFilePath);

        val state = new State(sol);
        for(int step = 0; step < state.input.steps; step++) {
            System.out.println("step " + step + " (score: " + state.getCurrTotalScore() + ")");
            System.out.println(state);
            System.out.println();

            state.step();
        }

        int score = sol.evalScore();
        System.out.println("Final score: " + score);
    }

}
