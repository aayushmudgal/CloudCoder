package org.cloudcoder.app.server.persist.txn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.cloudcoder.app.server.persist.util.AbstractDatabaseRunnableNoAuthException;
import org.cloudcoder.app.shared.model.Problem;
import org.cloudcoder.app.shared.model.ProblemAnalysisTagUrl;
import org.cloudcoder.app.shared.model.UserCourseTag;
import org.cloudcoder.app.shared.model.UserProblemTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetProblemAnalysisTagUrls extends AbstractDatabaseRunnableNoAuthException<ProblemAnalysisTagUrl[]> {
    private static final Logger logger=LoggerFactory.getLogger(GetProblemAnalysisTagUrls.class);
    
    private Integer userId;
    private Integer problemId;
    
    public GetProblemAnalysisTagUrls(Integer userId, Integer problemId) {
        this.userId=userId;
        this.problemId=problemId;
    }
    @Override
    public String getDescription() {
        return "Retrieving the problem analysis URLs for this problem";
    }

    @Override
    public ProblemAnalysisTagUrl[] run(Connection conn) throws SQLException {
        Set<String> tagSet=new HashSet<String>();
        // First lookup tags by course
        List<ProblemAnalysisTagUrl> resultList=getByCourse(conn);
        logger.info("by course: "+resultList.size());
        
        // Next lookup tags by problem
        List<ProblemAnalysisTagUrl> byProblem=getByProblem(conn);
        logger.info("by problem: "+byProblem.size());

        // mark which tags we found for the courses
        for (ProblemAnalysisTagUrl p : resultList) {
            tagSet.add(p.getTag());
        }
        // add the per-problem tags we found that we didn't already find for the course
        // necessary because we can't make the DB enforce tag constraints
        // across two different tables
        for (ProblemAnalysisTagUrl p : byProblem) {
            if (!tagSet.contains(p.getTag())) {
                tagSet.add(p.getTag());
                resultList.add(p);
            }
        }
        return resultList.toArray(new ProblemAnalysisTagUrl[resultList.size()]);
    }
    
    private List<ProblemAnalysisTagUrl> getByCourse(Connection conn) throws SQLException {
        PreparedStatement stmt = prepareStatement(
                conn,
                "select pa.* " +
                " from "+ProblemAnalysisTagUrl.SCHEMA.getDbTableName()+" as pa, " +
                        Problem.SCHEMA.getDbTableName()+" as p, " +
                        UserCourseTag.SCHEMA.getDbTableName()+" as uc " +
                        " WHERE 1 " +
                        " and p.problem_id = ? " +
                        " and uc.user_id = ? " +
                        " and p.course_id = uc.course_id " +
                        " and pa.problem_id = p.problem_id " +
                        " and pa.tag = uc.tag ");

        stmt.setInt(1, problemId);
        stmt.setInt(2, userId);
        
        List<ProblemAnalysisTagUrl> resultList=new LinkedList<ProblemAnalysisTagUrl>();
        
        ResultSet resultSet = executeQuery(stmt);
        while (resultSet.next()) {
            ProblemAnalysisTagUrl problemAnalysisTagUrl=new ProblemAnalysisTagUrl();
            Queries.loadGeneric(problemAnalysisTagUrl, resultSet, 1, ProblemAnalysisTagUrl.SCHEMA);
            resultList.add(problemAnalysisTagUrl);
        }
        return resultList;
    }
    
    
    private List<ProblemAnalysisTagUrl> getByProblem(Connection conn) throws SQLException {
        PreparedStatement stmt = prepareStatement(
                conn,
                "select pa.* " +
                " from "+ProblemAnalysisTagUrl.SCHEMA.getDbTableName()+" as pa, " +
                        Problem.SCHEMA.getDbTableName()+" as p, " +
                        UserProblemTag.SCHEMA.getDbTableName()+" as up " +
                        " WHERE 1 " +
                        " and p.problem_id = ? " +
                        " and pa.problem_id = up.problem_id " +
                        " and up.user_id = ? " +
                        " and pa.tag = up.tag ");

        stmt.setInt(1, problemId);
        stmt.setInt(2, userId);

        List<ProblemAnalysisTagUrl> resultList=new LinkedList<ProblemAnalysisTagUrl>();
        
        ResultSet resultSet = executeQuery(stmt);
        while (resultSet.next()) {
            ProblemAnalysisTagUrl problemAnalysisTagUrl=new ProblemAnalysisTagUrl();
            Queries.loadGeneric(problemAnalysisTagUrl, resultSet, 1, ProblemAnalysisTagUrl.SCHEMA);
            resultList.add(problemAnalysisTagUrl);
        }
        return resultList;
    }
}