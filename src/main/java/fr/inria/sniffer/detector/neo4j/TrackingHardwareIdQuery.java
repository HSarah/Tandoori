/*
 * Paprika - Detection of code smells in Android application
 *     Copyright (C)  2016  Geoffrey Hecht - INRIA - UQAM - University of Lille
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.inria.sniffer.detector.neo4j;

import org.neo4j.cypher.CypherException;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by Geoffrey Hecht on 18/08/15.
 */
public class TrackingHardwareIdQuery extends Query {

    private TrackingHardwareIdQuery(QueryEngine queryEngine) {
        super(queryEngine, "THI");
    }

    public static TrackingHardwareIdQuery createTrackingHardwareIdQuery(QueryEngine queryEngine) {
        return new TrackingHardwareIdQuery(queryEngine);
    }

    @Override
    protected String getQuery(boolean details) {
        String query = "MATCH (m1:Method)-[:CALLS]->(:ExternalMethod { full_name:'getDeviceId#android.telephony.TelephonyManager'}) RETURN m1.app_key as app_key";
        if (details) {
            query += ",m1.full_name as full_name";
        } else {
            query += ",count(m1) as THI";
        }
        return query;
    }
}
