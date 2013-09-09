package org.cloudcoder.app.server.persist.txn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.cloudcoder.app.server.persist.util.AbstractDatabaseRunnableNoAuthException;
import org.cloudcoder.app.shared.model.Event;
import org.cloudcoder.app.shared.model.EventType;
import org.cloudcoder.app.shared.model.Hint;
import org.cloudcoder.app.shared.model.Problem;
import org.cloudcoder.app.shared.model.User;

public class StoreHintRequest extends AbstractDatabaseRunnableNoAuthException<Boolean>
{
    private User user;
    private Problem problem;
    private Hint[] hints;
    
    public StoreHintRequest(User user, Problem problem, Hint[] hints) {
        this.user=user;
        this.problem=problem;
        this.hints=hints;
    }

    @Override
    public String getDescription() {
        return "Storing a hint request event";
    }

    @Override
    public Boolean run(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "insert into " + Event.SCHEMA.getDbTableName() + 
                " values (NULL, ?, ?, ?, ?)"); 
        stmt.setInt(1, user.getId());
        stmt.setInt(2, problem.getProblemId());
        stmt.setInt(3, EventType.HINT_REQUEST.ordinal());
        stmt.setLong(4, System.currentTimeMillis());
        stmt.executeUpdate();
        // TODO: get the lastInsertId() from the autoupdate
        // and use it to insert the text of each hint into
        // a new table
        return true;
    }
}
