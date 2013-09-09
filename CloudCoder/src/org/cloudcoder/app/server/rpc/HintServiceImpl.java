// CloudCoder - a web-based pedagogical programming environment
// Copyright (C) 2011-2013, Jaime Spacco <jspacco@knox.edu>
// Copyright (C) 2011-2013, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.cloudcoder.app.server.rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.cloudcoder.app.client.rpc.HintService;
import org.cloudcoder.app.server.persist.Database;
import org.cloudcoder.app.shared.model.CloudCoderAuthenticationException;
import org.cloudcoder.app.shared.model.Hint;
import org.cloudcoder.app.shared.model.Problem;
import org.cloudcoder.app.shared.model.ProblemAnalysisTagUrl;
import org.cloudcoder.app.shared.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jaimespacco
 *
 */
public class HintServiceImpl extends RemoteServiceServlet implements HintService
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger=LoggerFactory.getLogger(HintServiceImpl.class);
    private static final String ENCODING="UTF-8";
    
    @Override
    public Hint[] requestHint(Problem problem, User user, String programText, ProblemAnalysisTagUrl[] analyses)
    throws CloudCoderAuthenticationException
    {
        // Server-side code
        Hint[] results=new Hint[analyses.length];

        for (int i=0; i<analyses.length; i++) {
            ProblemAnalysisTagUrl analysis=analyses[i];

            // JSONify the request for help
            JSONObject jsonObj=convertHintRequestToJSON(problem.getProblemId(), user.getId(), programText);
            
            // TODO: Make these connections timeout at some point
            HttpClient client = new DefaultHttpClient();
            
            try {
                // Serialize the hint request into JSON
                StringWriter sw = new StringWriter();
                IOUtils.write(jsonObj.toString(), sw);
                StringEntity entity = new StringEntity(sw.toString(), ContentType.create("application/json", "UTF-8"));
            
                // create the post operation
                HttpPost post = new HttpPost(analysis.getAnalysisUrl());
                post.setEntity(entity);

                // Execute the HTTP POST operation
                HttpResponse response = client.execute(post);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), ENCODING));
                StringBuilder builder = new StringBuilder();
                for (String line = null; (line = reader.readLine()) != null;) {
                    builder.append(line).append("\n");
                }
                
                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    System.out.println("Success!");
                    JSONParser parser = new JSONParser();
                    ContainerFactory containerFactory = new ContainerFactory(){
                      public List<String> creatArrayContainer() {
                        return new LinkedList<String>();
                      }
                      public Map<String,String> createObjectContainer() {
                        return new LinkedHashMap<String,String>();
                      }
                    };
                    Map<String,String> json = (Map<String,String>)parser.parse(builder.toString(), containerFactory);
                    String hintText=(String)json.get("hint");

                    results[i]=new Hint();
                    results[i].setHintTag(analysis.getTag());
                    results[i].setHintText(hintText);
                } else if (statusLine.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                    logger.error("Web Service hint failure: Unauthorized HTTP access to "+analysis.getAnalysisUrl());
                } else {
                    logger.error("Web Service hint failure: Failed for some other reason");
                }
            } catch (IOException e) {
                logger.error("Unable to get hint for "+analysis.getTag()+" from "+analysis.getAnalysisUrl(), e);
            } catch (ParseException e) {
                logger.error("Unable to decode JSON response from "+analysis.getAnalysisUrl());
            } finally {
                client.getConnectionManager().shutdown();
            }
        }
        // TODO Eventually we should note which hint services were asked for and which ones failed
        // right now we are just noting that a button was pushed...
        
        Database.getInstance().storeHintRequest(user, problem, results);
        
        return results;
    }
    
    @SuppressWarnings("unchecked")
    private static JSONObject convertHintRequestToJSON(Integer problemId, Integer userId, String code) {
        JSONObject obj=new JSONObject();
        obj.put("code", code);
        obj.put("userId", userId);
        obj.put("problemId", problemId);
        return obj;
    }
    
    public ProblemAnalysisTagUrl[] getProblemAnalyses(Integer userId, Integer problemId)
    throws CloudCoderAuthenticationException
    {
        return Database.getInstance().lookupProblemAnalysisTagUrls(userId, problemId);
    }
}
