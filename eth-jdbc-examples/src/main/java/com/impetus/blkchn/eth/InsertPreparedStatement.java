/******************************************************************************* 
* * Copyright 2018 Impetus Infotech.
* *
* * Licensed under the Apache License, Version 2.0 (the "License");
* * you may not use this file except in compliance with the License.
* * You may obtain a copy of the License at
* *
* * http://www.apache.org/licenses/LICENSE-2.0
* *
* * Unless required by applicable law or agreed to in writing, software
* * distributed under the License is distributed on an "AS IS" BASIS,
* * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* * See the License for the specific language governing permissions and
* * limitations under the License.
******************************************************************************/
package com.impetus.blkchn.eth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eth.jdbc.DriverConstants;

public class InsertPreparedStatement {
    private static final Logger LOGGER = LoggerFactory.getLogger(InsertPreparedStatement.class);

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:blkchn:ethereum://ropsten.infura.io/1234";
        String driverClass = "com.impetus.eth.jdbc.EthDriver";
        try {
            Class.forName(driverClass);
            Properties prop = new Properties();
            prop.put(DriverConstants.KEYSTORE_PATH,
                    "/local/path/to/ethereum/keystore/UTC--XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            prop.put(DriverConstants.KEYSTORE_PASSWORD, "<password>");
            Connection conn = DriverManager.getConnection(url, prop);
            String query = "insert into transaction (toAddress, value, unit, async) values (?, ?, 'ether', true)";

            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setObject(1, "0x8144c67b144A408ABC989728e32965EDf37Adaa1");
            stmt.setObject(2, 0.001);
            stmt.executeUpdate();

            stmt.setObject(1, "0x8144c67b144A408ABC989728e32965EDf37Adaa1");
            stmt.setObject(2, 0.002);
            stmt.executeUpdate();

            conn.close();
            stmt.close();
            LOGGER.info("Connection closed " + conn.isClosed());
            LOGGER.info("Statement closed " + stmt.isClosed());

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
