package step5;

import java.sql.Connection;

public interface ConnectionMaker {
	Connection getConnection() throws Exception;
}
