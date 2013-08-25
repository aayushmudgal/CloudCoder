package org.cloudcoder.app.shared.model;

public class UserProblemTag implements IModelObject<UserProblemTag>
{
    private Integer userId;
    private Integer problemId;
    private String tag;
    
    public static final ModelObjectField<UserProblemTag, Integer> USER_ID=
            new ModelObjectField<UserProblemTag, Integer>("user_id", Integer.class, 0, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserProblemTag obj, Integer value) { obj.setUserId(value); }
        public Integer get(UserProblemTag obj) { return obj.getUserId(); }
    };
    public static final ModelObjectField<UserProblemTag, Integer> PROBLEM_ID=
            new ModelObjectField<UserProblemTag, Integer>("problem_id", Integer.class, 0, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserProblemTag obj, Integer value) { obj.setProblemId(value); }
        public Integer get(UserProblemTag obj) { return obj.getProblemId(); }
    };
    public static final ModelObjectField<UserProblemTag, String> TAG=
            new ModelObjectField<UserProblemTag, String>("tag", String.class, 128, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserProblemTag obj, String value) { obj.setTag(value); }
        public String get(UserProblemTag obj) { return obj.getTag(); }
    };
    
    public static final ModelObjectSchema<UserProblemTag> SCHEMA_V0 = new ModelObjectSchema<UserProblemTag>("user_problem_tag")
            .add(USER_ID)
            .add(PROBLEM_ID)
            .add(TAG).addIndex(new ModelObjectIndex<UserProblemTag>(ModelObjectIndexType.UNIQUE)
                    .addField(USER_ID)
                    .addField(PROBLEM_ID)
                    .addField(TAG)
                    );
    
    /**
     * Description of fields (current schema version).
     */
    public static final ModelObjectSchema<UserProblemTag> SCHEMA = SCHEMA_V0;
    
    /**
     * Number of fields.
     */
    public static final int NUM_FIELDS = SCHEMA.getNumFields();
    
    
    @Override
    public ModelObjectSchema<? super UserProblemTag> getSchema() {
        return SCHEMA;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
