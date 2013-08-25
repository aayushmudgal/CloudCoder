package org.cloudcoder.app.shared.model;

public class UserCourseTag implements IModelObject<UserCourseTag>
{
    private Integer userId;
    private Integer courseId;
    private String tag;
    
    public static final ModelObjectField<UserCourseTag, Integer> USER_ID=
            new ModelObjectField<UserCourseTag, Integer>("user_id", Integer.class, 0, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserCourseTag obj, Integer value) { obj.setUserId(value); }
        public Integer get(UserCourseTag obj) { return obj.getUserId(); }
    };
    public static final ModelObjectField<UserCourseTag, Integer> COURSE_ID=
            new ModelObjectField<UserCourseTag, Integer>("course_id", Integer.class, 0, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserCourseTag obj, Integer value) { obj.setCourseId(value); }
        public Integer get(UserCourseTag obj) { return obj.getCourseId(); }
    };
    public static final ModelObjectField<UserCourseTag, String> TAG=
            new ModelObjectField<UserCourseTag, String>("tag", String.class, 128, ModelObjectIndexType.NON_UNIQUE) {
        public void set(UserCourseTag obj, String value) { obj.setTag(value); }
        public String get(UserCourseTag obj) { return obj.getTag(); }
    };
    
    public static final ModelObjectSchema<UserCourseTag> SCHEMA_V0 = new ModelObjectSchema<UserCourseTag>("user_course_tag")
            .add(USER_ID)
            .add(COURSE_ID)
            .add(TAG).addIndex(
                    new ModelObjectIndex<UserCourseTag>(ModelObjectIndexType.UNIQUE)
                    .addField(USER_ID)
                    .addField(COURSE_ID)
                    .addField(TAG)
                    );
    
    /**
     * Description of fields (current schema version).
     */
    public static final ModelObjectSchema<UserCourseTag> SCHEMA = SCHEMA_V0;
    
    /**
     * Number of fields.
     */
    public static final int NUM_FIELDS = SCHEMA.getNumFields();
    
    
    @Override
    public ModelObjectSchema<? super UserCourseTag> getSchema() {
        return SCHEMA;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
