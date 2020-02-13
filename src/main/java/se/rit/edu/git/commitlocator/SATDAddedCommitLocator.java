package se.rit.edu.git.commitlocator;

import org.eclipse.jgit.api.Git;
import se.rit.edu.satd.SATDInstance;

public class SATDAddedCommitLocator extends CommitLocator {

    @Override
    public String findCommitAddressed(Git gitInstance, SATDInstance satdInstance, String v1, String v2) {
        // SATD was not addressed, so there is no commit which addressed it
        satdInstance.setResolution(SATDInstance.SATDResolution.SATD_ADDED);
        return SATDInstance.NO_COMMIT;
    }

}