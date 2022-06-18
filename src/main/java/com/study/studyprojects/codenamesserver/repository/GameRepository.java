package com.study.studyprojects.codenamesserver.repository;

import com.study.studyprojects.codenamesserver.utils.JdbcConnection;
import com.study.studyprojects.model.GameStatuses;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class GameRepository {

    public boolean createGame(Long ownerId) throws SQLException {

        Connection con = JdbcConnection.getConnection();

        PreparedStatement query = con.prepareStatement("INSERT INTO games (owner_id, status) VALUES (?, ?)");

        query.setLong(1, ownerId);
        query.setString(2, GameStatuses.CREATED);

        return query.executeUpdate() > 0;
    }

    public ResultSet findLatestCreatedGameByOwnerId(Long ownerId) throws SQLException {

        Connection con = JdbcConnection.getConnection();

        PreparedStatement query = con.prepareStatement("SELECT * FROM games WHERE owner_id = ? and status = ? order by id desc limit 1");

        query.setLong(1, ownerId);
        query.setString(2, GameStatuses.CREATED);

        return query.executeQuery();
    }


}
