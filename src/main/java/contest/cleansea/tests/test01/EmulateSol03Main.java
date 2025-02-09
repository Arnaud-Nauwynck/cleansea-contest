package contest.cleansea.tests.test01;

import contest.cleansea.model.*;
import lombok.val;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EmulateSol03Main {

    public static void main(String[] args) {
        Path testDirPath = Paths.get("src/test");

        System.out.println("Emulate Solution for pb 03");
        ProblemInput pbInput = ProblemInputReader.read(testDirPath.resolve("data/03_medium.txt"));

        Path solDirPath = testDirPath.resolve("data-solutions/03");

//        emulatePbSolution("01", pbInput, solDirPath.resolve("sol_2lines_30.txt"), 50); // => score: 276
//        emulatePbSolution("01", pbInput, solDirPath.resolve("sol_2lines_31.txt"), 50); // => score: 287
        emulatePbSolution("01", pbInput, solDirPath.resolve("sol_2lines_32.txt"), 50); // score: 285
        // emulatePbSolution("01", pbInput, solDirPath.resolve("sol_2lines.txt"), 50);
        // emulatePbSolution("01", pbInput, solDirPath.resolve("sol_4lines.txt"), 50);
        // emulatePbSolution("01", pbInput, solDirPath.resolve("sol_6lines.txt"), 50);
        // emulatePbSolution("01", pbInput, solDirPath.resolve("sol_10lines.txt"), 50);
    }

    private static void emulatePbSolution(String pbName, ProblemInput pbInput, Path solutionFilePath,
                                          int freqPrint) {
        ProblemSolution sol = ProblemSolutionReader.read(pbInput, solutionFilePath);

        val state = new State(sol);
        for(int step = 0; step < state.input.steps; step++) {
            if (step % freqPrint == 0) {
                System.out.println("step " + step + " (score: " + state.getCurrTotalScore() + ")");
                System.out.println(state);
                System.out.println();
            }

            state.step();
        }

        System.out.println("Final state (score: " + state.getCurrTotalScore() + ")");
        System.out.println(state);
        System.out.println();

        int score = sol.evalScore();
        System.out.println("Final score: " + score);
    }

}
