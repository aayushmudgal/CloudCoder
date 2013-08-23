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

import java.util.List;

import org.cloudcoder.app.client.rpc.HintService;
import org.cloudcoder.app.shared.model.CloudCoderAuthenticationException;
import org.cloudcoder.app.shared.model.Hint;
import org.cloudcoder.app.shared.model.Problem;
import org.cloudcoder.app.shared.model.ProblemAnalysisTagUrl;
import org.cloudcoder.app.shared.model.User;
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
    
    @Override
    public Hint requestHint(Problem problem, User user, String programText, List<ProblemAnalysisTagUrl> analyses)
    throws CloudCoderAuthenticationException
    {
        // Server-side code
        // TODO JSONify the hint request and pass it along to the webservice
        // Receive the result and return it
        Hint result=new Hint();
        result.setHintText("Hint hint hint!");
        return result;
    }
}
