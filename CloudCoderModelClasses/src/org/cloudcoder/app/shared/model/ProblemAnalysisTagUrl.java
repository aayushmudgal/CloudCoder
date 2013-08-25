package org.cloudcoder.app.shared.model;

import java.io.Serializable;

public class ProblemAnalysisTagUrl implements IModelObject<ProblemAnalysisTagUrl>, Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Integer problemId;
    private String tag;
    private String analysisUrl;
    
    /** {@link ModelObjectField} for problem id. */
    public static final ModelObjectField<ProblemAnalysisTagUrl, Integer> PROBLEM_ID =
            new ModelObjectField<ProblemAnalysisTagUrl, Integer>("problem_id", Integer.class, 0, ModelObjectIndexType.NON_UNIQUE) {
        public void set(ProblemAnalysisTagUrl obj, Integer value) { obj.setProblemId(value); }
        public Integer get(ProblemAnalysisTagUrl obj) { return obj.getProblemId(); }
    };
    /** {@link ModelObjectField} for tag. */
    public static final ModelObjectField<ProblemAnalysisTagUrl, String> TAG =
            new ModelObjectField<ProblemAnalysisTagUrl, String>("tag", String.class, 128, ModelObjectIndexType.NON_UNIQUE) {
        public void set(ProblemAnalysisTagUrl obj, String value) { obj.setTag(value); }
        public String get(ProblemAnalysisTagUrl obj) { return obj.getTag(); }
    };
    /** {@link ModelObjectField} for tag. */
    public static final ModelObjectField<ProblemAnalysisTagUrl, String> URL =
            new ModelObjectField<ProblemAnalysisTagUrl, String>("url", String.class, 256, ModelObjectIndexType.NONE) {
        public void set(ProblemAnalysisTagUrl obj, String value) { obj.setAnalysisUrl(value); }
        public String get(ProblemAnalysisTagUrl obj) { return obj.getAnalysisUrl(); }
    };
    
    public static final ModelObjectSchema<ProblemAnalysisTagUrl> SCHEMA_V0 = new ModelObjectSchema<ProblemAnalysisTagUrl>("problem_analysis_tag_url")
            .add(PROBLEM_ID)
            .add(TAG)
            .add(URL).addIndex(new ModelObjectIndex<ProblemAnalysisTagUrl>(ModelObjectIndexType.UNIQUE)
                    .addField(PROBLEM_ID)
                    .addField(TAG)
                    );
    
    /**
     * Description of fields (current schema version).
     */
    public static final ModelObjectSchema<ProblemAnalysisTagUrl> SCHEMA = SCHEMA_V0;
    
    /**
     * Number of fields.
     */
    public static final int NUM_FIELDS = SCHEMA.getNumFields();
    
    @Override
    public ModelObjectSchema<? super ProblemAnalysisTagUrl> getSchema() {
        return SCHEMA;
    }
    public Integer getProblemId() {
        return problemId;
    }
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getAnalysisUrl() {
        return analysisUrl;
    }
    public void setAnalysisUrl(String analysisUrl) {
        this.analysisUrl = analysisUrl;
    }
}
