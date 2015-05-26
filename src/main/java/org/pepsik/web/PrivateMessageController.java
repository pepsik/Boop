package org.pepsik.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 5/26/15.
 */

@Controller
@RequestMapping(value = "/messages")
public class PrivateMessageController {

    @RequestMapping(method = RequestMethod.GET, produces ="text/html")
    public String getMainPage() {
        return "messages/main";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET, produces ="text/html")
    public String getInputMessages() {
        return "messages/input";
    }

    @RequestMapping(value = "output", method = RequestMethod.GET, produces ="text/html")
    public String getOutputMessages() {
        return "messages/output";
    }
}
