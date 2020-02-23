package se.rit.edu.satd;

import com.sun.istack.internal.NotNull;
import se.rit.edu.git.models.CommitMetaData;
import se.rit.edu.git.models.NullCommitMetaData;
import se.rit.edu.util.GroupedComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class which stores information about the SATD Instance
 */
public class SATDInstance {

    // String constants for output
    public static final String COMMIT_UNKNOWN = "Commit Unknown";
    public static final String NO_COMMIT = "None";
    public static final String ERROR_FINDING_COMMIT = "ERROR_FINDING";
    public static final String FILE_UNKNOWN = "File Unknown";
    public static final String FILE_NONE = "None";
    public static final String FILE_DEV_NULL = "dev/null";
    public static final String COMMENT_NONE = "None";

    // SATD Instance mandatory fields
    private String oldFile;
    private String newFile;
    private GroupedComment commentOld = null;
    private GroupedComment commentNew = null;

    // SATD Instance other fields that maintain defaults
    @Deprecated
    private List<String> contributingCommits = new ArrayList<>();
    @Deprecated
    private String commitRemoved = COMMIT_UNKNOWN;

    private List<CommitMetaData> initialBlameCommits = new ArrayList<>();
    // Contains only commits that touched the SATD comment
    private List<CommitMetaData> commitsBetweenVersions = new ArrayList<>();
    private CommitMetaData commitAddressed = new NullCommitMetaData();

    private String nameOfFileWhenAddressed = FILE_UNKNOWN;
    private SATDResolution resolution = SATDResolution.UNKNOWN;

    public SATDInstance(@NotNull String oldFile, @NotNull String newFile, @NotNull GroupedComment satdComment) {
        this.oldFile = oldFile;
        this.newFile = newFile;
        if( oldFile.equals(FILE_DEV_NULL) ) {
            this.commentNew = satdComment;
        } else {
            this.commentOld = satdComment;
        }
    }

    @Deprecated
    public List<String> getContributingCommits() {
        return this.contributingCommits;
    }

    @Deprecated
    public String getCommitRemoved() {
        return commitRemoved;
    }

    public String getNameOfFileWhenAddressed() {
        return nameOfFileWhenAddressed;
    }

    public SATDResolution getResolution() {
        return resolution;
    }

    public String getCommentNew() {
        if( this.commentNew == null ) {
            return COMMENT_NONE;
        }
        return this.commentNew.getComment();
    }

    public String getOldFile() {
        return this.oldFile;
    }

    public String getNewFile() {
        return this.newFile;
    }

    public String getCommentOld() {
        if( this.commentOld == null ) {
            return COMMENT_NONE;
        }
        return this.commentOld.getComment();
    }

    public GroupedComment getCommentGroupOld() {
        return this.commentOld;
    }

    public int getStartLineNumberOldFile() {
        if( this.commentOld == null ) {
            return 0;
        }
        return this.commentOld.getStartLine();
    }

    public int getEndLineNumberOldFile() {
        if( this.commentOld == null ) {
            return 0;
        }
        return this.commentOld.getEndLine();
    }

    public int getStartLineNumberNewFile() {
        if( this.commentNew == null ) {
            return 0;
        }
        return this.commentNew.getStartLine();
    }

    public int getEndLineNumberNewFile() {
        if( this.commentNew == null ) {
            return 0;
        }
        return this.commentNew.getEndLine();
    }

    public List<CommitMetaData> getInitialBlameCommits() {
        return this.initialBlameCommits;
    }

    public List<CommitMetaData> getCommitsBetweenVersions() {
        return this.commitsBetweenVersions;
    }

    public CommitMetaData getCommitAddressed() {
        return this.commitAddressed;
    }

    @Deprecated
    public void addContributingCommit(String contributingCommit) {
        this.contributingCommits.add(contributingCommit);
    }

    @Deprecated
    public void setCommitRemoved(String commitRemoved) {
        this.commitRemoved = commitRemoved;
    }

    public void addInitialBlameCommit(CommitMetaData data) {
        this.initialBlameCommits.add(data);
    }

    public void addCommitBetweenVersions(CommitMetaData data) {
        this.commitsBetweenVersions.add(data);
    }

    public void setCommitAddressed(CommitMetaData data) {
        this.commitAddressed = data;
    }

    public void setNameOfFileWhenAddressed(String nameOfFileWhenAddressed) {
        this.nameOfFileWhenAddressed = nameOfFileWhenAddressed;
    }

    public void setResolution(SATDResolution resolution) {
        this.resolution = resolution;
    }

    public void setCommentNew(GroupedComment comment) {
        this.commentNew = comment;
    }

    public void setNewFile(String newFile) {
        this.newFile = newFile;
    }

    public enum SATDResolution {
        UNKNOWN,
        FILE_REMOVED,
        FILE_PATH_CHANGED,
        SATD_REMOVED,
        SATD_POSSIBLY_REMOVED,
        SATD_CHANGED,
        SATD_ADDED,
        SATD_UNADDRESSED,
        ERROR_UNKNOWN
    }
}
